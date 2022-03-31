package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import scene.ISceneElement;
import scene.Scene;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Ermitteln der Farbe des reflektierten Strahls durch die Klasse
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

		/**
		 * 
		 */
		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_addReflection_normal() {
			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);
			
			BasicColorCalculation testBasicColorCalculation = new BasicColorCalculation(0.1,0.5,testScene);
			
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,100,100));			
			
			Color testColor = testBasicColorCalculation.getColor(refIntersection, new Color(0, 0, 0));

			assertEquals(testColor.getColorValues()[0], 10);
			assertEquals(testColor.getColorValues()[1], 10);
			assertEquals(testColor.getColorValues()[2], 10);
		}
		
	}

	
	/**
	 * @author johan
	 *
	 */
	@Nested
	public class test_BasicColorCalculation_addShade{
		/**
		 * 
		 */
		@Test
		public void test_addShade_normal() {
			assertEquals(1,1);
		}
	}
	
	/**
	 * @author Johannes Widder
	 *
	 */
	@Nested
	public class test_BasicColorCalculation_Reflection {

		/**
		 * 
		 */
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testGetReflectionGrey() {

			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			@SuppressWarnings("unused")
			ISceneElement refElement = Mockito.mock(ISceneElement.class);
			
			when(refIntersection.getRefElement().getValueColor()).thenReturn(new Color(100,110,120));
			when(refIntersection.getRefElement().getMaterial().getReflection()).thenReturn(0.2);

			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);		
			BasicColorCalculation testBasicColorCalculation = new BasicColorCalculation(0.1,0.5,testScene);
			Color inColor = new Color(100, 110, 120);
			Color testColor = testBasicColorCalculation.getColor(refIntersection, inColor);

			assertEquals(testColor.getColorValues()[0], 30);
			assertEquals(testColor.getColorValues()[1], 33);
			assertEquals(testColor.getColorValues()[2], 36);
		}
	}
}
