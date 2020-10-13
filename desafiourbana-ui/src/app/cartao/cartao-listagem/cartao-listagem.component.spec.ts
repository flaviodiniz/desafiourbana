import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CartaoListagemComponent } from './cartao-listagem.component';

describe('CartaoListagemComponent', () => {
  let component: CartaoListagemComponent;
  let fixture: ComponentFixture<CartaoListagemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CartaoListagemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CartaoListagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
