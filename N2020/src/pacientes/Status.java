package pacientes;

public enum Status {
	FILA_ATENDIMENTO {
		public String toString() {
			return "Está aguardando na fila de atendimento.";
		}
	}, 
	
	LIBERADO {
		public String toString() {
			return "Paciente foi liberado, de acordo com seus sintomas não está com COVID-19.";
		}
	}, 
	
	FILA_INTERNACAO {
		public String toString() {
			return "Está aguardando na fila de internação.";
		}
	}, 
	
	INTERNADO {
		public String toString() {
			return "Paciente está internado.";
		}
	}, 
	
	EM_ALTA {
		public String toString() {
			return "Paciente já recebeu alta.";
		}
	}, 
	
	OBITO {
		public String toString() {
			return "Paciente veio a óbito.";
		}
	};
}
