package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import scene.Light;
import scene.LightRay;
import scene.Scene;
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
	public void testIntersection() throws Exception {
		final double r = 2.0;
		final double d = 5.0 * r;

		Scene testScene = new Scene();
		ColorCalculation testShading = new BasicColorCalculation(0.1);

		Sphere3D testSphere3d1 =new Sphere3D(new Point3D(d, -r * 0.5 * Math.sqrt(2.0), 0), r, ColorValue.RED,testShading,new Material());
		testScene.addElement(testSphere3d1);
		Sphere3D testSphere3d2 =new Sphere3D(new Point3D(d - Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading,new Material()); 
		testScene.addElement(testSphere3d2);
		Sphere3D testSphere3d3=new Sphere3D(new Point3D(d + Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading,new Material());
		testScene.addElement(testSphere3d3);

		testScene.addElement(new Light(1.0));

		LightRay testLightRay1 = new LightRay(new Point3D(0.0, 0.0, 0.0), new Dir3D(1.0, 0.0, 0.0));
		Intersection testIntersection1 = testScene.intersectRay(testLightRay1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testIntersectionPoint3DDir3DColorsDouble() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetPoint() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetPoint() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetNormale() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetNormale() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetAngle() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetAngle() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testSetColor() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetColor() throws Exception {
		// TODO Test erstellen
		assertEquals(1,1);
	}

}
