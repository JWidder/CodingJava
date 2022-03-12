package rayTracer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author Johannes Widder
 *
 */
public class RayTracerTest {

	/**
	 * @throws Exception
	 */
	@Test
	public void testMain() throws Exception {
		// TODO Test erstellen
		String[] testData=new String[3];
		RayTracer.main(testData);
		assertEquals(2,2);
	}

}
