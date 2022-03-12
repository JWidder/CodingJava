package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Dir3D;
import util.Point3D;

/**
 * @author Johannes Widder
 *
 */
public class Plane3DTest {

	/**
	 * 
	 */
	@Test
	public void testPlane3DPoint3DDir3D() {
		assertEquals(1, 1);
	}

	/**
	 * 
	 */
	@Test
	public void testPlane3DDir3DDir3DPoint3D() {
		assertEquals(1, 1);
	}

	/**
	 * 
	 */
	@Test
	public void testIntersectRay() {
		Plane3D testPlane3D = new Plane3D(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));
	}

	/**
	 * 
	 */
	@Test
	public void testGetNormal() {
		assertEquals(1, 1);
	}

	/**
	 * 
	 */
	@Test
	public void testGetValueColor() {
		assertEquals(1, 1);
	}

	/**
	 * 
	 */
	@Test
	public void testGetValueReflection() {
		assertEquals(1, 1);
	}

}
