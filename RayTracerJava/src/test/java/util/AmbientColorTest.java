package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author johan
 *
 */
public class AmbientColorTest {

	@Nested
	class TestAmbientColor{	
		@Test
		@ExtendWith(MockitoExtension.class)
		public void TestAmbientColor_normal() {
			// Arrange 
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,110,120));
		
			ColorCalculation testAmbientColor = new AmbientColor(0.5);
			// Run 
			Color result = testAmbientColor.getColor(refIntersection, null);

			// Validate
			assertEquals(50, result.getColorValue(0));
			assertEquals(55, result.getColorValue(1));
			assertEquals(60, result.getColorValue(2));
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void TestAmbientColor_black() {
			// Arrange 
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,110,120));
		
			ColorCalculation testAmbientColor = new AmbientColor(0.0);
			// Run 
			Color result = testAmbientColor.getColor(refIntersection, null);

			// Validate
			assertEquals(0, result.getColorValue(0));
			assertEquals(0, result.getColorValue(1));
			assertEquals(0, result.getColorValue(2));
		}
	}
}
