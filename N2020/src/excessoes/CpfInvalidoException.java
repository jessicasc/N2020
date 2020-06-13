package excessoes;

@SuppressWarnings("serial")
public class CpfInvalidoException extends Exception {
	public CpfInvalidoException(String msg) {
		super(msg);
	}
}
