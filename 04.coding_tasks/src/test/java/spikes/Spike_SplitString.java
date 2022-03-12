package spikes;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class Spike_SplitString {

	@Test
	public void spike_StringGetRemainder() {
		String str = "1,2,  3 , 4,   34,6";
		String regex = "\\W+";

		// Aufteilen an Wortgrenzen
		String[] elements = str.split(regex);
		
		assertEquals(6, elements.length);
	}

	/**
	 * Prüfen, ob eine Zeile mit einem vorgegebenen Schlüsselwort beginnt.
	 */
	@Test
	public void testCheckStartOfString() {
		String test = "   Sphere (12,32,23)";

		String regTest = "^(\\s*Sphere)(.+?)";
		boolean checkResult=test.matches(regTest);
		assertEquals(true, checkResult);
		String[] elements = test.split(regTest);
		assertEquals("(12,32,23)", elements[1]);

		regTest = "^(\\s*Box)(.+?)";
		checkResult=test.matches(regTest);
		assertEquals(false, checkResult);
		
		assertEquals(checkResult,false);
		elements = test.split(regTest);
		assertEquals(test, elements[0]);	
	}
	
	@Test
	public void spike_SplitPointToCoordinates() {
		String test = "( -12.1 , -12.0 , -13.0 )";
		String checkPoint = "\\(\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*\\)";
		
		boolean testMatch = test.matches(checkPoint);
		assertEquals(testMatch,true);
		
		String values[]= test.split("\\(\\s*|\\s*,\\s*|\\s*\\)");
		assertEquals(values[0], "");
		assertEquals(values[1], "-12.1");
		assertEquals(values[2], "-12.0");
		assertEquals(values[3], "-13.0");
	}
	
}
