# Sistema de Gestão 📊

Sistema simples para gerenciar usuários, equipes e projetos desenvolvido em Java com persistência em arquivos.

## 🚀 Funcionalidades

- **Interface Gráfica (Swing)**: Sistema com interface visual amigável
- **Gestão de Usuários**: Criar, listar, atualizar e remover usuários
- **Gestão de Equipes**: Criar, listar, atualizar e deletar equipes  
- **Gestão de Projetos**: Adicionar, listar, atualizar e remover projetos
- **Persistência de Dados**: Salvamento automático em arquivos (JSON e binário)
- **Interface Console**: Versão alternativa para linha de comando

## 🏗️ Estrutura do Projeto

```
ProjetoA3/
├── src/
│   ├── view/                    ← Interface Gráfica (Swing)
│   │   ├── MainWindow.java
│   │   ├── UsuarioWindow.java
│   │   ├── EquipeWindow.java
│   │   └── ProjetoWindow.java
│   ├── main/
│   │   ├── Main.java           ← Versão Console
│   │   └── MainSwing.java      ← Versão GUI
│   ├── modelos/
│   │   ├── Usuario.java
│   │   ├── Equipe.java
│   │   └── Projeto.java
│   ├── controller/
│   │   ├── UsuarioController.java
│   │   ├── EquipeController.java
│   │   └── ProjetoController.java
│   └── repository/
│       ├── UsuarioRepository.java
│       ├── EquipeRepository.java
│       └── ProjetoRepository.java
└── README.md
```

## 🛠️ Tecnologias Utilizadas

- **Java** - Linguagem principal
- **Java Swing** - Interface gráfica (GUI)
- **Gson** - Serialização JSON (para equipes e projetos)
- **Serialização Java** - Persistência binária (para usuários)

## 📋 Modelos de Dados

### Usuario
- Nome completo
- CPF
- Email
- Cargo
- Login/Senha
- Perfil (administrador, gerente, colaborador)

### Equipe
- Nome da equipe
- Descrição

### Projeto
- Nome
- Descrição
- Data de início
- Data de término prevista
- Status (planejado, em andamento, concluído, cancelado)

## 🚦 Como Executar

### Pré-requisitos
- Java 8 ou superior
- Biblioteca Gson (para JSON)

### Executando o projeto

#### 🎨 **Versão com Interface Gráfica (Recomendada)**

1. **Clone o repositório**
```bash
git clone https://github.com/seu-usuario/ProjetoA3.git
cd ProjetoA3
```

2. **Compile o projeto**
```bash
javac -cp ".:gson-2.8.9.jar" src/main/MainSwing.java src/view/*.java src/modelos/*.java src/controller/*.java src/repository/*.java
```

3. **Execute a aplicação GUI**
```bash
java -cp ".:gson-2.8.9.jar:src" main.MainSwing
```

#### 📟 **Versão Console (Alternativa)**

1. **Compile a versão console**
```bash
javac -cp ".:gson-2.8.9.jar" src/main/Main.java src/modelos/*.java src/controller/*.java src/repository/*.java
```

2. **Execute a versão console**
```bash
java -cp ".:gson-2.8.9.jar:src" main.Main
```

### 🎨 Interface Gráfica (Swing)

O sistema possui uma **interface visual moderna** com:

- **Menu Principal**: Navegação entre módulos
- **Formulários**: Cadastro e edição com validação
- **Tabelas**: Listagem organizada dos dados
- **Botões**: Operações CRUD intuitivas
- **Confirmações**: Diálogos para ações críticas

#### Telas Disponíveis:
- **Usuários**: Formulário completo com 7 campos
- **Equipes**: Cadastro simples com nome e descrição
- **Projetos**: Gestão completa com datas e status

### Exemplo de Saída (Console)
```
=== TESTE DO SISTEMA ===

1. Testando Usuários:
Usuário adicionado com sucesso!
Usuário{nome='João Silva', cpf='123.456.789-00', email='joao@email.com', cargo='Desenvolvedor', login='joao123', perfil='Admin'}

2. Testando Equipes:
Equipe criada com sucesso!
Equipe{nomeEquipe='Equipe Alpha', descricao='Equipe de desenvolvimento frontend'}

3. Testando Projetos:
Projeto adicionado com sucesso!
Projeto{nome='Sistema Web', descricao='Desenvolvimento de sistema web corporativo', dataInicio='15/01/2024', dataTerminoPrevista='30/06/2024', status='em andamento'}

=== TESTE CONCLUÍDO ===
```

## 💾 Persistência de Dados

O sistema salva automaticamente os dados em arquivos:

- **usuarios.dat** - Dados dos usuários (formato binário)
- **equipes.json** - Dados das equipes (formato JSON)
- **projetos.json** - Dados dos projetos (formato JSON)

## 🏛️ Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)** com camada de repositório:

- **Models**: Classes que representam as entidades
- **Controllers**: Lógica de negócio e operações CRUD
- **Repository**: Camada de persistência e acesso aos dados
- **Main**: Classe principal para testes e demonstração

## 🔧 Funcionalidades por Controller

### UsuarioController
- `adicionarUsuario(Usuario)` - Adiciona novo usuário
- `listarUsuarios()` - Lista todos os usuários
- `atualizarUsuario(String, Usuario)` - Atualiza usuário pelo login
- `removerUsuario(String)` - Remove usuário pelo login

### EquipeController  
- `criarEquipe(String, String)` - Cria nova equipe
- `listarEquipes()` - Lista todas as equipes
- `atualizarEquipe(String, String, String)` - Atualiza equipe
- `deletarEquipe(String)` - Remove equipe

### ProjetoController
- `adicionarProjeto(Projeto)` - Adiciona novo projeto
- `listarProjetos()` - Lista todos os projetos
- `atualizarProjeto(String, Projeto)` - Atualiza projeto
- `removerProjeto(String)` - Remove projeto

## 🤝 Contribuições

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Projeto acadêmico desenvolvido para demonstrar:
- **Programação Orientada a Objetos** em Java
- **Padrão MVC** com camada de persistência
- **Interface Gráfica** com Java Swing
- **Persistência de dados** em arquivos
- **Operações CRUD** completas

**Diferencial**: Sistema com interface visual profissional usando Java Swing.

---

⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!