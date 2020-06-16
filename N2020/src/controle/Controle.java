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

		nome = showInputDialog(null, "Informe o nome do paciente", "Registro do paciente", INFORMATION_MESSAGE);
		cpf = parseInt(showInputDialog(null, "Informe o CPF do paciente", "Registro do paciente", INFORMATION_MESSAGE));
		Status status = Status.FILA_ATENDIMENTO;

		Pacientes p = new Pacientes(nome, cpf, status);
		filaAtendimento.enqueue(p);
		lista.add(p);

		showMessageDialog(null, "Paciente " + nome + " registrado com sucesso na fila para atendimento.",
				"Registro do paciente", INFORMATION_MESSAGE);
	}

	public static void atenderPaciente(int qtd) {
		int resp;
		int aux = 0;

		if (filaAtendimento.isEmpty()) {
			showMessageDialog(null, "Não há pacientes na fila para atendimento!", "Fila vazia", WARNING_MESSAGE);
		} else {
			Pacientes p = filaAtendimento.dequeue();
			showMessageDialog(null, "Você atenderá o paciente " + p.getNome() + " faça a ele as perguntas a seguir",
					"Atendimento", INFORMATION_MESSAGE);

			resp = showConfirmDialog(null, "Você sente febre?", "Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você tem tosse seca e/ ou secretiva?", "Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você tem falta de ar e/ ou dificuldades para respirar?", "Atendimento",
					INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você sente dor de garganta?", "Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você sente fadiga?", "Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você possui alguma doença crônica (diabetes, asma ou hipertensão)?",
					"Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Você tem mais de 60 anos?", "Atendimento", INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			resp = showConfirmDialog(null, "Alguém do seu convívio contraiu a doença?", "Atendimento",
					INFORMATION_MESSAGE);
			if (resp == 0) {
				aux++;
			}

			if (aux >= 4) {
				int cont = calcularTotalInternados();

				if (cont < qtd) {
					showMessageDialog(null, "O paciente " + p.getNome() + " será internado!", "Paciente internado",
							INFORMATION_MESSAGE);
					p.setStatus(Status.INTERNADO);
				} else {
					filaInternacao.enqueue(p);
					showMessageDialog(null, "No momento não há leitos disponíveis.\n" + "O paciente " + p.getNome()
							+ " entrou na fila para internação!", "Aguardando internação", WARNING_MESSAGE);
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
		if (Controle.calcularTotalInternados() > 0) {
			
			int cpf = parseInt(showInputDialog(null, "Digite o CPF do paciente para liberá-lo", "Liberar paciente",
					INFORMATION_MESSAGE));

			int i = consultarCPF(cpf);

			try {

				if (i == -1) {
					throw new CpfInvalidoException("Não há nenhum paciente cadastrado com esse CPF!");
				} else if (lista.get(i).getStatus() != Status.INTERNADO) {
					showMessageDialog(null, "O paciente " + lista.get(i).getNome() + " não está internado!",
							"Liberar paciente", INFORMATION_MESSAGE);
				} else {
					Pacientes p = lista.get(i);
					
					int resp = parseInt(showInputDialog(null, Menu.menuSecundario(p.getNome()),
							"Liberar paciente", INFORMATION_MESSAGE));

					if (resp < 1 || resp > 2) {
						throw new OpcaoInvalidaException("Digite uma opção válida!");
					}

					switch (resp) {
					case 1:
						p.setStatus(Status.EM_ALTA);
						showMessageDialog(null, "O paciente " + p.getNome() + " recebeu alta!",
								"Liberar paciente", INFORMATION_MESSAGE);
						break;
					case 2:
						p.setStatus(Status.OBITO);
						showMessageDialog(null, "O paciente " + p.getNome() + " veio a óbito!",
								"Liberar paciente", INFORMATION_MESSAGE);
						break;
					}

					if (!filaInternacao.isEmpty()) {
						p = filaInternacao.dequeue();
						p.setStatus(Status.INTERNADO);
						showMessageDialog(null,
								"O paciente " + p.getNome() + " saiu da fila de internação e está internado!",
								"Paciente internado", INFORMATION_MESSAGE);
					}
				}
			} catch (CpfInvalidoException e) {
				showMessageDialog(null, e.getMessage(), "CPF Inválido", WARNING_MESSAGE);
			} catch (OpcaoInvalidaException e) {
				showMessageDialog(null, e.getMessage(), "ERRO", ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				showMessageDialog(null, "Você deve digitar um número", "ERRO", ERROR_MESSAGE);
			}
			
		} else {
			showMessageDialog(null, "Não há pacientes internados nesse hospital!", "Liberar paciente", WARNING_MESSAGE);
		}
	}

	public static void consultarPaciente() {
		int cpf = parseInt(showInputDialog(null, "Digite o CPF do paciente para consultá-lo", "Consultar paciente",
				INFORMATION_MESSAGE));

		int i = consultarCPF(cpf);

		try {
			if (i == -1) {
				throw new CpfInvalidoException("Não há nenhum paciente cadastrado com esse CPF!");
			} else {
				showMessageDialog(null, lista.get(i).toString(), "Consultar paciente", INFORMATION_MESSAGE);
			}
		} catch (CpfInvalidoException e) {
			showMessageDialog(null, e.getMessage(), "CPF Inválido", WARNING_MESSAGE);
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
