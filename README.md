# Sistema de Gestão de Projetos e Equipes - Projeto A3

![Status](https://img.shields.io/badge/Status-Pronto_para_Apresentação-green?style=for-the-badge)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) 
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

Sistema desktop completo para gerenciamento de projetos, tarefas e equipes, desenvolvido como parte da avaliação A3. O foco do projeto é a persistência de dados real, validação rigorosa de regras de negócio e interface amigável.

## 🚀 Funcionalidades Principais

- **Autenticação Segura**: Sistema de login com criptografia de senhas e perfil de administrador para contingência.
- **Gestão de Usuários**: Cadastro completo com validação de CPF, e-mail e perfis de acesso (Administrador, Gerente, Colaborador).
- **Gestão de Projetos**: Controle de cronograma (datas de início e término), descrição, status e atribuição de um gerente responsável.
- **Gestão de Equipes**: Criação de equipes e vinculação dinâmica de membros (usuários).
- **Gestão de Tarefas**: Detalhamento de atividades com prazos, status e responsáveis individuais.
- **Validação de Dados**: Tratamento rigoroso de datas, campos obrigatórios e prevenção de erros de banco de dados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: Java (JDK 17+)
- **Banco de Dados**: MySQL (utilizando XAMPP/WAMP ou servidor local)
- **Persistência**: JDBC com padrão Repository para separação de responsabilidades.
- **Interface**: Java Swing com layouts organizados.
- **Segurança**: Criptografia de senhas via prefixo de hash seguro.

## 📋 Pré-requisitos e Configuração

### 1. Preparação do Banco de Dados
O sistema requer que o MySQL esteja ativo.
- Abra o seu gerenciador de banco de dados (XAMPP é recomendado).
- Certifique-se de que o MySQL está rodando na porta **3306**.
- Importe ou execute o script contido no arquivo `database.sql` para criar a estrutura necessária.
- O banco de dados deve se chamar `projeto_a3`.

### 2. Configuração de Acesso
A configuração de conexão encontra-se em `src/util/DatabaseConnection.java`. 
- **Host padrão**: `127.0.0.1` (para evitar conflitos de resolução de nome).
- **Usuário padrão**: `root`
- **Senha padrão**: ` ` (vazia - comum no XAMPP). *Caso sua senha seja diferente, altere nesta classe.*

### 3. Execução
1. Certifique-se de que o driver `lib/mysql-connector-j-8.0.33.jar` está adicionado ao seu classpath (no VS Code, em *Referenced Libraries*).
2. Execute a classe `Main.java` localizada em `src/`.

## 📁 Estrutura de Pastas

- `src/model`: Entidades (Usuário, Projeto, Equipe, Tarefa).
- `src/view`: Telas do sistema (Swing).
- `src/controller`: Intermediário entre View e Repository.
- `src/repository`: Lógica de SQL e acesso ao banco.
- `src/util`: Conexão com o banco de dados.

---
*Desenvolvido para fins acadêmicos - Projeto A3*
