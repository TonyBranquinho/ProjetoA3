package controller;

import java.util.ArrayList;
import java.util.List;

import modelos.Usuario;

public class UsuarioController {

	private List<Usuario> usuarios = new ArrayList<>();

	// CREATE: adicionar novo usuário
	public void adicionarUsuario(Usuario usuario) {
		usuarios.add(usuario);
		System.out.println("Usuário adicionado com sucesso!");
	}

	// READ: listar todos os usuários
	public void listarUsuarios() {
		if (usuarios.isEmpty()) {
			System.out.println("Nenhum usuário cadastrado.");
		} else {
			for (Usuario u : usuarios) {
				System.out.println(u);
			}
		}
	}

	// UPDATE: atualizar dados de um usuário pelo login
	public void atualizarUsuario(String login, Usuario usuarioAtualizado) {
		for (Usuario u : usuarios) {
			if (u.getLogin().equals(login)) {
				u.setNomeCompleto(usuarioAtualizado.getNomeCompleto());
				u.setCpf(usuarioAtualizado.getCpf());
				u.setEmail(usuarioAtualizado.getEmail());
				u.setCargo(usuarioAtualizado.getCargo());
				u.setSenha(usuarioAtualizado.getSenha());
				u.setPerfil(usuarioAtualizado.getPerfil());
				System.out.println("Usuário atualizado com sucesso!");
				return;
			}
		}
		System.out.println("Usuário não encontrado.");
	}

	// DELETE: remover usuário pelo login
	public void removerUsuario(String login) {
		Usuario usuarioRemover = null;
		for (Usuario u : usuarios) {
			if (u.getLogin().equals(login)) {
				usuarioRemover = u;
				break;
			}
		}
		if (usuarioRemover != null) {
			usuarios.remove(usuarioRemover);
			System.out.println("Usuário removido com sucesso!");
		} else {
			System.out.println("Usuário não encontrado.");
		}
	}
}
