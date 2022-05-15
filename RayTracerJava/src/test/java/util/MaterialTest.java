package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MaterialTest {
	
	@Nested
	public class MaterialTest_Constructor{
		
		@Test
		public void testStandardConstructor() {	
		Material testMaterial = new Material(0.1);
		assertEquals(0.1,testMaterial.getReflection());
		}
	}
	
	/**
	 * @author Johannes Widder
	 *
	 */
	@Nested
	public class MaterialTest_GetterSetter{
		
		@Test
		public void testGetReflection() {
			Material testMaterial = new Material();
			double testValue=0.1;
			testMaterial.setReflection(testValue);
			assertEquals(testValue,testMaterial.getReflection());
			
		}
	}

}
