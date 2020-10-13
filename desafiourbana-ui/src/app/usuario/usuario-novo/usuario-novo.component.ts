import { FormControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Usuario } from './../../model';
import { UsuarioService } from '../usuario.service';
import { ToastyService } from 'ng2-toasty';

@Component({
  selector: 'app-usuario-novo',
  templateUrl: './usuario-novo.component.html',
  styleUrls: ['./usuario-novo.component.css']
})
export class UsuarioNovoComponent implements OnInit {

  usuario = new Usuario();

  constructor(private usuarioService: UsuarioService,
    private toasty: ToastyService,
    private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit() {
    const idUsuario = this.route.snapshot.params['id'];
    if (idUsuario) {
      this.carregarUsuario(idUsuario);
    }
  }

  get editando() {
    return Boolean(this.usuario.id);
   }

  carregarUsuario(id: number) {
    this.usuarioService.buscarPorId(id)
    .then(usuario => {
      this.usuario = usuario;
    })
  }

  salvar(form: FormControl) {
    if (this.editando) {
      this.atualizar(form);
    } else {
      this.adicionar(form);
    }
  }

  adicionar(form: FormControl) {
    this.usuarioService.adicionar(this.usuario)
      .then(() => {
        this.toasty.success('Usuário adicionado com sucesso!');

        form.reset();
        this.usuario = new Usuario();
        this.router.navigate(['/usuarios/all']);
      })
      .catch(erro => this.toasty.error('Erro ao adicionar Usuário!'));
   }

  atualizar(form: FormControl) {
    this.usuarioService.atualizar(this.usuario)
      .then(usuario => {
        this.usuario = usuario;
        this.toasty.success('Usuário editado com sucesso!');
      })
      .catch(erro => this.toasty.error('Erro ao editar Usuário!'));
  }

}
