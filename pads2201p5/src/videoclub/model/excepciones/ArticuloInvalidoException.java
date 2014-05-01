package videoclub.model.excepciones;

public class ArticuloInvalidoException extends RuntimeException {
	public ArticuloInvalidoException (String message, Throwable t) {
		super(message, t);
	}

	public ArticuloInvalidoException(String message) {
		super(message);
	}

}
