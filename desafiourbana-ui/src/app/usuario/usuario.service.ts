import { environment } from './../../environments/environment';
import { Injectable, OnInit } from '@angular/core';
import { Headers, URLSearchParams } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Usuario } from './../model';

export interface UsuarioFiltro {
  nome: string;
}

@Injectable()
export class UsuarioService implements OnInit {

  usuariosUrl: string;

  constructor(private http: AuthHttp) {
    this.usuariosUrl = `${environment.apiUrl}/usuarios`;
   }

   ngOnInit() {

  }

  listar(filtro: UsuarioFiltro): Promise<any> {
    const params = new URLSearchParams();
    if (filtro.nome) {
      params.set('nome', filtro.nome);
    }
    return this.http.get(`${this.usuariosUrl}?resumo`, { search: params })
      .toPromise()
      .then(response => response.json().content)
  }

  listarTodas(): Promise<any> {
    return this.http.get(`${this.usuariosUrl}/all`)
      .toPromise()
      .then(response => response.json().content);
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.usuariosUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(usuario: Usuario): Promise<Usuario> {
    return this.http.post(`${this.usuariosUrl}/novo`,
        JSON.stringify(usuario))
      .toPromise()
      .then(response => response.json());
  }

  atualizar(usuario: Usuario): Promise<Usuario> {
    return this.http.put(`${this.usuariosUrl}/novo/${usuario.id}`,
        JSON.stringify(usuario))
      .toPromise()
      .then(response => {
        const usuarioAlterado = response.json() as Usuario;
        return usuarioAlterado;
      });
  }

  buscarPorId(id: number): Promise<Usuario> {
    return this.http.get(`${this.usuariosUrl}/${id}`)
      .toPromise()
      .then(response => {
        const usuario = response.json() as Usuario;
        return usuario;
      });
  }

}
