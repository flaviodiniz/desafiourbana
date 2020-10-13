import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { UsuarioNovoComponent } from './usuario-novo/usuario-novo.component';
import { UsuarioListagemComponent } from './usuario-listagem/usuario-listagem.component';
import { AuthGuard } from './../seguranca/auth.guard';

const routes: Routes = [
    { path: 'usuarios/all', component: UsuarioListagemComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_USUARIO'] }},

    { path: 'usuarios/novo', component: UsuarioNovoComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_USUARIO'] }},

    { path: 'usuarios/:id', component: UsuarioNovoComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_USUARIO'] }},
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class UsuarioRoutingModule { }
