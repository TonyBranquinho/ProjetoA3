package view;

import controller.UsuarioController;
import modelos.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());
        
        JButton btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarUsuario();
            }
        });
        
        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarMenu();
            }
        });
        
        painel.add(btnAdicionar);
        painel.add(btnLimpar);
        painel.add(btnVoltar);
        
        return painel;
    }
    
    private void adicionarUsuario() {
        if (camposValidos()) {
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
    }
    
    private void carregarUsuarios() {
        modeloTabela.setRowCount(0);
        
        // Carrega usuários do arquivo via Repository
        repository.UsuarioRepository repo = new repository.UsuarioRepository();
        java.util.List<modelos.Usuario> usuarios = repo.listarTodoUsuarios();
        
        System.out.println(usuarios.size());
        
        for (modelos.Usuario usuario : usuarios) {
            modeloTabela.addRow(new Object[]{
                usuario.getNomeCompleto(),
                usuario.getCpf(),
                usuario.getEmail(),
                usuario.getCargo(),
                usuario.getLogin(),
                usuario.getPerfil()
            });
            
        }
        
    }
    
    private void voltarMenu() {
        janelaPai.setVisible(true);
        this.dispose();
    }
}