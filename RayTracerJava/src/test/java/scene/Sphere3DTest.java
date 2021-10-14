package scene;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.ColorValue;
import util.Dir3D;
import util.Point3D;
import util.Util;
import util.Vector3D;

public class Sphere3DTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testSphere3D() throws Exception {
		Point3D mittelPunkt = new Point3D();
		double radius = 1.0;
		
		Sphere3D testSphere3d = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);
		
		assertEquals(1.0, testSphere3d.getRadius(),0.001);
	}

	@Test
	public void testGetterSetter() throws Exception {
		// preparation
		double radius=1.0;
		Point3D center = new Point3D(1.0,2.0,3.0);
		
		// action
		Sphere3D testSphere = new Sphere3D(center, radius,ColorValue.GREEN);
		
		// assert
		assertEquals(1.0, testSphere.getRadius(),0.0001);
		assertEquals(1.0, testSphere.getMittelPunkt().getxPos(),0.0001);
		assertEquals(2.0, testSphere.getMittelPunkt().getyPos(),0.0001);
		assertEquals(3.0, testSphere.getMittelPunkt().getzPos(),0.0001);
	}
	
	@Test
	public void testIntersection() throws Exception{
		Point3D mittelPunkt = new Point3D(5.0,0.0,0.0);
		double radius = 2.5;
		Sphere3D sphere = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);

		Point3D cameraPos = new Point3D(-5.0,0.0,0.0);
		Point3D screenPoint = new Point3D(0.0,0.0,0.0); 
		Dir3D direction = Util.difference(screenPoint, cameraPos);
		
		Vector3D testRay = new Vector3D(cameraPos,direction);
		
		double result = sphere.intersectRay(testRay);
		assertEquals(result,1.5,0.00001);
	}

	@Test
	public void testIntersectionInner() throws Exception{
		Point3D mittelPunkt = new Point3D(5.0,0.0,0.0);
		double radius = 7.5;
		Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN);

		Point3D cameraPos = new Point3D(-5.0,0.0,0.0);
		Point3D screenPoint = new Point3D(0.0,0.0,0.0); 
		Dir3D direction = Util.difference(screenPoint, cameraPos);
		
		Vector3D testRay = new Vector3D(cameraPos,direction);

		double result = sphere.intersectRay(testRay);
		assertEquals(result,0.5,0.00001);
	}

	@Test
	public void testIntersectionNone() throws Exception{
		new Point3D(0.0,0.0,0.0);
		new Dir3D(0.0,1.0,0.0);
		
		Point3D mittelPunkt = new Point3D(10.0,0.0,0.0);
		double radius = 5.0;
		Sphere3D spere = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);
		
		Vector3D testRay = new Vector3D();

		double result=spere.intersectRay(testRay);
		assertEquals(result, 5.0,0.00001);
	}
}
