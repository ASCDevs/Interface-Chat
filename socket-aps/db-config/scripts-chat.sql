use baseaps;

Select * from ArquivosHistorico;

select * from Usuario;
select * from Grupo;
select * from UsuarioGrupo;
select * from UsuarioRelacao;



select * from UsuarioContatos; #ok
select * from HistoricoConversa; #ok

select * from UsuarioHistorico;
select * from GrupoHistorico;
-- REMETENTE - DESTINATÁRIO
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,3,"Olá, Bom dia");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(3,12,"Buenos dias como vais?");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(3,12,"Mensagem de teste 1");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(3,12,"Mensagem de teste 2");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,3,"Mensagem de teste 3");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,3,"Mensagem de teste 4");
INSERT INTO HistoricoConversa(id_remetente,id_destinatario,mensagem) values(,,);

-- REMETENTE - DESTINATÁRIO
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(4,12,"Mensagem com outro 1");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,4,"Mensagem com outro 2");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(4,12,"Mensagem com outro 1");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,4,"Mensagem com outro 2");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(4,12,"Mensagem com outro 1");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(12,4,"Mensagem com outro 2");
insert into HistoricoConversa(id_remetente,id_destinatario,mensagem) values(4,12,"Mensagem final");

SELECT * FROM HistoricoConversa WHERE id_remetente in(12,3) AND id_destinatario in(12,3) ORDER BY id_conversa ASC;
SELECT * FROM HistoricoConversa WHERE id_remetente in(12,4) AND id_destinatario in(12,4) ORDER BY id_conversa ASC;

SELECT username_usuario, senha_usuario FROM Usuario WHERE senha_usuario = "123" AND username_usuario = "alexandre";
SELECT u.id_usuario, u.nome_usuario, u.username_usuario, u.porta_ip_usuario, g.nome_grupo FROM UsuarioGrupo ug inner join Usuario u on u.id_usuario = ug.id_usuario and nome_usuario = "alexandre" left join Grupo g on g.id_grupo = ug.id_grupo;

insert into Usuario(nome_usuario,username_usuario,senha_usuario,porta_ip_usuario) values("Ademiro","admin","admin",5122);
SELECT * FROM Usuario where username_usuario = "alexandre";

SELECT username_usuario, senha_usuario FROM Usuario WHERE username_usuario = "rafaela" AND senha_usuario = "12";

insert into UsuarioContatos values(12,3);
insert into UsuarioContatos values(12,4);
insert into UsuarioContatos values(12,5);
insert into UsuarioContatos values(12,6);

insert into UsuarioContatos values(6,1);
insert into UsuarioContatos values(6,2);
insert into UsuarioContatos values(6,7);

SELECT * FROM Usuario WHERE id_usuario <> 1;

DELETE FROM Usuario WHERE id_usuario = 11;

UPDATE Usuario SET equipe_usuario = 'Unidade I' where id_usuario = 1;
UPDATE Usuario SET equipe_usuario = 'Unidade I' where id_usuario = 2;
UPDATE Usuario SET equipe_usuario = 'Unidade I' where id_usuario = 3;
UPDATE Usuario SET equipe_usuario = 'Unidade II' where id_usuario = 4;
UPDATE Usuario SET equipe_usuario = 'Unidade II' where id_usuario = 5;
UPDATE Usuario SET equipe_usuario = 'Unidade II' where id_usuario = 6;
UPDATE Usuario SET equipe_usuario = 'Unidade III' where id_usuario = 7;
UPDATE Usuario SET equipe_usuario = 'Unidade III' where id_usuario = 8;
UPDATE Usuario SET equipe_usuario = 'Unidade III' where id_usuario = 9;
UPDATE Usuario SET equipe_usuario = 'Unidade III' where id_usuario = 12;

SELECT * FROM Usuario;

SELECT u.* FROM Usuario u, UsuarioContatos uc WHERE uc.id_contato = u.id_usuario AND uc.id_usuario = 6;
DELETE FROM Usuario WHERE id_usuario = 10;

select * from HistoricoConversa;
delete from HistoricoConversa;
drop table HistoricoConversa;