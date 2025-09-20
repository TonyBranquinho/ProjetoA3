package view;

import controller.ProjetoController;
import modelos.Projeto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjetoWindow extends JFrame {
    
    private JFrame janelaPai;
    private ProjetoController projetoController;
    private JTable tabelaProjetos;
    private DefaultTableModel modeloTabela;
    
    // Campos do formulário
    private JTextField txtNome, txtDescricao, txtDataInicio, txtDataTermino, txtStatus;
    
    public ProjetoWindow(JFrame pai) {
        this.janelaPai = pai;
        this.projetoController = new ProjetoController();
        inicializarComponentes();
        carregarProjetos();
    }
    
    private void inicializarComponentes() {
        setTitle("Gerenciamento de Projetos");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1200, 700);
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
        painel.setBorder(BorderFactory.createTitledBorder("Cadastro de Projeto"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Primeira linha
        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        txtNome = new JTextField(15);
        painel.add(txtNome, gbc);
        
        gbc.gridx = 2;
        painel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 3;
        txtStatus = new JTextField(15);
        painel.add(txtStatus, gbc);
        
        // Segunda linha
        gbc.gridx = 0; gbc.gridy = 1;
        painel.add(new JLabel("Descrição:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 3;
        txtDescricao = new JTextField(50);
        painel.add(txtDescricao, gbc);
        
        // Terceira linha
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 2;
        painel.add(new JLabel("Data Início:"), gbc);
        gbc.gridx = 1;
        txtDataInicio = new JTextField(15);
        painel.add(txtDataInicio, gbc);
        
        gbc.gridx = 2;
        painel.add(new JLabel("Data Término:"), gbc);
        gbc.gridx = 3;
        txtDataTermino = new JTextField(15);
        painel.add(txtDataTermino, gbc);
        
        // Dica de formato
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 4;
        JLabel dica = new JLabel("* Formato das datas: dd/MM/yyyy (exemplo: 25/12/2023)");
        dica.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 11));
        dica.setForeground(Color.GRAY);
        painel.add(dica, gbc);
        
        return painel;
    }
    
    private JPanel criarPainelTabela() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Lista de Projetos"));
        
        // Modelo da tabela
        String[] colunas = {"Nome", "Descrição", "Data Início", "Data Término", "Status"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tabelaProjetos = new JTable(modeloTabela);
        
        // Ajustar larguras das colunas
        tabelaProjetos.getColumnModel().getColumn(0).setPreferredWidth(150); // Nome
        tabelaProjetos.getColumnModel().getColumn(1).setPreferredWidth(300); // Descrição
        tabelaProjetos.getColumnModel().getColumn(2).setPreferredWidth(100); // Data Início
        tabelaProjetos.getColumnModel().getColumn(3).setPreferredWidth(100); // Data Término
        tabelaProjetos.getColumnModel().getColumn(4).setPreferredWidth(100); // Status
        
        JScrollPane scrollPane = new JScrollPane(tabelaProjetos);
        painel.add(scrollPane, BorderLayout.CENTER);
        
        return painel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout());
        
        JButton btnAdicionar = new JButton("Adicionar Projeto");
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarProjeto();
            }
        });
        
        JButton btnAtualizar = new JButton("Atualizar Projeto");
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarProjeto();
            }
        });
        
        JButton btnRemover = new JButton("Remover Projeto");
        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerProjeto();
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
        painel.add(btnAtualizar);
        painel.add(btnRemover);
        painel.add(btnLimpar);
        painel.add(btnVoltar);
        
        return painel;
    }
    
    private void adicionarProjeto() {
        if (camposValidos()) {
            Projeto projeto = new Projeto(
                txtNome.getText(),
                txtDescricao.getText(),
                txtDataInicio.getText(),
                txtDataTermino.getText(),
                txtStatus.getText()
            );
            
            projetoController.adicionarProjeto(projeto);
            carregarProjetos();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Projeto adicionado com sucesso!");
        }
    }
    
    private void atualizarProjeto() {
        int linhaSelecionada = tabelaProjetos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto na tabela para atualizar!");
            return;
        }
        
        if (camposValidos()) {
            String nomeAntigo = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
            
            Projeto novoProjeto = new Projeto(
                txtNome.getText(),
                txtDescricao.getText(),
                txtDataInicio.getText(),
                txtDataTermino.getText(),
                txtStatus.getText()
            );
            
            projetoController.atualizarProjeto(nomeAntigo, novoProjeto);
            carregarProjetos();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Projeto atualizado com sucesso!");
        }
    }
    
    private void removerProjeto() {
        int linhaSelecionada = tabelaProjetos.getSelectedRow();
        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um projeto na tabela para remover!");
            return;
        }
        
        String nomeProjeto = (String) modeloTabela.getValueAt(linhaSelecionada, 0);
        int confirmacao = JOptionPane.showConfirmDialog(this, 
            "Tem certeza que deseja remover o projeto '" + nomeProjeto + "'?", 
            "Confirmar Remoção", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacao == JOptionPane.YES_OPTION) {
            projetoController.removerProjeto(nomeProjeto);
            carregarProjetos();
            limparCampos();
            
            JOptionPane.showMessageDialog(this, "Projeto removido com sucesso!");
        }
    }
    
    private boolean camposValidos() {
        if (txtNome.getText().trim().isEmpty() ||
            txtDataInicio.getText().trim().isEmpty() ||
            txtDataTermino.getText().trim().isEmpty()) {
            
            JOptionPane.showMessageDialog(this, "Preencha todos os campos obrigatórios!");
            return false;
        }
        return true;
    }
    
    private void limparCampos() {
        txtNome.setText("");
        txtDescricao.setText("");
        txtDataInicio.setText("");
        txtDataTermino.setText("");
        txtStatus.setText("");
        tabelaProjetos.clearSelection();
    }
    
    private void carregarProjetos() {
        modeloTabela.setRowCount(0);
        
        // Carrega projetos do arquivo JSON via Repository
        repository.ProjetoRepository repo = new repository.ProjetoRepository();
        java.util.List<modelos.Projeto> projetos = repo.carregarProjetos();
        
        for (modelos.Projeto projeto : projetos) {
            modeloTabela.addRow(new Object[]{
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataTerminoPrevista(),
                projeto.getStatus()
            });
        }
    }
    
    private void voltarMenu() {
        janelaPai.setVisible(true);
        this.dispose();
    }
}