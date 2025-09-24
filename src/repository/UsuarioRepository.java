package repository;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import modelos.Usuario;

public class UsuarioRepository {
    
    private List<Usuario> usuarios; // Lista em memória
    
    private final String arquivo = "usuarios.json"; // Nome do arquivo para persistência
    
    private Gson gson = new Gson();
    
    // Construtor da classe
    public UsuarioRepository() {
        this.usuarios = carregarUsuarios(); // Ao iniciar, carrega do arquivo se existir
        System.out.println("=== DEBUG CONSTRUTOR ===");
        System.out.println("Usuarios carregados no construtor: " + usuarios.size());
        
        // Se não há usuários, vamos criar alguns de teste
        if (usuarios.isEmpty()) {
            System.out.println("Lista vazia, criando usuários de teste...");
            criarUsuariosIniciais();
        }
        
        // Debug: listar todos os usuários carregados
        System.out.println("Lista final de usuários:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println("  [" + i + "] " + u.getNomeCompleto() + " - " + u.getCpf());
        }
        System.out.println("========================");
    }
    
    // Método para criar usuários iniciais se a lista estiver vazia
    private void criarUsuariosIniciais() {
        usuarios.add(new Usuario("João Silva", "123.456.789-00", "joao.silva@email.com", 
                                "administrador", "joaosilva", "senha123", "administrador"));
        usuarios.add(new Usuario("Maria Oliveira", "234.567.890-11", "maria.oliveira@email.com", 
                                "gerente", "mariaoliveira", "senha456", "gerente"));
        usuarios.add(new Usuario("Carlos Pereira", "345.678.901-22", "carlos.pereira@email.com", 
                                "colaborador", "carlosp", "senha789", "colaborador"));
        
        System.out.println("Criados " + usuarios.size() + " usuários iniciais");
        salvarEmArquivo(); // Salva os usuários iniciais no arquivo
    }
    
    // Adiciona um usuario
    public void salvar(Usuario usuario) {
        System.out.println("=== SALVANDO USUARIO ===");
        System.out.println("Usuario a ser salvo: " + usuario.getNomeCompleto());
        System.out.println("Tamanho da lista antes: " + usuarios.size());
        
        usuarios.add(usuario);
        
        System.out.println("Tamanho da lista depois: " + usuarios.size());
        salvarEmArquivo();
        System.out.println("Usuario salvo com sucesso!");
        System.out.println("========================");
    }
    
    // Busca um usuario pelo CPF
    public Optional<Usuario> buscarPorCpf(String cpf) {
        System.out.println("=== BUSCANDO POR CPF ===");
        System.out.println("CPF procurado: " + cpf);
        System.out.println("Total de usuarios na lista: " + usuarios.size());
        
        // Usa stream para procurar um usuario com cpf igual ao fornecido
        Optional<Usuario> resultado = usuarios.stream()
            .filter(u -> u.getCpf().equals(cpf)) // Filtra pelo cpf
            .findFirst(); // Retorna o primeiro encontrado
            
        System.out.println("Usuario encontrado: " + (resultado.isPresent() ? resultado.get().getNomeCompleto() : "NÃO ENCONTRADO"));
        System.out.println("=======================");
        
        return resultado;
    }
    
    // Busca todos os usuarios
    public List<Usuario> listarTodoUsuarios() {
        System.out.println("=== LISTANDO TODOS OS USUARIOS ===");
        System.out.println("Total de usuarios na memória: " + usuarios.size());
        
        // Debug detalhado
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario u = usuarios.get(i);
            System.out.println("  Usuario[" + i + "]: " + u.getNomeCompleto() + " (" + u.getCpf() + ")");
        }
        
        List<Usuario> lista = new ArrayList<>(usuarios);
        System.out.println("Retornando lista com " + lista.size() + " usuarios");
        System.out.println("==================================");
        
        return lista;
    }
    
    // Atualizar um usuário existente
    public boolean atualizar(Usuario usuarioAtualizado) {
        System.out.println("=== ATUALIZANDO USUARIO ===");
        System.out.println("CPF do usuario a atualizar: " + usuarioAtualizado.getCpf());
        
        // Percorre a lista de usuários
        for (int i = 0; i < usuarios.size(); i++) {
            // Verifica se o CPF do usuário da lista é igual ao CPF do atualizado
            if (usuarios.get(i).getCpf().equals(usuarioAtualizado.getCpf())) {
                System.out.println("Usuario encontrado na posição " + i + ", atualizando...");
                // Substitui o usuário antigo pelo atualizado
                usuarios.set(i, usuarioAtualizado);
                // Salva a lista modificada no arquivo
                salvarEmArquivo();
                System.out.println("Usuario atualizado com sucesso!");
                System.out.println("===========================");
                // Retorna true, indicando que foi atualizado
                return true;
            }
        }
        // Se não encontrou nenhum usuário com o CPF, retorna false
        System.out.println("Usuario NÃO encontrado para atualização");
        System.out.println("===========================");
        return false;
    }

    // Remover um usuário pelo CPF
    public boolean remover(String cpf) {
        System.out.println("=== REMOVENDO USUARIO ===");
        System.out.println("CPF a remover: " + cpf);
        System.out.println("Tamanho antes: " + usuarios.size());
        
        // Remove da lista todos os usuários com o CPF fornecido
        boolean removido = usuarios.removeIf(u -> u.getCpf().equals(cpf));
        
        System.out.println("Tamanho depois: " + usuarios.size());
        System.out.println("Usuario removido: " + removido);
        
        // Se algum usuário foi removido, atualiza o arquivo
        if (removido) {
            salvarEmArquivo();
            System.out.println("Arquivo atualizado!");
        }
        System.out.println("=========================");
        // Retorna true se removeu, false caso contrário
        return removido;
    }

    // Método auxiliar para salvar a lista de usuários no arquivo
    private void salvarEmArquivo() {
        System.out.println("=== SALVANDO NO ARQUIVO ===");
        try (FileWriter writer = new FileWriter(arquivo)) {
            String json = gson.toJson(usuarios);
            writer.write(json);
            System.out.println("Arquivo salvo com sucesso!");
            System.out.println("Conteúdo salvo: " + json.substring(0, Math.min(100, json.length())) + "...");
        } catch (IOException e) {
            System.out.println("ERRO ao salvar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("===========================");
    }

    // Método auxiliar para carregar usuários do arquivo
    private List<Usuario> carregarUsuarios() {
        File f = new File(arquivo);
        System.out.println("=== CARREGANDO DO ARQUIVO ===");
        System.out.println("Tentando carregar arquivo: " + f.getAbsolutePath());
        System.out.println("Arquivo existe? " + f.exists());
        System.out.println("Arquivo pode ser lido? " + f.canRead());
        
        if (f.exists()) {
            System.out.println("Tamanho do arquivo: " + f.length() + " bytes");
        }

        // Se o arquivo não existe, retorna uma lista vazia
        if (!f.exists()) {
            System.out.println("Arquivo não existe, retornando lista vazia");
            System.out.println("=============================");
            return new ArrayList<>();
        }

        // Tenta abrir e ler os dados do arquivo JSON
        try (FileReader reader = new FileReader(f)) {
            List<Usuario> usuariosCarregados = gson.fromJson(reader, new TypeToken<List<Usuario>>(){}.getType());
            System.out.println("Usuarios carregados do JSON: " + (usuariosCarregados != null ? usuariosCarregados.size() : 0));
            
            if (usuariosCarregados != null) {
                System.out.println("Listando usuarios carregados:");
                for (int i = 0; i < usuariosCarregados.size(); i++) {
                    Usuario u = usuariosCarregados.get(i);
                    System.out.println("  [" + i + "] " + u.getNomeCompleto() + " - " + u.getCpf());
                }
            }
            
            System.out.println("=============================");
            return usuariosCarregados != null ? usuariosCarregados : new ArrayList<>();
        } catch (IOException e) {
            System.out.println("ERRO ao carregar arquivo: " + e.getMessage());
            e.printStackTrace();
            System.out.println("=============================");
            return new ArrayList<>();
        }
    }
}