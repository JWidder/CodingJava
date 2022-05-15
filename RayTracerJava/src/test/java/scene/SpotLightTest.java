package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author johan
 *
 */
public class SpotLightTest {

	/**
	 * @author johan
	 *
	 */
	@Nested 
	public class LightTest_Contructor{
		/**
		 * 
		 */
		@Test
		public void testLight() {
			assertEquals(1, 1);
		}		
	}

	/**
	 * @author Johannes Widder
	 *
	 */
	@Nested
	public class LightTest_GetIntensity{
		/**
		 * 
		 */
		@Test
		public void testGetIntensity() {
			assertEquals(1, 1);
		}		
	}
}
