package view;

import controller.UsuarioController;
import modelos.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Optional;

public class UsuarioWindow extends JFrame {
    
    private JFrame janelaPai;
    private UsuarioController usuarioController;
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    
    // Campos do formulário
    private JTextField txtNome, txtCpf, txtEmail, txtCargo, txtLogin, txtSenha, txtPerfil;
    
    public UsuarioWindow(JFrame pai) {
        this.janelaPai = pai;
        this.usuarioController = new UsuarioController();
        inicializarComponentes();
        carregarUsuarios();
    }
    
    private void inicializarComponentes() {
        setTitle("Gerenciamento de Usuários");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Painel superior - Formulário
        JPanel painelFormulario = criarPainelFormulario();
        add(painelFormulario, BorderLayout.NORTH);
        
        // Painel central - Tabela
        JPanel painelTabela = criarPainelTabela();
        add(painelTabela, BorderLayout.CENTER);
        
        // Painel inferior - Botões
        JPanel painelBotoes = criarPainelBotoes();
        add(painelBotoes, BorderLayout.SOUTH);
        
        // Evento para fechar janela
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                voltarMenu();
            }
        });
    }
    
    private JPanel criarPainelFormulario() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de Usuário"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Primeira linha
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(15);
        painel.add(txtNome, gbc);
        
        gbc.gridx = 2;
        painel.add(new JLabel("CPF:"), gbc);
        gbc.gridx = 3;
        txtCpf = new JTextField(15);
        painel.add(txtCpf, gbc);
        
        // Segunda linha
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        txtEmail = new JTextField(15);
        painel.add(txtEmail, gbc);
        
        gbc.gridx = 2;
        painel.add(new JLabel("Cargo:"), gbc);
        gbc.gridx = 3;
        txtCargo = new JTextField(15);
        painel.add(txtCargo, gbc);
        
        // Terceira linha
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Login:"), gbc);
        gbc.gridx = 1;
        txtLogin = new JTextField(15);
        painel.add(txtLogin, gbc);
        
        gbc.gridx = 2;
        painel.add(new JLabel("Senha:"), gbc);
        gbc.gridx = 3;
        txtSenha = new JPasswordField(15);
        painel.add(txtSenha, gbc);
        
        // Quarta linha
        gbc.gridx = 0; gbc.gridy = 3;
        painel.add(new JLabel("Perfil:"), gbc);
        gbc.gridx = 1;
        txtPerfil = new JTextField(15);
        painel.add(txtPerfil, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Usuários"));
        
        // Modelo da tabela
        String[] colunas = {"Nome", "CPF", "Email", "Cargo", "Login", "Perfil"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Não permite edição direta na tabela
            }
        };
        
        tabelaUsuarios = new JTable(modeloTabela);
        tabelaUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Event listener para seleção da tabela
        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tabelaUsuarios.getSelectedRow();
                if (selectedRow != -1) {
                    carregarUsuarioSelecionado(selectedRow);
                }
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> adicionarUsuario());
        
        JButton btnAtualizar = new JButton("Atualizar");
        btnAtualizar.addActionListener(e -> atualizarUsuario());
        
        JButton btnRemover = new JButton("Remover");
        btnRemover.addActionListener(e -> removerUsuario());
        
        JButton btnListar = new JButton("Listar Todos");
        btnListar.addActionListener(e -> carregarUsuarios());
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> limparCampos());
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> voltarMenu());
        
        painel.add(btnAdicionar);
        painel.add(btnAtualizar);
        painel.add(btnRemover);
        painel.add(btnListar);
        painel.add(btnLimpar);
        painel.add(btnVoltar);
        
        return painel;
    }
    
    private void carregarUsuarioSelecionado(int row) {
        String cpf = (String) modeloTabela.getValueAt(row, 1);
        Optional<Usuario> usuarioOpt = usuarioController.buscarPorCpf(cpf);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            txtNome.setText(usuario.getNomeCompleto());
            txtCpf.setText(usuario.getCpf());
            txtEmail.setText(usuario.getEmail());
            txtCargo.setText(usuario.getCargo());
            txtLogin.setText(usuario.getLogin());
            txtSenha.setText(usuario.getSenha());
            txtPerfil.setText(usuario.getPerfil());
        }
    }
    
    private void adicionarUsuario() {
        if (camposValidos()) {
            // Verificar se CPF já existe
            Optional<Usuario> usuarioExistente = usuarioController.buscarPorCpf(txtCpf.getText());
            if (usuarioExistente.isPresent()) {
                JOptionPane.showMessageDialog(this, "Já existe um usuário com este CPF!");
                return;
            }
            
            Usuario usuario = new Usuario(
                txtNome.getText(),
                txtCpf.getText(),
                txtEmail.getText(),
                txtCargo.getText(),
                txtLogin.getText(),
                txtSenha.getText(),
                txtPerfil.getText()
            );
            
            usuarioController.adicionarUsuario(usuario);
            carregarUsuarios();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Usuário adicionado com sucesso!");
        }
    }
    
    private void atualizarUsuario() {
        if (camposValidos()) {
            Usuario usuarioAtualizado = new Usuario(
                txtNome.getText(),
                txtCpf.getText(),
                txtEmail.getText(),
                txtCargo.getText(),
                txtLogin.getText(),
                txtSenha.getText(),
                txtPerfil.getText()
            );
            
            if (usuarioController.atualizarUsuario(usuarioAtualizado)) {
                carregarUsuarios();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado para atualização!");
            }
        }
    }
    
    private void removerUsuario() {
        String cpf = txtCpf.getText();
        if (cpf.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o CPF do usuário a ser removido!");
            return;
        }
        
        int opcao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja remover o usuário com CPF: " + cpf + "?",
            "Confirmar Remoção",
            JOptionPane.YES_NO_OPTION);
            
        if (opcao == JOptionPane.YES_OPTION) {
            if (usuarioController.removerUsuario(cpf)) {
                carregarUsuarios();
                limparCampos();
                JOptionPane.showMessageDialog(this, "Usuário removido com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado!");
            }
        }
    }
    
    private boolean camposValidos() {
        if (txtNome.getText().trim().isEmpty() ||
            txtCpf.getText().trim().isEmpty() ||
            txtEmail.getText().trim().isEmpty() ||
            txtLogin.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!");
            return false;
        }
        return true;
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtCargo.setText("");
        txtLogin.setText("");
        txtSenha.setText("");
        txtPerfil.setText("");
        tabelaUsuarios.clearSelection();
    }
    
 // Adicione este método na sua classe UsuarioWindow, substituindo o método carregarUsuarios() existente

    private void carregarUsuarios() {
        System.out.println("=== CARREGANDO USUARIOS NA INTERFACE ===");
        
        // Limpar tabela
        modeloTabela.setRowCount(0);
        System.out.println("Tabela limpa. Linhas na tabela: " + modeloTabela.getRowCount());
        
        // Buscar usuários do controller
        java.util.List<Usuario> usuarios = usuarioController.listarUsuarios();
        System.out.println("Usuarios retornados do controller: " + (usuarios != null ? usuarios.size() : "NULL"));
        
        if (usuarios == null) {
            System.out.println("ERRO: Lista de usuários é NULL!");
            JOptionPane.showMessageDialog(this, "Erro: Lista de usuários não pôde ser carregada!");
            return;
        }
        
        if (usuarios.isEmpty()) {
            System.out.println("AVISO: Lista de usuários está vazia!");
            JOptionPane.showMessageDialog(this, "Nenhum usuário encontrado na base de dados!");
            return;
        }
        
        // Adicionar usuários à tabela
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            System.out.println("Adicionando usuario[" + i + "]: " + usuario.getNomeCompleto());
            
            Object[] row = new Object[]{
                usuario.getNomeCompleto(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getCargo(),
                usuario.getLogin(),
                usuario.getPerfil()
            };
            
            modeloTabela.addRow(row);
            System.out.println("Linha adicionada. Total de linhas na tabela: " + modeloTabela.getRowCount());
        }
        
        System.out.println("Carregamento concluído. Linhas finais na tabela: " + modeloTabela.getRowCount());
        
        // Forçar atualização da tabela
        tabelaUsuarios.revalidate();
        tabelaUsuarios.repaint();
        
        System.out.println("========================================");
    }
    
    private void voltarMenu() {
        janelaPai.setVisible(true);
        this.dispose();
    }
}