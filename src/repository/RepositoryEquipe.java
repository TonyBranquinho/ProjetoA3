package repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * EquipeRepository
 * Responsável por salvar e carregar equipes do arquivo JSON.
 */
public class EquipeRepository {
    private static final String ARQUIVO = "equipes.json"; // Nome do arquivo JSON
    private Gson gson = new Gson(); // Objeto para converter entre Java e JSON

    /**
     * Carrega a lista de equipes do arquivo JSON.
     * Se o arquivo não existir, retorna lista vazia.
     */
    public List<Equipe> carregarEquipes() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            // Define o tipo da lista (List<Equipe>) para o Gson entender
            Type tipoLista = new TypeToken<List<Equipe>>() {}.getType();

            // Lê o JSON e converte para List<Equipe>
            List<Equipe> equipes = gson.fromJson(reader, tipoLista);

            // Se for null (ex: arquivo vazio), devolve lista nova
            return (equipes != null) ? equipes : new ArrayList<>();
        } catch (IOException e) {
            // Se o arquivo não existir ainda, retorna lista vazia
            return new ArrayList<>();
        }
    }

    /**
     * Salva a lista de equipes no arquivo JSON.
     */
    public void salvarEquipes(List<Equipe> equipes) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            // Converte a lista em JSON e escreve no arquivo
            gson.toJson(equipes, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Mostra erro se não conseguir salvar
        }
    }
}