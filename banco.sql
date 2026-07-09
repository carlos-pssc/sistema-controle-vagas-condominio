



create database a3;

use a3;

create table funcionario (email varchar(255) primary key,nome_completo varchar(255) not null,senha varchar(255) not null);

create table apartamento (numero int primary key,tipo_responsavel enum('proprietario', 'inquilino') not null);

create table morador (cpf varchar(14) primary key,nome_completo varchar(255) not null,email varchar(255) unique not null,telefones varchar(50),numero_apartamento int not null,foreign key (numero_apartamento) references apartamento(numero));

create table veiculo (placa varchar(10) primary key,fabricante varchar(100),modelo varchar(100),cor varchar(50),numero_apartamento_vaga int unique not null,foreign key (numero_apartamento_vaga) references apartamento(numero));

create table aviso (id_aviso int primary key auto_increment,data_emissao date not null,cpf_morador_infrator varchar(14) not null,email_funcionario varchar(255) not null,foreign key (cpf_morador_infrator) references morador(cpf),foreign key (email_funcionario) references funcionario(email));

create table multa (id_multa int primary key auto_increment,data_emissao date not null,valor decimal(10, 2) not null default 200.00,cpf_morador_infrator varchar(14) not null,email_funcionario varchar(255) not null,foreign key (cpf_morador_infrator) references morador(cpf),foreign key (email_funcionario) references funcionario(email));