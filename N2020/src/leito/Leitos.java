package leito;

import pacientes.Pacientes;

public class Leitos {
	private Pacientes paciente;
	private int numeroDoLeito;
	
	public Leitos(Pacientes paciente, int numeroDoLeito) {
		this.paciente = paciente;
		this.numeroDoLeito = numeroDoLeito;
	}
	
	public Pacientes getPaciente() {
		return paciente;
	}
	
	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}
	
	public int getNumeroDoLeito() {
		return numeroDoLeito;
	}
	
	public void setNumeroDoLeito(int numeroDoLeito) {
		this.numeroDoLeito = numeroDoLeito;
	}
}
