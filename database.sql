CREATE DATABASE IF NOT EXISTS projeto_a3;
USE projeto_a3;

CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    cargo VARCHAR(100) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('ADMINISTRADOR', 'GERENTE', 'COLABORADOR') NOT NULL
);

CREATE TABLE projetos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_inicio DATE NOT NULL,
    data_termino DATE,
    status ENUM('PLANEJADO', 'EM_ANDAMENTO', 'CONCLUIDO', 'CANCELADO') NOT NULL,
    gerente_id INT,
    
    FOREIGN KEY (gerente_id) REFERENCES usuarios(id)
);

CREATE TABLE equipes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT
);

CREATE TABLE equipe_membros (
    equipe_id INT NOT NULL,
    usuario_id INT NOT NULL,

    PRIMARY KEY (equipe_id, usuario_id),

    FOREIGN KEY (equipe_id) REFERENCES equipes(id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE tarefas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT,
    responsavel_id INT,
    data_inicio DATE NOT NULL,
    data_termino DATE,
    status ENUM('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDA') NOT NULL,

    FOREIGN KEY (responsavel_id) REFERENCES usuarios(id)
);