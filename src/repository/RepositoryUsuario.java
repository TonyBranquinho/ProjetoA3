package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelos.Usuario;

public class RepositoryUsuario {
	
	private List<Usuario> usuarios; // Lista em memória
	
	private final String arquivo = "usuarios.dat"; // Nome do arquivo para persistência
	
	// Construtor da classe
	public void UsuarioRepository() {
		this.usuarios = carregarUsuarios(); // Ao iniciar, carrega do arquivo se existir
	}
	
	// Adiciona um usuario
	public void salvar(Usuario usuario) {
		usuarios.add(usuario);
		salvarEmArquivo();
	}
	
	// Busca um usuario pelo CPF
	public Optional<Usuario> buscarPorCpf(String cpf) {
		
		// Usa strem para procurar um usuario com cpf igual ao fornecido
		return usuarios.stream()
			.filter(u -> u.getCpf().equals(cpf)) // Filtra pelo cpf
			.findFirst(); // Retorna o primeiro encontrado
	}
	
	// Busca todos os usuarios
	public List<Usuario> listarTodoUsuarios() {
		
		// Retorna uma copia da lista para evitar modificaçoes externas
		return new ArrayList<>(usuarios);
	}
	
	// Atualizar um usuário existente
    public boolean atualizar(Usuario usuarioAtualizado) {
        // Percorre a lista de usuários
        for (int i = 0; i < usuarios.size(); i++) {
            // Verifica se o CPF do usuário da lista é igual ao CPF do atualizado
            if (usuarios.get(i).getCpf().equals(usuarioAtualizado.getCpf())) {
                // Substitui o usuário antigo pelo atualizado
                usuarios.set(i, usuarioAtualizado);
                // Salva a lista modificada no arquivo
                salvarEmArquivo();
                // Retorna true, indicando que foi atualizado
                return true;
            }
        }
        // Se não encontrou nenhum usuário com o CPF, retorna false
        return false;
    }

    // Remover um usuário pelo CPF
    public boolean remover(String cpf) {
        // Remove da lista todos os usuários com o CPF fornecido
        boolean removido = usuarios.removeIf(u -> u.getCpf().equals(cpf));
        // Se algum usuário foi removido, atualiza o arquivo
        if (removido) salvarEmArquivo();
        // Retorna true se removeu, false caso contrário
        return removido;
    }

    // Método auxiliar para salvar a lista de usuários no arquivo
    private void salvarEmArquivo() {
        // Usa try-with-resources para garantir fechamento do arquivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            // Escreve o objeto (lista de usuários) no arquivo
            oos.writeObject(usuarios);
        } catch (IOException e) {
            // Em caso de erro, imprime a stacktrace
            e.printStackTrace();
        }
    }

    // Método auxiliar para carregar usuários do arquivo
    @SuppressWarnings("unchecked") // Ignora aviso de cast
    private List<Usuario> carregarUsuarios() {
        // Cria objeto representando o arquivo
        File f = new File(arquivo);

        // Se o arquivo não existe, retorna uma lista vazia
        if (!f.exists()) {
            return new ArrayList<>();
        }

        // Tenta abrir e ler os dados do arquivo
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            // Faz cast do objeto lido para lista de usuários
            return (List<Usuario>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // Se ocorrer erro de leitura ou de classe, imprime stacktrace
            e.printStackTrace();
            // Retorna uma lista vazia para evitar falhas
            return new ArrayList<>();
        }
    }
}






















































































