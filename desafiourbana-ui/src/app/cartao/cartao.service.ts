import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { URLSearchParams } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { Cartao } from './../model';

export interface CartaoFiltro {
  nome: string;
}

@Injectable()
export class CartaoService {

  cartoesUrl: string;

  constructor(private http: AuthHttp) {
    this.cartoesUrl = `${environment.apiUrl}/cartoes`;
  }

  listar(filtro: CartaoFiltro): Promise<any> {
    const params = new URLSearchParams();
    if (filtro.nome) {
      params.set('nome', filtro.nome);
    }
    return this.http.get(`${this.cartoesUrl}?resumo`, { search: params })
      .toPromise()
      .then(response => response.json().content)
  }

  excluir(id: number): Promise<void> {
    return this.http.delete(`${this.cartoesUrl}/${id}`)
      .toPromise()
      .then(() => null);
  }

  adicionar(cartao: Cartao): Promise<Cartao> {
    return this.http.post(`${this.cartoesUrl}/novo`,
        JSON.stringify(cartao))
      .toPromise()
      .then(response => response.json());
  }

  atualizar(cartao: Cartao): Promise<Cartao> {
    return this.http.put(`${this.cartoesUrl}/novo/${cartao.id}`,
        JSON.stringify(cartao))
      .toPromise()
      .then(response => {
        const usuarioAlterado = response.json() as Cartao;
        return usuarioAlterado;
      });
  }

  listarUsuarios(): Promise<any> {
    return this.http.get('`${environment.apiUrl}/usuarios/all`')
      .toPromise()
      .then(response => response.json());
  }

  mudarStatus(id: number, ativo: boolean): Promise<void> {
    return this.http.put(`${this.cartoesUrl}/${id}/ativo`, ativo)
      .toPromise()
      .then(() => null);
  }

  listarCartoesUsuario(id: number): Promise<any> {
    return this.http.get(`${this.cartoesUrl}/all/${id}`)
      .toPromise()
      .then(response => response.json());
  }

}
