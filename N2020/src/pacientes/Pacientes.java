package pacientes;

public class Pacientes {
	private String nome;
	private int cpf;
	private Status status;
	
	public Pacientes(String nome, int cpf, Status status) {
		this.nome = nome;
		this.cpf = cpf;
		this.status = status;
	}
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public int getCpf() {
		return cpf;
	}
	
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Nome do paciente: "+nome+"\nStatus do paciente: "+status.toString();
	}
}
