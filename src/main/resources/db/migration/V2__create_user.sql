CREATE TABLE usuario (
    id bigint not null auto_increment,
    nome varchar(50) not null,
    email varchar(50) not null,
    primary key (id)
);

insert into usuario values (1, 'Vitor Dev', 'vitor.dev@gmail.com');