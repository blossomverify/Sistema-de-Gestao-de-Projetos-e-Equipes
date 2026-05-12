# Sistema de Gestão de Projetos e Equipes

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

Sistema desenvolvido para gerenciamento eficaz de projetos, tarefas e equipes, utilizando Java e o padrão de arquitetura MVC.

## 🛠 Tecnologias e Padrões

- **Linguagem**: Java
- **Banco de Dados**: MySQL
- **Controle de Versão**: Git e GitHub
- **Arquitetura**: Padrão MVC (Model, View, Controller)

## 📁 Estrutura do Projeto

O sistema foi estruturado seguindo boas práticas de programação orientada a objetos:

- `src/model/`: Classes de domínio (Ex: Usuario, Projeto).
- `src/view/`: Interface Gráfica Java Swing.
- `src/controller/`: Regras de negócio.
- `src/repository/`: Persistência de dados via JDBC (MySQL).
- `src/util/`: Utilitários (Conexão de Banco).
- `lib/`: Driver JDBC do MySQL.

## 🚀 Como Executar

1. **Requisitos:** Tenha o Java JDK e o MySQL instalados.
2. **Banco de Dados:**
   - Certifique-se de que o MySQL está rodando.
   - Execute o script `database.sql` no seu MySQL para criar o banco e as tabelas.
3. **Driver JDBC (VS Code):**
   - No menu lateral do Java, vá em `Referenced Libraries`.
   - Clique no `+` e selecione o arquivo `lib/mysql-connector-j-8.0.33.jar`.
4. **Executar:**
   - Rode o arquivo `src/Main.java`.

Este projeto foi desenvolvido sem comentários no código fonte, prezando pela legibilidade e código limpo.
