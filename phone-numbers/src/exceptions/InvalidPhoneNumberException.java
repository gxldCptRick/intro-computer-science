package exceptions;

public class InvalidPhoneNumberException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPhoneNumberException(String Message) {
		super(Message);
	}
}
