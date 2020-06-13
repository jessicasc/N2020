package controle;

import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;

import pacientes.Pacientes;
import pacientes.Status;
import filas.filaAtendimento;
import filas.filaInternacao;
import menu.Menu;
import excessoes.CpfInvalidoException;
import excessoes.OpcaoInvalidaException;

public class Controle {
	private static List<Pacientes> lista = new ArrayList<Pacientes>();
	public static filaAtendimento filaAtendimento = new filaAtendimento();
	public static filaInternacao filaInternacao = new filaInternacao();

	public static void registrarPaciente() {
		String nome;
		int cpf;
		Status status;

		nome = showInputDialog(null, "Informe o nome do paciente", "Registro do paciente", INFORMATION_MESSAGE);
		cpf = parseInt(showInputDialog(null, "Informe o CPF do paciente", "Registro do paciente", INFORMATION_MESSAGE));
		status = Status.FILA_ATENDIMENTO;

		Pacientes p1 = new Pacientes(nome, cpf, status);
		filaAtendimento.enqueue(p1);
		lista.add(p1);

		showMessageDialog(null, "Paciente " + nome + " registrado com sucesso na fila para atendimento.",
				"Registro do paciente", INFORMATION_MESSAGE);
	}

	public static void atenderPaciente(int qtd) {
		int resp;
		int aux = 0;

		if (filaAtendimento.isEmpty()) {
			showMessageDialog(null, "N�o h� pacientes na fila para atendimento!", "Fila vazia", WARNING_MESSAGE);
		} else {
			Pacientes p = filaAtendimento.dequeue();
			showMessageDialog(null, "Voc� atender� o paciente " + p.getNome() + " fa�a a ele as perguntas a seguir",
					"Atendimento", INFORMATION_MESSAGE);

			resp = showConfirmDialog(null, "Voc� sente febre?", "Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� tem tosse seca e/ ou secretiva?", "Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� tem falta de ar e/ ou dificuldades para respirar?", "Perguntas",
					INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� sente dor de garganta?", "Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� sente fadiga?", "Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� possui alguma doen�a cr�nica (diabetes, asma ou hipertens�o?",
					"Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Voc� tem mais de 60 anos?", "Perguntas", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Algu�m do seu conv�vio contraiu a doen�a?", "Perguntas",
					INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			if (aux >= 4) {
				int cont = calcularTotalInternados();

				if (cont < qtd) {
					showMessageDialog(null, "O paciente " + p.getNome() + " ser� internado!", "Paciente internado",
							INFORMATION_MESSAGE);
					p.setStatus(Status.INTERNADO);
				} else {
					filaInternacao.enqueue(p);
					showMessageDialog(null, "No momento n�o h� leitos dispon�veis.\n" + "O paciente " + p.getNome()
							+ " entrou na fila para interna��o!", "Aguardando interna��o", WARNING_MESSAGE);
					p.setStatus(Status.FILA_INTERNACAO);
				}
			} else {
				showMessageDialog(null, "O paciente " + p.getNome() + " pode ser liberado!", "Paciente liberado",
						INFORMATION_MESSAGE);
				p.setStatus(Status.LIBERADO);
			}
		}
	}

	public static void liberarPaciente() {
		int cpf = parseInt(showInputDialog(null, "Digite o CPF do paciente para liber�-lo", "Liberar paciente",
				INFORMATION_MESSAGE));

		int i = consultarCPF(cpf);

		try {

			if (i == -1) {
				throw new CpfInvalidoException("N�o h� nenhum paciente cadastrado com esse CPF!");
			} else if (lista.get(i).getStatus() != Status.INTERNADO) {
				showMessageDialog(null, "O paciente " + lista.get(i).getNome() + " n�o est� internado!",
						"Liberar paciente", INFORMATION_MESSAGE);
			} else {
				int resp = parseInt(showInputDialog(null, Menu.menuSecundario(lista.get(i).getNome()),
						"Liberar paciente", INFORMATION_MESSAGE));

				if (resp < 1 || resp > 2) {
					throw new OpcaoInvalidaException("Digite uma op��o v�lida!");
				}

				switch (resp) {
				case 1:
					lista.get(i).setStatus(Status.EM_ALTA);
					showMessageDialog(null, "O paciente " + lista.get(i).getNome() + " recebeu alta!",
							"Liberar paciente", INFORMATION_MESSAGE);
					break;
				case 2:
					lista.get(i).setStatus(Status.OBITO);
					showMessageDialog(null, "O paciente " + lista.get(i).getNome() + " veio a �bito!",
							"Liberar paciente", INFORMATION_MESSAGE);
					break;
				}

				if (!filaInternacao.isEmpty()) {
					Pacientes p = filaInternacao.dequeue();
					p.setStatus(Status.INTERNADO);
					showMessageDialog(null,
							"O paciente " + p.getNome() + " saiu da fila de interna��o e est� internado!",
							"Paciente internado", INFORMATION_MESSAGE);
				}
			}
		} catch (CpfInvalidoException e) {
			showMessageDialog(null, e.getMessage(), "CPF Inv�lido", WARNING_MESSAGE);
		} catch (OpcaoInvalidaException e) {
			showMessageDialog(null, e.getMessage(), "ERRO", ERROR_MESSAGE);
		} catch (NumberFormatException e) {
			showMessageDialog(null, "Voc� deve digitar um n�mero", "ERRO", ERROR_MESSAGE);
		}

	}

	public static void consultarPaciente() {
		int cpf = parseInt(showInputDialog(null, "Digite o CPF do paciente para consult�-lo", "Consultar paciente",
				INFORMATION_MESSAGE));

		int i = consultarCPF(cpf);

		try {
			if (i == -1) {
				throw new CpfInvalidoException("N�o h� nenhum paciente cadastrado com esse CPF!");
			} else {
				showMessageDialog(null, lista.get(i).toString(), "Consultar paciente", INFORMATION_MESSAGE);
			}
		} catch (CpfInvalidoException e) {
			showMessageDialog(null, e.getMessage(), "CPF Inv�lido", WARNING_MESSAGE);
		}

	}

	public static int calcularTotalInternados() {
		int cont = 0;

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getStatus() == Status.INTERNADO) {
				cont++;
			}
		}

		return cont;
	}
	
	private static int consultarCPF(int cpf) {
		int aux = -1;

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCpf() == cpf) {
				aux = i;
				break;
			}
		}
		return aux;
	}
}
