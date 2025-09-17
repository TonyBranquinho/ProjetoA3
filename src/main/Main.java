package main;

import modelos.Usuario;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Criando um usuário de exemplo
        Usuario u = new Usuario(
            "Fulano da Silva",           // nome
            "123.456.789-00",            // cpf
            "fulano@example.com",        // email
            "fulano",                    // login
            "senha123",                  // senha (apenas exemplo)
            "gerente"                    // cargo
        );

        // Imprime no console usando toString() do objeto
        System.out.println(u);
	
	}

}
