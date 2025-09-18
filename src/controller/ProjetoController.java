package controller;

import java.util.ArrayList;
import java.util.List;

import modelos.Projeto;

public class ProjetoController {
	private List<Projeto> listaProjetos = new ArrayList<>();

	// CREATE
	public void adicionarProjeto(Projeto projeto) {
		listaProjetos.add(projeto);
		System.out.println("Projeto adicionado com sucesso!");
	}

	// READ (listar todos)
	public void listarProjetos() {
		if (listaProjetos.isEmpty()) {
			System.out.println("Nenhum projeto cadastrado.");
		} else {
			for (Projeto p : listaProjetos) {
				System.out.println(p);
			}
		}
	}

	// UPDATE
	public void atualizarProjeto(String nome, Projeto novoProjeto) {
		for (int i = 0; i < listaProjetos.size(); i++) {
			if (listaProjetos.get(i).getNome().equalsIgnoreCase(nome)) {
				listaProjetos.set(i, novoProjeto);
				System.out.println("Projeto atualizado com sucesso!");
				return;
			}
		}
		System.out.println("Projeto não encontrado.");
	}

	// DELETE
	public void removerProjeto(String nome) {
		for (int i = 0; i < listaProjetos.size(); i++) {
			if (listaProjetos.get(i).getNome().equalsIgnoreCase(nome)) {
				listaProjetos.remove(i);
				System.out.println("Projeto removido com sucesso!");
				return;
			}
		}
		System.out.println("Projeto não encontrado.");
	}
}
