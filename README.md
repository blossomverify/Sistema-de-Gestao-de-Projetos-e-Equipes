# Sistema de Gestão de Projetos e Equipes

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

Sistema desenvolvido para gerenciamento eficaz de projetos, tarefas e equipes, utilizando Java e o padrão de arquitetura MVC.

## 🛠 Tecnologias e Padrões

- **Linguagem**: Java (JDK 17+)
- **Banco de Dados**: MySQL (Porta 3306)
- **Interface**: Java Swing (GUI)
- **Arquitetura**: Padrão MVC

## 📁 Estrutura do Projeto

- `src/model/`: Classes de domínio.
- `src/view/`: Interface Gráfica Java Swing.
- `src/controller/`: Regras de negócio.
- `src/repository/`: Persistência via JDBC.
- `src/util/`: Conexão de Banco.
- `lib/`: Driver JDBC MySQL (Incluso).

## 🚀 Guia de Configuração (Para o Projeto Funcionar)

### 1. Banco de Dados (Obrigatório)
O sistema não inicia sem o banco de dados configurado.
- Abra o seu MySQL (XAMPP, WampServer ou MySQL Workbench).
- Execute o conteúdo do arquivo `database.sql` para criar o banco `projeto_a3`.
- **Importante**: O sistema está configurado para o usuário `root` sem senha. Se o seu MySQL tiver senha, altere na classe `src/util/DatabaseConnection.java`.

### 2. Configuração do Driver JDBC no VS Code
O Java precisa do driver para "falar" com o MySQL.
- No VS Code, clique no ícone do Java (Xícara) na barra lateral esquerda.
- Procure por **"Referenced Libraries"**.
- Clique no botão **"+"** (Add Library).
- Selecione o arquivo: `lib/mysql-connector-j-8.0.33.jar`.

### 3. Execução
- Clique com o botão direito no arquivo `src/Main.java`.
- Selecione **"Run Java"**.

## ❌ Solução de Problemas (Se não iniciar)

- **Erro "No suitable driver found":** Significa que o passo 2 não foi feito corretamente. Adicione o JAR da pasta `lib` nas bibliotecas do projeto.
- **Erro "Access denied for user":** Verifique seu usuário e senha do MySQL na classe `src/util/DatabaseConnection.java`.
- **O Banco de Dados está ligado?** Certifique-se de que o serviço do MySQL está ativo no seu computador.

Este projeto segue as diretrizes de código limpo, sem comentários no fonte.
