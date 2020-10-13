import { UsuarioFiltro,  UsuarioService } from './../usuario.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastyService } from 'ng2-toasty';
import { ConfirmationService } from 'primeng/components/common/api';

@Component({
  selector: 'app-usuario-listagem',
  templateUrl: './usuario-listagem.component.html',
  styleUrls: ['./usuario-listagem.component.css']
})
export class UsuarioListagemComponent implements OnInit {

  usuarios = [];
  nome: string;
  @ViewChild('tabela') grid;

  constructor(private usuarioService: UsuarioService,
    private toasty: ToastyService,
    private confirmation: ConfirmationService) { }

  ngOnInit() {
    this.listar();
  }

  listar() {
    const filtro: UsuarioFiltro = {
      nome: this.nome
    };
    this.usuarioService.listar(filtro)
    .then(dados => this.usuarios = dados);
  }

  confirmarExclusao(usuario: any) {
    this.usuarioService.excluir(usuario.id)
      .then(() => {
        if (this.grid.first === 0) {
          this.listar();
        } else {
          this.grid.first = 0;
        }
        this.toasty.success('Usuário excluído com sucesso!');
      });
  }

  excluir(usuario: any) {
    this.confirmation.confirm({
      message: 'Tem certeza que deseja excluir?',
      accept: () => {
        this.confirmarExclusao(usuario);
      }
    });
  }

}
