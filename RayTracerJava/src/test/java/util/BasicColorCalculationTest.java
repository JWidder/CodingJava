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
 * @d
 */
@ExtendWith(MockitoExtension.class)  
public class BasicColorCalculationTest {
	/**
	 * 
	 */
	@Nested
	public class test_BasicShading_addReflection{

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
	public class test_PhongShading_addShade{
		@Test
		public void test_addShade_normal() {
			assertEquals(1,1);
		}
	}
	
	@Nested
	public class test_PhongShading_Reflection {

		@Test
		public void testGetReflectionBlack() {

			BasicColorCalculation testPhongShading = new BasicColorCalculation(0.1);

			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,100,100));
			
			Color testColor = testPhongShading.getColor(refIntersection, new Color(0, 0, 0));

			
			
//			assertEquals(testColor.getColorValues()[0], 100);
//			assertEquals(testColor.getColorValues()[1], 100);
//			assertEquals(testColor.getColorValues()[2], 100);
		}

		@Test
		public void testGetReflectionGrey() {

			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			ISceneElement refElement = Mockito.mock(ISceneElement.class);
			
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color());

			
			BasicColorCalculation testPhongShading = new BasicColorCalculation(0.1);
			Color inColor = new Color(100, 110, 120);
			Color testColor = testPhongShading.getColor(refIntersection, inColor);

			assertEquals(testColor.getColorValues()[0], 0);
			assertEquals(testColor.getColorValues()[1], 0);
			assertEquals(testColor.getColorValues()[2], 0);
		}
	}
}
