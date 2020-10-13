import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ErroHandlerService } from 'app/core/erro-handler.service';
import { AuthService } from './../../seguranca/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  constructor(private auth: AuthService,
    private errorHandler: ErroHandlerService,
    private router: Router,
    ) { }

  login(usuario: string, senha: string) {
    this.auth.login(usuario, senha)
      .then(() => {
        this.router.navigate(['/inicio']);
      })
      .catch(erro => {
        this.errorHandler.handle(erro);
      });
  }

}
