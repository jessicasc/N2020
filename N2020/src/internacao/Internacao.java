package internacao;

import leito.Leitos;
import pacientes.Pacientes;
import pacientes.Status;

public class Internacao {
	private Leitos[] leitos;
	private int indice = 0;
	
	public Internacao(int tamanho) {
		leitos = new Leitos[tamanho];
	}

	public void internar(Pacientes p) {
		int leito;
		if (indice == leitos.length) {
			for (int i = 0; i < leitos.length; i++) {
				if (leitos[i].getPaciente().getStatus() == Status.EM_ALTA || leitos[i].getPaciente().getStatus() == Status.OBITO) {
					leito = i+1;
					leitos[i] = new Leitos(p, leito);
					p.setLeito(leito);
				}
			}
		} else {
			leito = indice+1;
			leitos[indice] = new Leitos(p, leito);	
			p.setLeito(leito);
			indice++;
		}
		
		p.setStatus(Status.INTERNADO);
	}	
}
