import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ErroHandlerService } from 'app/core/erro-handler.service';
import { AuthService } from './../../seguranca/auth.service';
import { LogoutService } from './../../seguranca/logout.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.css']
})
export class InicioComponent implements OnInit {

  itens = [];

  constructor(private logoutService: LogoutService,
    private errorHandler: ErroHandlerService,
    private router: Router) { }

  ngOnInit() {
  }

  logout() {
    this.logoutService.logout()
      .then(() => {
        this.router.navigate(['/login']);
      })
      .catch(erro => this.errorHandler.handle(erro));
  }

}
