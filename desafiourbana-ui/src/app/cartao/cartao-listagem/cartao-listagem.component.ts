import { Usuario } from './../../model';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { CartaoService, CartaoFiltro } from '../cartao.service';
import { ConfirmationService } from 'primeng/components/common/api';
import { ErroHandlerService } from 'app/core/erro-handler.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cartao-listagem',
  templateUrl: './cartao-listagem.component.html',
  styleUrls: ['./cartao-listagem.component.css']
})
export class CartaoListagemComponent implements OnInit {

  cartoes = [];
  nome: string;
  @ViewChild('tabela') grid;
  usuario = new Usuario();

  constructor(private cartaoService: CartaoService,
    private toasty: ToastyService,
    private route: ActivatedRoute,
    private errorHandler: ErroHandlerService,
    private confirmation: ConfirmationService) { }

  ngOnInit() {
    const idUsuario = this.route.snapshot.params['id'];
    if (idUsuario) {
      this.listarPorUsuario(idUsuario);
    } else {
      this.listar();
    }
  }

  listar() {
    const filtro: CartaoFiltro = {
      nome: this.nome
    };
    this.cartaoService.listar(filtro)
    .then(dados => this.cartoes = dados);
  }

  listarPorUsuario(id: number) {
    this.cartaoService.listarCartoesUsuario(id)
    .then(dados => this.cartoes = dados)
    .catch(erro => this.toasty.error('Erro ao listar por usuários!'));
  }

  confirmarExclusao(usuario: any) {
    this.cartaoService.excluir(usuario.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.listar();
        } else {
          this.grid.first = 0;
        }
        this.toasty.success('Cartão excluído com sucesso!');
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

  excluir(cartao: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.confirmarExclusao(cartao);
      }
    })
  }

  alternarStatus(cartao: any): void {
    const novoStatus = !cartao.ativo;
    this.cartaoService.mudarStatus(cartao.id, novoStatus)
      .then(() => {
        const acao = novoStatus ? 'ativado' : 'desativado';
        cartao.status = novoStatus;
        this.toasty.success(`Cartão ${acao} com sucesso!`);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
