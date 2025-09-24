// Crie esta classe para testar se o sistema está funcionando
// TestUsuarioSystem.java

import controller.UsuarioController;
import modelos.Usuario;
import java.util.List;

public class TestUsuarioSystem {
    public static void main(String[] args) {
        System.out.println("=== TESTE DO SISTEMA DE USUARIOS ===");
        
        // Criar controller
        UsuarioController controller = new UsuarioController();
        
        // Testar listagem inicial
        System.out.println("\n1. Testando listagem inicial:");
        List<Usuario> usuarios = controller.listarUsuarios();
        System.out.println("Total de usuários: " + usuarios.size());
        
        // Testar adição de usuário
        System.out.println("\n2. Testando adição de usuário:");
        Usuario novoUsuario = new Usuario(
            "Teste Silva", 
            "999.999.999-99", 
            "teste@email.com",
            "colaborador", 
            "teste", 
            "123", 
            "colaborador"
        );
        
        controller.adicionarUsuario(novoUsuario);
        
        // Testar listagem após adição
        System.out.println("\n3. Testando listagem após adição:");
        usuarios = controller.listarUsuarios();
        System.out.println("Total de usuários após adição: " + usuarios.size());
        
        // Listar todos os usuários
        System.out.println("\n4. Lista completa de usuários:");
        for (Usuario u : usuarios) {
            System.out.println("  - " + u.getNomeCompleto() + " (" + u.getCpf() + ")");
        }
        
        // Testar busca por CPF
        System.out.println("\n5. Testando busca por CPF:");
        var resultado = controller.buscarPorCpf("999.999.999-99");
        System.out.println("Usuário encontrado: " + (resultado.isPresent() ? resultado.get().getNomeCompleto() : "NÃO"));
        
        System.out.println("\n=== TESTE CONCLUIDO ===");
    }
}