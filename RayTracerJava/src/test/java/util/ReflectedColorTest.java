package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generator.Intersection;

/**
 * @author Johannes Widder
 *
 */
public class ReflectedColorTest {

	@Nested
	class TestReflectedColor{	
		@Test
		@ExtendWith(MockitoExtension.class)
		public void TestAmbientColor_normal() {
			// Arrange 
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,110,120));
			when(refIntersection.getRefElement().getMaterial().getReflection()).thenReturn(0.1);
			
			ColorCalculation testReflectedColor = new ReflectedColor();
			// Run 
			Color result = testReflectedColor.getColor(refIntersection, new Color(100,100,100));

			// Validate
			assertEquals(10, result.getColorValue(0));
			assertEquals(10, result.getColorValue(1));
			assertEquals(10, result.getColorValue(2));
		}
	}
}
