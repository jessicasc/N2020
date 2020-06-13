package pacientes;

public enum Status {
	FILA_ATENDIMENTO {
		public String toString() {
			return "Est� aguardando na fila de atendimento.";
		}
	}, 
	
	LIBERADO {
		public String toString() {
			return "Paciente foi liberado, de acordo com seus sintomas n�o est� com COVID-19.";
		}
	}, 
	
	FILA_INTERNACAO {
		public String toString() {
			return "Est� aguardando na fila de interna��o.";
		}
	}, 
	
	INTERNADO {
		public String toString() {
			return "Paciente est� internado.";
		}
	}, 
	
	EM_ALTA {
		public String toString() {
			return "Paciente j� recebeu alta.";
		}
	}, 
	
	OBITO {
		public String toString() {
			return "Paciente veio a �bito.";
		}
	};
}
