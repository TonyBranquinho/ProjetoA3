package modelos;

public class Usuario {
	private String nomeCompleto;
	private String cpf;
	private String email;
	private String cargo;
	private String login;
	private String senha;
	private String perfil; // administrador, gerente ou colaborador

	// Construtor
	public Usuario(String nomeCompleto, String cpf, String email, String cargo, String login, String senha,
			String perfil) {
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.email = email;
		this.cargo = cargo;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
	}

	// Getters e Setters
	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "Usuário{" + "nome='" + nomeCompleto + '\'' + ", cpf='" + cpf + '\'' + ", email='" + email + '\''
				+ ", cargo='" + cargo + '\'' + ", login='" + login + '\'' + ", perfil='" + perfil + '\'' + '}';
	}
}