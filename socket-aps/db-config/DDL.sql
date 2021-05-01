create database SocketAps

use SocketAps

create table Grupo(
	id_grupo int auto_increment primary key,
	nome_grupo varchar(100)
)

create table Usuario(
	id_usuario int auto_increment primary key,
	nome_usuario varchar(100) not null,
	username_usuario varchar(100) not null,
	senha_usuario varchar(100) not null,
	ip_address_usuario varchar(18) not null,
	porta_ip_usuario varchar(5) not null
)

create table UsuarioGrupo(
	id_usuario_grupo int auto_increment primary key,
	id_usuario int,
	id_grupo int,

	foreign key (id_usuario) references Usuario(id_usuario),
	foreign key (id_grupo) references Grupo(id_grupo)
)

create table UsuarioRelacao(
	id_usuario_relacao int auto_increment primary key,
	id_usuario_emissor int,
	id_usuario_receptor int,

	foreign key (id_usuario_emissor) references Usuario(id_usuario),
	foreign key (id_usuario_receptor) references Usuario(id_usuario)
)

create table UsuarioHistorico(
	id_usuario_historico int auto_increment primary key,
	mensagem varchar(255),
	data_mensagem datetime,
	id_usuario_relacao int,

	foreign key (id_usuario_relacao) references UsuarioRelacao(id_usuario_relacao)
)

create table GrupoHistorico(
	id_grupo_historico int auto_increment primary key,
	mensagem varchar(255),
	data_mensagem datetime,
	id_usuario_grupo int,

	foreign key (id_usuario_grupo) references UsuarioGrupo(id_usuario_grupo)
)