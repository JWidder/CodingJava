package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import util.ColorValue;
import util.Dir3D;
import util.Intersection;
import util.Material;
import util.BasicColorCalculation;
import util.Point3D;
import util.ColorCalculation;
import util.Util;

/**
 * Unit Testklasse Spere3D
 * 
 * @author Johannes Widder
 *
 */
public class Sphere3DTest {

	@Nested
	class testConstructor{
		@Test
		public final void testSphere3D() throws Exception {
			Point3D mittelPunkt = new Point3D();
			double radius = 1.0;
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Sphere3D testSphere3d = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN,testShading,new Material());
			
			assertEquals(1.0, testSphere3d.getRadius(),0.001);
		}
	}
	
	@Nested
	class testGetterSetter{
		@Test
		public void testBasicGetterSetter() throws Exception {
			// preparation
			double radius=1.0;
			Point3D center = new Point3D(1.0,2.0,3.0);
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			// action
			Sphere3D testSphere = new Sphere3D(center, radius,ColorValue.GREEN,testShading,new Material());
			
			// assert
			assertEquals(1.0, testSphere.getRadius(),0.0001);
			assertEquals(1.0, testSphere.getMittelPunkt().getxPos(),0.0001);
			assertEquals(2.0, testSphere.getMittelPunkt().getyPos(),0.0001);
			assertEquals(3.0, testSphere.getMittelPunkt().getzPos(),0.0001);
		}		
	}
	
	@Nested
	class testIntersection {
		@Test
		public void testIntersection_Standard() throws Exception {

			double radius = 1.0;
			Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
			ColorCalculation testShading = new BasicColorCalculation(0.1);
			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());

			LightRay testRay = new LightRay(new Point3D(-2.0, 0.0, 0.0), new Dir3D(1.0,0,0));

			Intersection result = sphere.intersectRay(testRay);
			assertEquals(result.getParameter(), 1.0, 0.00001);
			assertEquals(result.getRefElement(),sphere);
		}

		@Test
		public void testIntersectionInner() throws Exception {
			double radius = 2.5;
			Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());

			Point3D cameraPos = new Point3D(-5.0, 0.0, 0.0);
			Point3D screenPoint = new Point3D(0.0, 0.0, 0.0);
			Dir3D direction = Util.difference(screenPoint, cameraPos);

			LightRay testRay = new LightRay(cameraPos, direction);

			Intersection result = sphere.intersectRay(testRay);
			assertEquals(result.getParameter(), 2.5, 0.00001);

			sphere.move(new Dir3D(radius, 0.0, 0.0));
			result = sphere.intersectRay(testRay);
			assertEquals(result.getParameter(), 5.0, 0.00001);
		}

		@Test
		public void testIntersectionNone() throws Exception {
			double radius = 2.5;
			Point3D mittelPunkt = new Point3D(radius, 2 * radius, 0.0);
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());

			Point3D cameraPos = new Point3D(-5.0, 0.0, 0.0);
			Point3D screenPoint = new Point3D(0.0, 0.0, 0.0);
			Dir3D direction = Util.difference(screenPoint, cameraPos);

			LightRay testRay = new LightRay(cameraPos, direction);

			Intersection result = sphere.intersectRay(testRay);
			assertEquals(result.getParameter(), Double.MAX_VALUE, 0.00001);
		}
	}

	@Nested
	class UnitTest_DoesIntersectRay{
		@Test
		public void testDoesIntersectRay() {
			double radius = 5.0;
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Point3D mittelPunkt = new Point3D(3*radius, 0.0 , 0.0);
			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());
			LightRay testRay = new LightRay();
			
			boolean check = testSphere.doesIntersectRay(testRay);
			
			assertTrue(check);	
		}
		
		@Test
		public void testDoesNotIntersectRay() {
			double radius = 5.0;
			Point3D mittelPunkt = new Point3D(3*radius, 4*radius , 0.0);
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());
			LightRay testRay = new LightRay();
			
			boolean check = testSphere.doesIntersectRay(testRay);
			assertFalse(check);	
		}
		@Test
		public void testDoesNotIntersectRay_02() {
			double radius = 5.0;
			ColorCalculation testShading = new BasicColorCalculation(0.1);

			Point3D mittelPunkt = new Point3D(3*radius, 4*radius , 0.0);
			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());
			LightRay testRay = new LightRay(new Point3D(),new Dir3D(10.0, 0.0, 0.0));
			
			boolean check = testSphere.doesIntersectRay(testRay);
			assertFalse(check);	
		}
	}
}
