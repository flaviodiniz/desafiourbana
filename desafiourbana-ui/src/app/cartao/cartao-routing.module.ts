import { AuthGuard } from 'app/seguranca/auth.guard';
import { Routes, RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { CartaoListagemComponent } from './cartao-listagem/cartao-listagem.component';
import { CartaoNovoComponent } from './cartao-novo/cartao-novo.component';

const routes: Routes = [
    { path: 'cartoes/all', component: CartaoListagemComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_CARTAO'] }},
    { path: 'cartoes/all/:id', component: CartaoListagemComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_PESQUISAR_CARTAO'] }},
    { path: 'cartoes/novo', component: CartaoNovoComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CARTAO'] }} ,
    { path: 'cartoes/:id', component: CartaoNovoComponent, canActivate: [AuthGuard],
    data: { roles: ['ROLE_CADASTRAR_CARTAO'] }},
];

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class CartaoRoutingModule { }
