import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BrowserModule } from '@angular/platform-browser';
import { Http, RequestOptions } from '@angular/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { InputTextModule } from 'primeng/components/inputtext/inputtext';
import { ButtonModule } from 'primeng/components/button/button';
import { DataTableModule } from 'primeng/components/datatable/datatable';
import { TooltipModule } from 'primeng/components/tooltip/tooltip';
import { DropdownModule } from 'primeng/components/dropdown/dropdown';
import { ConfirmDialogModule } from 'primeng/components/confirmdialog/confirmdialog';
import { ConfirmationService } from 'primeng/components/common/api';

import { ToastyModule } from 'ng2-toasty';
import { JwtHelper, AuthHttp, AuthConfig } from 'angular2-jwt';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import { UsuarioListagemComponent } from './usuario/usuario-listagem/usuario-listagem.component';
import { UsuarioNovoComponent } from './usuario/usuario-novo/usuario-novo.component';
import { UsuarioService } from './usuario/usuario.service';

import { CartaoNovoComponent } from './cartao/cartao-novo/cartao-novo.component';
import { CartaoListagemComponent } from './cartao/cartao-listagem/cartao-listagem.component';
import { CartaoService } from './cartao/cartao.service';

import { MessageComponent } from './message/message.component';
import { InicioComponent } from './inicio/inicio/inicio.component';
import { LoginComponent } from './login/login/login.component';

import { AuthGuard } from './seguranca/auth.guard';
import { AuthService } from './seguranca/auth.service';
import { LogoutService } from './seguranca/logout.service';
import { UrbanaHttp } from './seguranca/urbana-http';

import { PaginaNaoTrocadaComponent } from './pagina-ne/pagina-nao-trocada/pagina-nao-trocada.component';
import { NaoAutorizadoComponent } from './pagina-ne/nao-autorizado/nao-autorizado.component';
import { ErroHandlerService } from './core/erro-handler.service';

export function authHttpServiceFactory(auth: AuthService, http: Http, options: RequestOptions) {
  const config = new AuthConfig({
    globalHeaders: [
      { 'Content-Type': 'application/json' }
    ]
  });

  return new UrbanaHttp(auth, config, http, options);
}

@NgModule({
  declarations: [
    AppComponent,
    UsuarioListagemComponent,
    UsuarioNovoComponent,
    CartaoNovoComponent,
    CartaoListagemComponent,
    MessageComponent,
    InicioComponent,
    LoginComponent,
    PaginaNaoTrocadaComponent,
    NaoAutorizadoComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    HttpModule,

    InputTextModule,
    ButtonModule,
    DataTableModule,
    TooltipModule,
    DropdownModule,
    AppRoutingModule,
    ToastyModule.forRoot(),
    ConfirmDialogModule
  ],
  providers: [UsuarioService, ConfirmationService, CartaoService,
    ErroHandlerService, AuthService, JwtHelper, AuthGuard, LogoutService,
    {
      provide: AuthHttp,
      useFactory: authHttpServiceFactory,
      deps: [AuthService, Http, RequestOptions]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
