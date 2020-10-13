CREATE TABLE permissao (
	id BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE usuario_permissao (
	id_usuario BIGINT(20) NOT NULL,
	id_permissao BIGINT(20) NOT NULL,
	PRIMARY KEY (id_usuario, id_permissao),
	FOREIGN KEY (id_usuario) REFERENCES usuario(id),
	FOREIGN KEY (id_permissao) REFERENCES permissao(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao (id, descricao) values (1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao (id, descricao) values (2, 'ROLE_REMOVER_USUARIO');
INSERT INTO permissao (id, descricao) values (3, 'ROLE_PESQUISAR_USUARIO');

INSERT INTO permissao (id, descricao) values (4, 'ROLE_CADASTRAR_CARTAO');
INSERT INTO permissao (id, descricao) values (5, 'ROLE_REMOVER_CARTAO');
INSERT INTO permissao (id, descricao) values (6, 'ROLE_PESQUISAR_CARTAO');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
