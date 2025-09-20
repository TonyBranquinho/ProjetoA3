package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {

	public MainWindow() {
		inicializarComponentes();

	}

	private void inicializarComponentes() {
		// Configurações da janela
		setTitle("Sistema de Gestão");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setLocationRelativeTo(null); // Centraliza na tela

		// Layout principal
		setLayout(new BorderLayout());

		// Título
		JLabel titulo = new JLabel("Sistema de Gestão", SwingConstants.CENTER);
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		add(titulo, BorderLayout.NORTH);

		// Painel central com botões
		JPanel painelBotoes = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		// Botão Usuários
		JButton btnUsuarios = new JButton("Gerenciar Usuários");
		btnUsuarios.setPreferredSize(new Dimension(200, 50));
		btnUsuarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirJanelaUsuarios();
			}
		});

		// Botão Equipes
		JButton btnEquipes = new JButton("Gerenciar Equipes");
		btnEquipes.setPreferredSize(new Dimension(200, 50));
		btnEquipes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirJanelaEquipes();
			}
		});

		// Botão Projetos
		JButton btnProjetos = new JButton("Gerenciar Projetos");
		btnProjetos.setPreferredSize(new Dimension(200, 50));
		btnProjetos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirJanelaProjetos();
			}
		});

		// Botão Sair
		JButton btnSair = new JButton("Sair");
		btnSair.setPreferredSize(new Dimension(200, 50));
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		// Adicionar botões ao painel
		gbc.gridx = 0;
		gbc.gridy = 0;
		painelBotoes.add(btnUsuarios, gbc);

		gbc.gridy = 1;
		painelBotoes.add(btnEquipes, gbc);

		gbc.gridy = 2;
		painelBotoes.add(btnProjetos, gbc);

		gbc.gridy = 3;
		painelBotoes.add(btnSair, gbc);

		add(painelBotoes, BorderLayout.CENTER);

		// Rodapé
		JLabel rodape = new JLabel("Sistema desenvolvido em Java Swing", SwingConstants.CENTER);
		rodape.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		add(rodape, BorderLayout.SOUTH);
	}

	private void abrirJanelaUsuarios() {
		UsuarioWindow janelaUsuarios = new UsuarioWindow(this);
		janelaUsuarios.setVisible(true);
		this.setVisible(false);
	}

	private void abrirJanelaEquipes() {
		EquipeWindow janelaEquipes = new EquipeWindow(this);
		janelaEquipes.setVisible(true);
		this.setVisible(false);
	}

	private void abrirJanelaProjetos() {
		ProjetoWindow janelaProjetos = new ProjetoWindow(this);
		janelaProjetos.setVisible(true);
		this.setVisible(false);
	}
}
