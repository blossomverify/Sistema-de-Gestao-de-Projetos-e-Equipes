# Sistema de Gestão de Projetos e Equipes

![Banner](https://images.unsplash.com/photo-1531403009284-440f080d1e12?ixlib=rb-4.0.3&auto=format&fit=crop&w=1000&q=80)

![Status](https://img.shields.io/badge/Status-Desenvolvimento-green?style=for-the-badge)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

Sistema desenvolvido para gerenciamento eficaz de projetos, tarefas e equipes, utilizando Java e o padrão de arquitetura MVC.

## Funcionalidades

- **Gestão de Usuários**: Cadastro e controle de perfis de acesso.
- **Gestão de Equipes**: Organização de membros em grupos de trabalho.
- **Gestão de Projetos**: Controle de prazos, status e atribuição de equipes.
- **Gestão de Tarefas**: Detalhamento de atividades vinculadas a projetos.
- **Interface Gráfica**: Telas amigáveis desenvolvidas em Java Swing.

## Tecnologias e Padrões

- **Linguagem**: Java (JDK 17+)
- **Banco de Dados**: MySQL (Porta 3306)
- **Interface**: Java Swing (GUI)
- **Arquitetura**: MVC (Model-View-Controller)
- **Persistência**: JDBC (Java Database Connectivity)

## Estrutura do Projeto

- `src/model/`: Classes de domínio e entidades.
- `src/view/`: Interface Gráfica com Java Swing.
- `src/controller/`: Lógica de controle e regras de negócio.
- `src/repository/`: Camada de acesso a dados (DAO/Repository).
- `src/util/`: Utilitários e configuração de conexão.
- `lib/`: Dependências externas (Driver JDBC).

## Guia de Configuração

### 1. Banco de Dados (Obrigatório)
O sistema não inicia sem o banco de dados configurado e **LIGADO**.
- Abra o seu painel do **MySQL** (Workbench ou XAMPP).
- Execute o conteúdo do arquivo `database.sql` para criar o banco e as tabelas.
- **Configuração Atual**: O sistema está configurado para o usuário `root` com a senha `011020`. Se precisar alterar, edite a classe `src/util/DatabaseConnection.java`.

### 2. Configuração do Driver JDBC no VS Code
O Java precisa do driver para "falar" com o MySQL.
- No VS Code, clique no ícone do Java (Xícara) na barra lateral esquerda.
- Procure por **"Referenced Libraries"**.
- Clique no botão **"+"** (Add Library).
- Selecione o arquivo: `lib/mysql-connector-j-8.0.33.jar`.

### 3. Execução
- Clique com o botão direito no arquivo `src/Main.java`.
- Selecione **"Run Java"**.

## Solução de Problemas

- **Communications link failure**: O seu **MySQL está desligado**. Ligue o serviço do MySQL ou XAMPP.
- **Access denied for user**: A senha no banco é diferente de `Apolo*654321`. Ajuste em `DatabaseConnection.java`.
- **Unknown database 'projeto_a3'**: Você esqueceu de rodar o `database.sql`.

Este projeto segue as diretrizes de código limpo, sem comentários no fonte.
