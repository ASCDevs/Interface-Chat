create database SocketAps;

use SocketAps;

create table Grupo(
	id_grupo int auto_increment primary key,
	nome_grupo varchar(100)
);

# Criar on delete Cascade
create table Usuario(
	id_usuario int auto_increment primary key,
	nome_usuario varchar(100) not null,
	username_usuario varchar(100) not null,
	senha_usuario varchar(100) not null,
	ip_address_usuario varchar(18) not null,
	porta_ip_usuario varchar(5) not null
);
ALTER TABLE Usuario ADD COLUMN sobrenome_usuario varchar(100) not null;
ALTER TABLE Usuario ADD COLUMN equipe_usuario varchar(100) not null;

create table UsuarioGrupo(
	id_usuario_grupo int auto_increment primary key,
	id_usuario int,
	id_grupo int,

	foreign key (id_usuario) references Usuario(id_usuario),
	foreign key (id_grupo) references Grupo(id_grupo)
);

create table UsuarioRelacao(
	id_usuario_relacao int auto_increment primary key,
	id_usuario_emissor int,
	id_usuario_receptor int,

	foreign key (id_usuario_emissor) references Usuario(id_usuario),
	foreign key (id_usuario_receptor) references Usuario(id_usuario)
);

create table HistoricoConversa(
	id_conversa int auto_increment primary key,
    id_remetente int not null,
    id_destinatario int not null,
    mensagem varchar(255),
	foreign key (id_remetente) references Usuario(id_usuario),
    foreign key (id_destinatario) references Usuario(id_usuario)
);

drop table HistoricoConversa;

# ALTER TABLE HistoricoConversa ADD COLUMN arquivo ???;
# ALTER TABLE HistoricoConversa ADD COLUMN id_conversa int auto_increment primary key;


create table UsuarioHistorico(
	id_usuario_historico int auto_increment primary key,
	mensagem varchar(255),
	data_mensagem datetime,
	id_usuario_relacao int,

	foreign key (id_usuario_relacao) references UsuarioRelacao(id_usuario_relacao)
);

create table GrupoHistorico(
	id_grupo_historico int auto_increment primary key,
	mensagem varchar(255),
	data_mensagem datetime,
	id_usuario_grupo int,

	foreign key (id_usuario_grupo) references UsuarioGrupo(id_usuario_grupo)
);