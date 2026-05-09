# Sistema de Gestão de Projetos e Equipes

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)

Um sistema desenvolvido em Java para ajudar empresas a gerenciar projetos, equipes e tarefas, garantindo um controle mais efetivo sobre os prazos de entrega e o aproveitamento dos colaboradores.

## 💻 Funcionalidades Principais

- **Gestão de Usuários**: Cadastro de perfis com definição de hierarquia (Administrador, Gerente e Colaborador).
- **Gestão de Projetos**: Acompanhamento detalhado do ciclo de vida dos projetos (status, datas de início e término) e designação de gerentes responsáveis.
- **Gestão de Equipes**: Criação de times e alocação dinâmica de membros para atuar em múltiplos projetos.

## 🛠️ Tecnologias e Padrões

- **Linguagem**: Java
- **Controle de Versão**: Git e GitHub
- **Arquitetura**: Padrão MVC (Model, View, Controller)

## 📁 Estrutura do Projeto

O sistema foi estruturado seguindo boas práticas de programação orientada a objetos:

- `src/model/`: Classes que representam os dados e o domínio (Ex: Usuario, Projeto, Equipe).
- `src/view/`: Classes responsáveis pela interface e interação direta com o usuário.
- `src/controller/`: Classes que processam as regras de negócio e fazem a ponte entre Model e View.
- `src/Main.java`: Classe principal e porta de entrada para execução do sistema.

## 🚀 Como Executar

1. Certifique-se de ter o [Java JDK](https://www.oracle.com/java/technologies/downloads/) instalado na sua máquina.
2. Faça o clone do repositório:
   ```bash
   git clone https://github.com/blossomverify/Sistema-de-Gestao-de-Projetos-e-Equipes.git
   ```
3. Abra a pasta do projeto na sua IDE de preferência (VS Code, IntelliJ, Eclipse).
4. Execute o arquivo `src/Main.java`.
