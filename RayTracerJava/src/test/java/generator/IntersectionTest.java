package generator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import rayTracer.Parameter;
import util.Color;
import util.ColorCalculation;
import util.ColorValue;
import util.Dir3D;
import util.Material;
import util.Point3D;
import scene.SpotLight;
import scene.LightRay;
import scene.Scene;
import scene.SceneTracer;
import scene.Sphere3D;

/**
 * @author Johanns Widder
 *
 */
public class IntersectionTest {

	/**
	 * @throws Exception
	 */
	@Test
	@ExtendWith(MockitoExtension.class)
	public void testIntersection() throws Exception {
		final double r = 2.0;
		final double d = 5.0 * r;

		Scene testScene = new Scene();
		SceneTracer testSceneTracer = new SceneTracer(testScene,new Parameter());

		Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
		ColorCalculation testColorCalculation = Mockito.mock(ColorCalculation.class, RETURNS_DEEP_STUBS);

		Sphere3D testSphere3d1 =new Sphere3D(new Point3D(d, -r * 0.5 * Math.sqrt(2.0), 0), r, ColorValue.RED,testColorCalculation,testMaterial);
		testScene.addElement(testSphere3d1);
		Sphere3D testSphere3d2 =new Sphere3D(new Point3D(d - Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testColorCalculation,testMaterial); 
		testScene.addElement(testSphere3d2);
		Sphere3D testSphere3d3=new Sphere3D(new Point3D(d + Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testColorCalculation,testMaterial);
		testScene.addElement(testSphere3d3);

		testScene.addElement(new SpotLight(new Point3D(),new Color(255,255,255)));

		LightRay testLightRay1 = new LightRay(new Point3D(0.0, 0.0, 0.0), new Dir3D(1.0, 0.0, 0.0));
		Intersection testIntersection1 = testSceneTracer.intersectRay(testLightRay1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testIntersectionPoint3DDir3DColorsDouble() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetPoint() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetPoint() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetNormale() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetNormale() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetAngle() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetAngle() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetColor() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetColor() throws Exception {
		// XXX Test erstellen
		assertEquals(1,1);
	}
	@Nested
	class testAddOutLightRay{
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testAdd_ReflectedLight() {
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testColorCalculation = Mockito.mock(ColorCalculation.class, RETURNS_DEEP_STUBS);
			
			double radius=1.0;
			Point3D mittelPunkt = new Point3D(0.0 , 0.0 , -radius);
			Sphere3D testSceneElement = new Sphere3D(mittelPunkt, 1.0, ColorValue.BLACK, testColorCalculation, testMaterial);
			
			LightRay testLightRay = new LightRay(new Point3D(-10.0,0.0,10.0), new Point3D(-9.0,0.0,9.0));
		
			Intersection testIntersection = new Intersection();
			testIntersection.setParameter(1.0,new Point3D(0.0,0.0,0.0),testLightRay);
			testIntersection.setSceneElement(testSceneElement);
			testIntersection.setNormale(testSceneElement.getNormal(new Point3D(0.0,0.0,0.0)).getDirection());
			testIntersection.processIntersection();

			assertEquals(testIntersection.getOutRay().getDirection().getxDir(),0.5*Math.sqrt(2.0));
			assertEquals(testIntersection.getOutRay().getDirection().getyDir(),0.0);
			assertEquals(testIntersection.getOutRay().getDirection().getzDir(),0.5*Math.sqrt(2.0));
		}
	}
}
