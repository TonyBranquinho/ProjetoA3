package view;

import controller.EquipeController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipeWindow extends JFrame {
    
    private JFrame janelaPai;
    private EquipeController equipeController;
    private JTable tabelaEquipes;
    private DefaultTableModel modeloTabela;
    
    // Campos do formulário
    private JTextField txtNome, txtDescricao;
    
    public EquipeWindow(JFrame pai) {
        this.janelaPai = pai;
        this.equipeController = new EquipeController();
        inicializarComponentes();
        carregarEquipes();
    }
    
    private void inicializarComponentes() {
        setTitle("Gerenciamento de Equipes");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(800, 600);
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
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de Equipe"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        
        // Nome da equipe
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nome da Equipe:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(20);
        painel.add(txtNome, gbc);
        
        // Descrição
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1;
        txtDescricao = new JTextField(20);
        painel.add(txtDescricao, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Equipes"));
        
        // Modelo da tabela
        String[] colunas = {"Nome da Equipe", "Descrição"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaEquipes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaEquipes);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());
        
        JButton btnCriar = new JButton("Criar Equipe");
        btnCriar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarEquipe();
            }
        });
        
        JButton btnAtualizar = new JButton("Atualizar Equipe");
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarEquipe();
            }
        });
        
        JButton btnDeletar = new JButton("Deletar Equipe");
        btnDeletar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarEquipe();
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
        
        painel.add(btnCriar);
        painel.add(btnAtualizar);
        painel.add(btnDeletar);
        painel.add(btnLimpar);
        painel.add(btnVoltar);
        
        return painel;
    }
    
    private void criarEquipe() {
        if (camposValidos()) {
            equipeController.criarEquipe(txtNome.getText(), txtDescricao.getText());
            carregarEquipes();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Equipe criada com sucesso!");
        }
    }
    
    private void atualizarEquipe() {
        int linhaSelecionada = tabelaEquipes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe na tabela para atualizar!");
            return;
        }
        
        if (camposValidos()) {
            String nomeAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
            equipeController.atualizarEquipe(nomeAntigo, txtNome.getText(), txtDescricao.getText());
            carregarEquipes();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Equipe atualizada com sucesso!");
        }
    }
    
    private void deletarEquipe() {
        int linhaSelecionada = tabelaEquipes.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione uma equipe na tabela para deletar!");
            return;
        }
        
        String nomeEquipe = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja deletar a equipe '" + nomeEquipe + "'?", 
            "Confirmar Exclusão", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            equipeController.deletarEquipe(nomeEquipe);
            carregarEquipes();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Equipe deletada com sucesso!");
        }
    }
    
    private boolean camposValidos() {
        if (txtNome.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "O nome da equipe é obrigatório!");
            return false;
        }
        return true;
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
        tabelaEquipes.clearSelection();
    }
    
    private void carregarEquipes() {
        modeloTabela.setRowCount(0);
        
        // Carrega equipes do arquivo JSON via Repository
        repository.EquipeRepository repo = new repository.EquipeRepository();
        java.util.List<modelos.Equipe> equipes = repo.carregarEquipes();
        
        for (modelos.Equipe equipe : equipes) {
            modeloTabela.addRow(new Object[]{
                equipe.getNomeEquipe(), 
                equipe.getDescricao()
            });
        }
    }
    
    private void voltarMenu() {
        janelaPai.setVisible(true);
        this.dispose();
    }
}