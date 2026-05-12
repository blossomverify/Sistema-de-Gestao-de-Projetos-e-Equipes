CREATE DATABASE IF NOT EXISTS projeto_a3;
USE projeto_a3;

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    cargo VARCHAR(100),
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('ADMINISTRADOR', 'GERENTE', 'COLABORADOR') NOT NULL
);

CREATE TABLE IF NOT EXISTS projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio VARCHAR(10),
    data_termino VARCHAR(10),
    status ENUM('PLANEJADO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO') NOT NULL,
    gerente_login VARCHAR(50),
    FOREIGN KEY (gerente_login) REFERENCES usuarios(login)
);

CREATE TABLE IF NOT EXISTS equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS equipe_membros (
    equipe_nome VARCHAR(255) NOT NULL,
    usuario_login VARCHAR(50) NOT NULL,
    PRIMARY KEY (equipe_nome, usuario_login),
    FOREIGN KEY (equipe_nome) REFERENCES equipes(nome),
    FOREIGN KEY (usuario_login) REFERENCES usuarios(login)
);

CREATE TABLE IF NOT EXISTS tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    responsavel_login VARCHAR(50),
    data_inicio VARCHAR(10),
    data_termino VARCHAR(10),
    status VARCHAR(50),
    FOREIGN KEY (responsavel_login) REFERENCES usuarios(login)
);
