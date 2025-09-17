package controller;

import java.util.ArrayList;
import java.util.List;

import modelos.Equipe;

public class EquipeController {
	private List<Equipe> equipes = new ArrayList<>();

	// CREATE
	public void criarEquipe(String nome, String descricao) {
		Equipe equipe = new Equipe(nome, descricao);
		equipes.add(equipe);
		System.out.println("Equipe criada com sucesso!");
	}

	// READ (listar todas as equipes)
	public void listarEquipes() {
		if (equipes.isEmpty()) {
			System.out.println("Nenhuma equipe cadastrada.");
		} else {
			for (Equipe e : equipes) {
				System.out.println(e);
				System.out.println("-------------------------");
			}
		}
	}

	// UPDATE (alterar nome e descrição da equipe)
	public void atualizarEquipe(String nomeAntigo, String novoNome, String novaDescricao) {
		for (Equipe e : equipes) {
			if (e.getNomeEquipe().equalsIgnoreCase(nomeAntigo)) {
				e.setNomeEquipe(novoNome);
				e.setDescricao(novaDescricao);
				System.out.println("Equipe atualizada com sucesso!");
				return;
			}
		}
		System.out.println("Equipe não encontrada.");
	}

	// DELETE
	public void deletarEquipe(String nomeEquipe) {
		Equipe equipeRemover = null;
		for (Equipe e : equipes) {
			if (e.getNomeEquipe().equalsIgnoreCase(nomeEquipe)) {
				equipeRemover = e;
				break;
			}
		}
		if (equipeRemover != null) {
			equipes.remove(equipeRemover);
			System.out.println("Equipe removida com sucesso!");
		} else {
			System.out.println("Equipe não encontrada.");
		}
	}
}
