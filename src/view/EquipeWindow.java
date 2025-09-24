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
    
 // Substitua o método carregarEquipes() na sua EquipeWindow por este:

    private void carregarEquipes() {
        System.out.println("=== CARREGANDO EQUIPES NA INTERFACE ===");
        
        // Limpar tabela
        modeloTabela.setRowCount(0);
        System.out.println("Tabela limpa. Linhas na tabela: " + modeloTabela.getRowCount());
        
        // Buscar equipes do controller
        java.util.List<modelos.Equipe> equipes = equipeController.listarEquipes();
        System.out.println("Equipes retornadas do controller: " + (equipes != null ? equipes.size() : "NULL"));
        
        if (equipes == null) {
            System.out.println("ERRO: Lista de equipes é NULL!");
            JOptionPane.showMessageDialog(this, "Erro: Lista de equipes não pôde ser carregada!");
            return;
        }
        
        if (equipes.isEmpty()) {
            System.out.println("AVISO: Lista de equipes está vazia!");
            JOptionPane.showMessageDialog(this, "Nenhuma equipe encontrada na base de dados!\nUse o botão 'Criar Equipe' para adicionar equipes.");
            return;
        }
        
        // Adicionar equipes à tabela
        for (int i = 0; i < equipes.size(); i++) {
            modelos.Equipe equipe = equipes.get(i);
            System.out.println("Adicionando equipe[" + i + "]: " + equipe.getNomeEquipe());
            
            Object[] row = new Object[]{
                equipe.getNomeEquipe(),
                equipe.getDescricao()
            };
            
            modeloTabela.addRow(row);
            System.out.println("Linha adicionada. Total de linhas na tabela: " + modeloTabela.getRowCount());
        }
        
        System.out.println("Carregamento concluído. Linhas finais na tabela: " + modeloTabela.getRowCount());
        
        // Forçar atualização da tabela
        tabelaEquipes.revalidate();
        tabelaEquipes.repaint();
        
        System.out.println("========================================");
    }
    
    private void voltarMenu() {
        janelaPai.setVisible(true);
        this.dispose();
    }
}