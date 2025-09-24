package controller;

import java.util.List;
import modelos.Projeto;
import repository.ProjetoRepository;

public class ProjetoController {
    private ProjetoRepository projetoRepository;
    
    public ProjetoController() {
        this.projetoRepository = new ProjetoRepository();
    }
    
    // CREATE
    public void adicionarProjeto(Projeto projeto) {
        projetoRepository.adicionarProjeto(projeto);
        System.out.println("Projeto adicionado com sucesso!");
    }
    
    // READ (listar todos)
    public List<Projeto> listarProjetos() {
        return projetoRepository.carregarProjetos();
    }
    
    // UPDATE
    public void atualizarProjeto(String nome, Projeto novoProjeto) {
        boolean sucesso = projetoRepository.atualizarProjeto(nome, novoProjeto);
        
        if (sucesso) {
            System.out.println("Projeto atualizado com sucesso!");
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
    
    // DELETE
    public void removerProjeto(String nome) {
        boolean sucesso = projetoRepository.removerProjeto(nome);
        
        if (sucesso) {
            System.out.println("Projeto removido com sucesso!");
        } else {
            System.out.println("Projeto não encontrado.");
        }
    }
}