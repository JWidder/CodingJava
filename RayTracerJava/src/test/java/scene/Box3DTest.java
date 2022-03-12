/**
 * 
 */
package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Dir3D;
import util.Intersection;
import util.Point3D;


/**
 * @author Johannes Widder
 *
 */
public class Box3DTest {

	/**
	 * Test method for {@link scene.Box3D#intersectRay(scene.LightRay)}.
	 */
	@Test
	public void testRayStandardBoxIntersection() {
		double delta=5.0;
	
		Box3D box = new Box3D();

		Point3D posStart = new Point3D(-delta,0.5,0.5);
		LightRay testRay = new LightRay(posStart, new Dir3D(delta,0.0,0.0));
		Intersection result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);

		posStart = new Point3D(0.5,-delta,0.5);
		testRay = new LightRay(posStart, new Dir3D(0.0,delta,0.0));
		result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(0.5,0.5,-delta);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,delta));
		result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(1.5,1.5,-delta);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,delta));
		result = box.intersectRay(testRay);
		assertEquals(Double.MAX_VALUE,result.getParameter(),0.00001);

		// Strahlen von hinten
		posStart = new Point3D(delta+1.0,0.5,0.5);
		testRay = new LightRay(posStart, new Dir3D(-delta,0.0,0.0));
		result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);

		posStart = new Point3D(0.5,delta+1.0,0.5);
		testRay = new LightRay(posStart, new Dir3D(0.0,-delta,0.0));
		result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(0.5,0.5,delta+1.0);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,-delta));
		result = box.intersectRay(testRay);
		assertEquals(5.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(1.5,1.5,delta+1.0);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,-delta));
		result = box.intersectRay(testRay);
		assertEquals(Double.MAX_VALUE,result.getParameter(),0.00001);
	}
	/**
	 * Test method for {@link scene.Box3D#intersectRay(scene.LightRay)}.
	 */
	@Test
	public void testRayBoxIntersection() {
		double delta=10.0;
	
		double xLen=2.0;
		double yLen=3.0;
		double zLen=4.0;
		
		Box3D box = new Box3D(xLen,yLen,zLen);

		Point3D posStart = new Point3D(-delta,0.5,0.5);
		LightRay testRay = new LightRay(posStart, new Dir3D(delta,0.0,0.0));
		Intersection result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);

		posStart = new Point3D(0.5,-delta,0.5);
		testRay = new LightRay(posStart, new Dir3D(0.0,delta,0.0));
		result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(0.5,0.5,-delta);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,delta));
		result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(10,1.5,-delta);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,delta));
		result = box.intersectRay(testRay);
		assertEquals(Double.MAX_VALUE,result.getParameter(),0.00001);

		// Strahlen von hinten
		posStart = new Point3D(delta+xLen,0.5,0.5);
		testRay = new LightRay(posStart, new Dir3D(-delta,0.0,0.0));
		result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);

		posStart = new Point3D(0.5,delta+yLen,0.5);
		testRay = new LightRay(posStart, new Dir3D(0.0,-delta,0.0));
		result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(0.5,0.5,delta+zLen);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,-delta));
		result = box.intersectRay(testRay);
		assertEquals(10.0,result.getParameter(),0.00001);
	
		posStart = new Point3D(10,10,delta+1.0);
		testRay = new LightRay(posStart, new Dir3D(0.0,0.0,-delta));
		result = box.intersectRay(testRay);
		assertEquals(Double.MAX_VALUE,result.getParameter(),0.00001);
	}

}
