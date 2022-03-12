package stringHandling;

public class BasicException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6795683798428222912L;

	private String code;
	
	public BasicException (String inCode, String inMessage) {
		super(inMessage);
		this.code = inCode;
	}
	
    public BasicException(String inCode, String inMessage, Throwable inCause) {
        super(inMessage, inCause);
        this.setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }	
	
}
