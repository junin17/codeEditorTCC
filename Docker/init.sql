create table usuario(
	id serial primary key,
	nome varchar(50),
	login varchar(50),
	sobrenome varchar(50),
	email varchar(25),
	password varchar(100),
	isAdmin bool
);


create table PROBLEMA(
	id serial primary key,
	titulo varchar(100),
	descricao text,
	numero integer,
	pontuacao integer
);

create table CASOS_TESTES(
	id serial primary key,
	entrada text,
	saida text,
	problema_id integer references problema(id)
);

create table Resolucao(
	id serial primary key,
	codigo text,
	linguagem varchar(15),
	data_submissao timestamp,
	problema_Id integer not null references problema(id),
	usuario_Id integer not null references usuario(id)
);

create table relacionamento(
	usuario1 integer not null references usuario(id),
	usuario2 integer not null references usuario(id),
	primary key (usuario1,usuario2)
);


