package modelos;

public class Projeto {

	private String nome;
	private String descricao;
	private String dataInicio;
	private String dataTerminoPrevista;
	private String status; // planejado, em andamento, concluído, cancelado

	// Construtor
	public Projeto(String nome, String descricao, String dataInicio, String dataTerminoPrevista, String status) {
		this.nome = nome;
		this.descricao = descricao;
		this.dataInicio = dataInicio;
		this.dataTerminoPrevista = dataTerminoPrevista;
		this.status = status;
	}

	// Getters e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataTerminoPrevista() {
		return dataTerminoPrevista;
	}

	public void setDataTerminoPrevista(String dataTerminoPrevista) {
		this.dataTerminoPrevista = dataTerminoPrevista;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Projeto{" + "nome='" + nome + '\'' + ", descricao='" + descricao + '\'' + ", dataInicio='" + dataInicio
				+ '\'' + ", dataTerminoPrevista='" + dataTerminoPrevista + '\'' + ", status='" + status + '\'' + '}';
	}
}
