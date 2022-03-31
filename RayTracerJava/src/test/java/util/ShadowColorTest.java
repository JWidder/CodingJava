package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import scene.ILight;
import scene.ILightRay;
import scene.LightRay;
import scene.Scene;
import scene.Sphere3D;
import scene.SpotLight;

/**
 * @author Johannes Widder
 *
 */
public class ShadowColorTest {

	/**
	 * 
	 */
	@Test
	public void testShadowColor() {
		assertEquals(1, 1);
	}

	/**
	 * Tests zur Berechnung des Beitrags einer Lichtquelle zu dem reflektierten Strahl.
	 */
	@Nested
	public class testGetColor {
		/**
		 * 
		 */
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testGetColor_abgeschattet() {
			Scene testScene = new Scene();

			Point3D testMittelpunktSphere=new Point3D(0.0, 0.0, 5.0);
			Sphere3D testSphere = new Sphere3D(testMittelpunktSphere, 2.0, ColorValue.BLACK,new BasicColorCalculation(0.1, 0.5, testScene), new Material());
			testScene.addElement(testSphere);

			ILight testLight = Mockito.mock(SpotLight.class, RETURNS_DEEP_STUBS);
			when(testLight.getLightPos()).thenReturn(new Point3D(0.0, 0.0, 10.0));
			when(testLight.getColorLight()).thenReturn(new Color(255,255,255));
			testScene.addElement(testLight);

			Point3D testIntersectionPoint = new Point3D(0.0,0.0,0.0);
			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			
			when(refIntersection.getIntersectionPoint()).thenReturn(testIntersectionPoint);
			when(refIntersection.getRefElement().getNormal(testIntersectionPoint).getDirection().normalize()).thenReturn(new Dir3D(1.0, 0.0,0.0));
			ILightRay testLightRay = new LightRay(testIntersectionPoint,new Dir3D(1.0,1.0,0.0).normalize());
			when(refIntersection.getOutRay()).thenReturn(testLightRay);

			ShadowColor testShadowColor = new ShadowColor(testScene,0.5);

			Color result = testShadowColor.getColor(refIntersection, new Color());

			assertEquals(0, result.getColorValue(0));
			assertEquals(0, result.getColorValue(1));
			assertEquals(0, result.getColorValue(2));
		}

		/**
		 * Ohne Kugel, so dass es keinen Schatten gibt.
		 */
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testGetColor_ohneSchatten() {
			Scene testScene = new Scene();

			ILight testLight = Mockito.mock(SpotLight.class, RETURNS_DEEP_STUBS);
			when(testLight.getLightPos()).thenReturn(new Point3D(-0.1, 0.0, 1.0));
			when(testLight.getColorLight()).thenReturn(new Color(255,255,255));
			testScene.addElement(testLight);

			Intersection refIntersection = Mockito.mock(Intersection.class, RETURNS_DEEP_STUBS);
			Point3D point = new Point3D(0.0, 0.0, 0.0);
			when(refIntersection.getIntersectionPoint()).thenReturn(point);
			when(refIntersection.getRefElement().getNormal(point).getDirection()).thenReturn(new Dir3D(0.0,0.0,1.0));
			when(refIntersection.getOutRay().getDirection()).thenReturn(new Dir3D(point,new Point3D(0.1, 0.0, 1.0)));
			
			ShadowColor testShadowColor = new ShadowColor(testScene,0.5);

			Color result = testShadowColor.getColor(refIntersection, new Color());

			assertEquals(127, result.getColorValue(0));
			assertEquals(127, result.getColorValue(1));
			assertEquals(127, result.getColorValue(2));
		}
	}
}
