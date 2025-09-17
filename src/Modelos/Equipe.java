package modelos;

import java.util.ArrayList;
import java.util.List;

public class Equipe {

	private String nomeEquipe;
	private String descricao;
	private List<String> membros; // Lista de usuários vinculados
	private List<String> projetos; // Projetos em que a equipe atua

	public Equipe(String nomeEquipe, String descricao) {
		this.nomeEquipe = nomeEquipe;
		this.descricao = descricao;
		this.membros = new ArrayList<>();
		this.projetos = new ArrayList<>();
	}

	// Getters e Setters
	public String getNomeEquipe() {
		return nomeEquipe;
	}

	public void setNomeEquipe(String nomeEquipe) {
		this.nomeEquipe = nomeEquipe;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<String> getMembros() {
		return membros;
	}

	public void adicionarMembro(String membro) {
		this.membros.add(membro);
	}

	public void removerMembro(String membro) {
		this.membros.remove(membro);
	}

	public List<String> getProjetos() {
		return projetos;
	}

	public void adicionarProjeto(String projeto) {
		this.projetos.add(projeto);
	}

	public void removerProjeto(String projeto) {
		this.projetos.remove(projeto);
	}

	@Override
	public String toString() {
		return "Equipe: " + nomeEquipe + "\nDescrição: " + descricao + "\nMembros: " + membros + "\nProjetos: "
				+ projetos;
	}
}