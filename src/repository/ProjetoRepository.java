package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.GsonBuilder;
import modelos.Projeto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ProjetoRepository {
    private static final String FILE_PATH = "projetos.json";
    private Gson gson;

    public ProjetoRepository() {
        // Configura o Gson para formatar o JSON de forma legível
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    // Carregar todos os projetos do arquivo JSON
    public List<Projeto> carregarProjetos() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<Projeto>>() {}.getType();
            List<Projeto> projetos = gson.fromJson(reader, listType);
            return (projetos != null) ? projetos : new ArrayList<>();
        } catch (IOException e) {
            return new ArrayList<>(); // Se o arquivo não existir ou erro, retorna lista vazia
        }
    }

    // Salvar lista de projetos no arquivo JSON
    public void salvarProjetos(List<Projeto> projetos) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(projetos, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar os projetos: " + e.getMessage());
        }
    }

    // Adicionar um projeto novo
    public void adicionarProjeto(Projeto projeto) {
        List<Projeto> projetos = carregarProjetos();
        projetos.add(projeto);
        salvarProjetos(projetos);
    }

    // Atualizar um projeto existente
    public boolean atualizarProjeto(String nome, Projeto novoProjeto) {
        List<Projeto> projetos = carregarProjetos();
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getNome().equalsIgnoreCase(nome)) {
                projetos.set(i, novoProjeto);
                salvarProjetos(projetos);
                return true;
            }
        }
        return false;
    }

    // Remover um projeto pelo nome
    public boolean removerProjeto(String nome) {
        List<Projeto> projetos = carregarProjetos();
        for (int i = 0; i < projetos.size(); i++) {
            if (projetos.get(i).getNome().equalsIgnoreCase(nome)) {
                projetos.remove(i);
                salvarProjetos(projetos);
                return true;
            }
        }
        return false;
    }
}