import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartaoService } from '../cartao.service';
import { ToastyService } from 'ng2-toasty';
import { Cartao } from 'app/model';

@Component({
  selector: 'app-cartao-novo',
  templateUrl: './cartao-novo.component.html',
  styleUrls: ['./cartao-novo.component.css']
})
export class CartaoNovoComponent implements OnInit {

  tipos = [
    { label: 'COMUM', value: 'COMUM' },
    { label: 'ESTUDANTE', value: 'ESTUDANTE' },
    { label: 'TRABALHADOR', value: 'TRABALHADOR' }
  ];

  usuarios = [];
  cartao = new Cartao();

  constructor(private cartaoService: CartaoService,
    private toasty: ToastyService,
    private router: Router) { }

  ngOnInit() {
    this.carregarUsuarios();
  }

  get editando() {
    return Boolean(this.cartao.id);
   }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizar(form);
    } else {
      this.adicionar(form);
    }
   }

  adicionar(form: FormControl) {
    this.cartaoService.adicionar(this.cartao)
      .then(() => {
        this.toasty.success('Cartão adicionado com sucesso!');
        this.router.navigate(['/cartoes/all']);
      })
      .catch(erro => this.toasty.error('Erro ao adicionar Cartão!'));
   }

   atualizar(form: FormControl) {
    this.cartaoService.atualizar(this.cartao)
    .then(cartao => {
      this.cartao = cartao;
      this.toasty.success('Cartão editado com sucesso!');
    })
    .catch(erro => this.toasty.error('Erro ao editar Cartão!'));
   }

  carregarUsuarios() {
    return this.cartaoService.listarUsuarios()
      .then(dados => {
        this.usuarios = dados
        .map(usuario => ({ label: usuario.nome, value: usuario.id }));
      })
       .catch(erro => this.toasty.error('Erro ao selecionar Usuários!'));
  }

}
