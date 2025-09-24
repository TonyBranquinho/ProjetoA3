package controller;

import java.util.List;
import modelos.Equipe;
import repository.EquipeRepository;

public class EquipeController {
    private EquipeRepository equipeRepository;
    
    public EquipeController() {
        this.equipeRepository = new EquipeRepository();
    }
    
    // CREATE
    public void criarEquipe(String nome, String descricao) {
        Equipe equipe = new Equipe(nome, descricao);
        
        // Carrega lista atual, adiciona nova equipe e salva
        List<Equipe> equipes = equipeRepository.carregarEquipes();
        equipes.add(equipe);
        equipeRepository.salvarEquipes(equipes);
        
        System.out.println("Equipe criada com sucesso!");
    }
    
    // READ (listar todas as equipes)
    public List<Equipe> listarEquipes() {
        return equipeRepository.carregarEquipes();
    }
    
    // UPDATE (alterar nome e descrição da equipe)
    public void atualizarEquipe(String nomeAntigo, String novoNome, String novaDescricao) {
        List<Equipe> equipes = equipeRepository.carregarEquipes();
        
        for (Equipe e : equipes) {
            if (e.getNomeEquipe().equalsIgnoreCase(nomeAntigo)) {
                e.setNomeEquipe(novoNome);
                e.setDescricao(novaDescricao);
                equipeRepository.salvarEquipes(equipes);
                System.out.println("Equipe atualizada com sucesso!");
                return;
            }
        }
        System.out.println("Equipe não encontrada.");
    }
    
    // DELETE
    public void deletarEquipe(String nomeEquipe) {
        List<Equipe> equipes = equipeRepository.carregarEquipes();
        
        boolean removeu = equipes.removeIf(e -> e.getNomeEquipe().equalsIgnoreCase(nomeEquipe));
        
        if (removeu) {
            equipeRepository.salvarEquipes(equipes);
            System.out.println("Equipe removida com sucesso!");
        } else {
            System.out.println("Equipe não encontrada.");
        }
    }
}