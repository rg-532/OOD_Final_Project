package model.admin;

public class NamePwdMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2578802783544851546L;
	
	public NamePwdMismatchException(String msg) {
		super(msg);
	}
}
