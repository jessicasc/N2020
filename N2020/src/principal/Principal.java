package principal;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;

import controle.Controle;
import excessoes.OpcaoInvalidaException;
import menu.Menu;

public class Principal {
	public static void main(String[] args) {
		int op = 0;
		
		int qtd = Integer.parseInt(showInputDialog(null, "Quantos leitos para internação tem nessa unidade?", "Total de vagas", INFORMATION_MESSAGE));
		
		Controle.filaAtendimento.init();
		Controle.filaInternacao.init();
		
		do {
			
			try {
				op = parseInt(showInputDialog(null, Menu.menuPrincipal(), "Menu", INFORMATION_MESSAGE));
				if (op < 1 || op > 5) {
					throw new OpcaoInvalidaException("Digite uma opção válida!");
				}
				switch (op) {
					case 1:
						Controle.registrarPaciente();
						break;
					case 2:
						Controle.atenderPaciente(qtd);
						break;
					case 3:
						if (Controle.calcularTotalInternados() > 0) {
							Controle.liberarPaciente();
						} else {
							showMessageDialog(null, "Não há pacientes internados nesse hospital!", "Liberar paciente", WARNING_MESSAGE);
						}
						break;
					case 4:
						Controle.consultarPaciente();
						break;
					case 5:
						if (Controle.filaAtendimento.isEmpty()) {
							showMessageDialog(null, "Programa encerrado!", "Encerrando...", INFORMATION_MESSAGE);
						} else {
							showMessageDialog(null, "Ainda há pacientes na fila, favor atender antes de encerrar o programa!", "Alerta!!", WARNING_MESSAGE);
							op = 0;
						}
						break;
				}
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número", "ERRO", ERROR_MESSAGE);
			} catch (OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage(), "ERRO", ERROR_MESSAGE);
			}
			
		} while (op != 5);
	}
}
