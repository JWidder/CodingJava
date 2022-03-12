package scene;

/**
 * @author Johannes Widder
 *
 */
public class ExceptionSyntaxError extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param w1
	 * @param w2
	 */
	public ExceptionSyntaxError(String w1,String w2)
	{
		super(w1+"_"+w2); //$NON-NLS-1$
		return;
	}

}
