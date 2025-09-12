package Modelos;

public class Usuario {

	// ---------ATRIBUTOS-------------
	// Cada usuario vai ter esses dados basicos:
	private String nome;
	private String cpf;
	private String email;
	private String loging;
	private String senha;
	private String cargo;
	
	// --------Construtor-------------
	public Usuario(String nome, String cpf, String email, String loging, String senha, String cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.loging = loging;
		this.senha = senha;
		this.cargo = cargo;
}