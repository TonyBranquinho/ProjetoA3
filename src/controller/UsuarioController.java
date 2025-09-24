package controller;

import java.util.List;
import java.util.Optional;

import modelos.Usuario;
import repository.UsuarioRepository;

public class UsuarioController {
	private UsuarioRepository usuarioRepository;

	public UsuarioController() {
		this.usuarioRepository = new UsuarioRepository();
	}

	// CREATE: adicionar novo usuário
	public void adicionarUsuario(Usuario usuario) {
		usuarioRepository.salvar(usuario);
		System.out.println("Usuário adicionado com sucesso!");
	}

	// READ: listar todos os usuários
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.listarTodoUsuarios();
	}

	// READ: buscar usuário por CPF
    public Optional<Usuario> buscarPorCpf(String cpf) {
        return usuarioRepository.buscarPorCpf(cpf);
    }
    
	// UPDATE: atualizar dados de um usuário pelo login
    public boolean atualizarUsuario(Usuario usuarioAtualizado) {
        return usuarioRepository.atualizar(usuarioAtualizado);
    }

	// DELETE: remover usuário pelo login
    public boolean removerUsuario(String cpf) {
        return usuarioRepository.remover(cpf);
    }
}
