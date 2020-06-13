package filas;

import pacientes.Pacientes;

public class filaAtendimento {
	private class NO {
		Pacientes dado;
		NO prox;
	}
	
	private NO ini;
	private NO fim;
	
	public void init() {
		ini=fim=null;
	}
	
	public boolean isEmpty() {
		if (ini == null && fim == null) {
			return true;
		} else {
			return false;		
		}
	}
	
	public void enqueue(Pacientes elem) {
		NO novo = new NO();
		novo.dado = elem;
		novo.prox = null;
		if (isEmpty()) {
			ini = novo;
		} else {
			fim.prox = novo;
		}
		fim = novo;
	}
	
	public Pacientes dequeue() {
		Pacientes p = ini.dado;
		ini = ini.prox;
		if (ini == null) {
			fim = null;
		}	
		return p;
	}
}
