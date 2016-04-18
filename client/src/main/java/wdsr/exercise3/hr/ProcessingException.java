package wdsr.exercise3.hr;

/**
 * Indicates that a required service failed to execute the requested operation.
 */
public class ProcessingException extends RuntimeException {
	private static final long serialVersionUID = 3880706055264264067L;

	public ProcessingException() {
		super();
	}

	public ProcessingException(String message) {
		super(message);
	}

	public ProcessingException(Throwable cause) {
		super(cause);
	}

	public ProcessingException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProcessingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
