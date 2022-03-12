package stringHandling;

public class ExampleExceptionHandling {
	public ExampleExceptionHandling() {
		return;
	}
	
	public double calculateResult(double test) throws BasicException {
		if (test>10.0) throw new BasicException("test","12");
		return 2*test;
	}
}
