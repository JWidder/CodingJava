package spikes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class Spike_StringErkennen {
	public String kl_auf="\\(";
	public String kl_zu="\\)";
	public String komma=",";
	public String ws = "\\s*";
	public String zahl=ws + "-?\\d+\\.?\\d*" + ws;

	public String key = "[A-Za-z_]\\w*";
	
	String checkPoint1 = kl_auf + zahl + kl_zu;
	String checkPoint2 = kl_auf + zahl + komma + zahl + kl_zu;
	String checkPoint3 = kl_auf + zahl + komma + zahl + komma + zahl + kl_zu;

	@Test
	void Spike_detect_select_KeyWord() {
		String test1 = " Box ( -12.1 , 12.3 , 12.4)";
		String test2 = "  Box ( -12.1 , 12.3 , 12.4)";
		String test3 = "Box( -12.1 , 12.3 , 12.4)";

	    Pattern p = Pattern.compile(key);   // the pattern to search for
	    Matcher m = p.matcher(test1);

		boolean result = m.find();
		assertEquals(true, result);
		String resultString =m.group(0);  
		assertEquals("Box",resultString);
	}
	
	@Test
	void Spike_detectSingleNumber() {
		String test1 = "( -12.1 )";
		String test2 = "( -12.1 , 12.3 )";
		String test3 = "( -12.1 , 12.3 , 12.4)";
		String test4 = "( -12.1 , 12.3 , 12.4 , 15)";

		assertEquals(test1.matches(checkPoint1),true);
		assertEquals(test1.matches(checkPoint2),false);
		assertEquals(test1.matches(checkPoint3),false);

		assertEquals(test2.matches(checkPoint2),true);
		assertEquals(test2.matches(checkPoint1),false);
		assertEquals(test2.matches(checkPoint3),false);		

		assertEquals(test3.matches(checkPoint3),true);		

		assertEquals(test4.matches(checkPoint3),false);		
	}
	
	@Test
	void Spike_detecetSphere() {
				
		String test = "( -12.1 , -12.0 , -13.0 )";
		String checkPoint = kl_auf + zahl + komma + zahl + komma + zahl + kl_zu;
			
		boolean testMatch = test.matches(checkPoint);
		assertEquals(testMatch,true);

		test = "( -12.0 , -13.0 )";
		checkPoint = "\\(\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*\\)";		
		testMatch = test.matches(checkPoint);
		assertEquals(testMatch,false);

		test = "( -12.0 , -13.0 , 12 , -23)";
		checkPoint = "\\(\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*,\\s*-?\\d+\\.?\\d*\\s*\\)";		
		testMatch = test.matches(checkPoint);
		assertEquals(testMatch,false);
	}
	
	
}
