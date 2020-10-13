import { PaginaNaoTrocadaComponent } from './pagina-ne/pagina-nao-trocada/pagina-nao-trocada.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InicioComponent } from './inicio/inicio/inicio.component';
import { LoginComponent } from './login/login/login.component';
import { UsuarioRoutingModule } from './usuario/usuario-routing.module';
import { CartaoRoutingModule } from './cartao/cartao-routing.module';
import { NaoAutorizadoComponent } from './pagina-ne/nao-autorizado/nao-autorizado.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'inicio', component: InicioComponent },

  { path: 'nao-autorizado', component: NaoAutorizadoComponent },
  { path: 'pagina-nao-encontrada', component: PaginaNaoTrocadaComponent },
  { path: '**', redirectTo: 'pagina-nao-encontrada' },
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    UsuarioRoutingModule,
    CartaoRoutingModule
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
