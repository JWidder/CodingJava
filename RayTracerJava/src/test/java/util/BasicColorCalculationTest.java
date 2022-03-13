package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import scene.ISceneElement;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Ermitteln der Farbe des reflektierten Strahls durch die Klasse
 *  
 * @see util.BasicColorCalculation.
 * 
 * @author Johannes Widder
 */
@ExtendWith(MockitoExtension.class)  
public class BasicColorCalculationTest {
	/**
	 * 
	 */
	@Nested
	public class test_BasicColorCalculation_Ambient{

		@Test
		public void test_addReflection_normal() {

			BasicColorCalculation testPhongShading = new BasicColorCalculation(0.1);
			
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,100,100));			
			
			Color testColor = testPhongShading.getColor(refIntersection, new Color(0, 0, 0));

			assertEquals(testColor.getColorValues()[0], 10);
			assertEquals(testColor.getColorValues()[1], 10);
			assertEquals(testColor.getColorValues()[2], 10);
		}
		
	}

	
	@Nested
	public class test_BasicColorCalculation_addShade{
		@Test
		public void test_addShade_normal() {
			assertEquals(1,1);
		}
	}
	
	@Nested
	public class test_BasicColorCalculation_Reflection {

		@Test
		public void testGetReflectionGrey() {

			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			@SuppressWarnings("unused")
			ISceneElement refElement = Mockito.mock(ISceneElement.class);
			
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,110,120));
			when(refIntersection.getRefElement().getMaterial().getReflection()).thenReturn(0.2);
			
			BasicColorCalculation testBasicColorCalculation = new BasicColorCalculation(0.1);
			Color inColor = new Color(100, 110, 120);
			Color testColor = testBasicColorCalculation.getColor(refIntersection, inColor);

			assertEquals(testColor.getColorValues()[0], 30);
			assertEquals(testColor.getColorValues()[1], 33);
			assertEquals(testColor.getColorValues()[2], 36);
		}
	}
}
