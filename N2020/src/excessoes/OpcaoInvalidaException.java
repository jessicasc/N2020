package excessoes;

@SuppressWarnings("serial")
public class OpcaoInvalidaException extends Exception {
	public OpcaoInvalidaException(String msg) {
		super(msg);
	}
}
