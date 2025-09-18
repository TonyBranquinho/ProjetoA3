package main;

import java.util.Scanner;
import controller.EquipeController;
import controller.ProjetoController;
import controller.UsuarioController;
import modelos.Usuario;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Testando funcionalidades básicas
        System.out.println("=== TESTE DO SISTEMA ===\n");
        
        // Teste Usuários
        System.out.println("1. Testando Usuários:");
        UsuarioController usuarioController = new UsuarioController();
        
        Usuario usuario1 = new Usuario("João Silva", "123.456.789-00", "joao@email.com", 
                                      "Desenvolvedor", "joao123", "senha123", "Admin");
        
        usuarioController.adicionarUsuario(usuario1);
        usuarioController.listarUsuarios();
        
        // Teste Equipes
        System.out.println("\n2. Testando Equipes:");
        EquipeController equipeController = new EquipeController();
        
        equipeController.criarEquipe("Equipe Alpha", "Equipe de desenvolvimento frontend");
        equipeController.listarEquipes();
        
        // Teste Projetos
        System.out.println("\n3. Testando Projetos:");
        ProjetoController projetoController = new ProjetoController();
        
        modelos.Projeto projeto1 = new modelos.Projeto("Sistema Web", "Desenvolvimento de sistema web corporativo",
                                                       "15/01/2024", "30/06/2024", "em andamento");
        
        projetoController.adicionarProjeto(projeto1);
        projetoController.listarProjetos();
        
        System.out.println("\n=== TESTE CONCLUÍDO ===");
        scanner.close();
    }
}