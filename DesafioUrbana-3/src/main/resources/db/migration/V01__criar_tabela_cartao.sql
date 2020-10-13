CREATE TABLE usuario (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL,
	senha VARCHAR(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE cartao (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	numeroCartao int(30) NOT NULL,
	nome VARCHAR(50) NOT NULL,
	status boolean NOT NULL,
	tipoCartao VARCHAR(150) NOT NULL,
	usuario BIGINT(50) NOT NULL,
	FOREIGN KEY (usuario) REFERENCES usuario(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO usuario (id, nome, email, senha) values (1, 'Usuario', 'usuario@urbana.com', '$2a$10$bGv8AUaD0MmvXtcJc9mP5OsiIx19lPnc8nKuCpksUb10XuAc/LWu.');
