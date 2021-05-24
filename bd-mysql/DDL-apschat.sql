create database apschat;
use apschat;

create table Usuario(
	id_usuario int auto_increment primary key,
	nome_usuario varchar(100) not null,
	username_usuario varchar(100) not null unique,
	senha_usuario varchar(100) not null,
	porta_ip_usuario varchar(5) not null unique,
    sobrenome_usuario varchar(100) not null,
    equipe_usuario varchar(100) not null
);


create table HistoricoConversa(
	id_conversa int auto_increment primary key,
    id_remetente int not null,
    id_destinatario int not null,
    mensagem varchar(255),
	foreign key (id_remetente) references Usuario(id_usuario) on delete cascade,
    foreign key (id_destinatario) references Usuario(id_usuario) on delete cascade
);

