package menu;

public class Menu {
	public static String menuPrincipal() {
		String aux = "Escolha uma das opções abaixo:\n";
		aux += "1. Registrar paciente; \n";
		aux += "2. Atender paciente; \n";
		aux += "3. Liberar paciente; \n";
		aux += "4. Consultar paciente; \n";
		aux += "5. Sair.";
		return aux;
	}
	
	public static String menuSecundario(String nome) {
		String aux = "Qual o motivo da liberação do paciente "+nome+":\n";
		aux += "1. Receberá alta; \n";
		aux += "2. Paciente veio a óbito.";
		return aux;
	}
}
