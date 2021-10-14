package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import scene.Sphere3D;

public class UtilTest {

	/**
	 * This is a trivial test. Purpose: Ensure test coverage. 
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUtil() throws Exception{
		Util testUtil = new Util();
				
		assertEquals(Util.class, testUtil.getClass());
	}
	
	@Test
	public void testDot() throws Exception {
		Dir3D a1 = new Dir3D(1.0,1.0,1.0);
		Dir3D b1 = new Dir3D(1.0,1.0,1.0);
		assertEquals(3.0,Util.dot(a1, b1),0.0001);

		Dir3D a2 = new Dir3D(1.0,0.0,0.0);
		Dir3D b2 = new Dir3D(0.0,1.0,0.0);
		assertEquals(0.0,Util.dot(a2, b2),0.0001);
	}

	@Test
	public void testDifference() throws Exception {
		// arrange
		Dir3D a1 = new Dir3D(10.0,10.0,10.0);
		Dir3D b1 = new Dir3D(0.0,1.0,2.0);

		//act 
		Dir3D actual=Util.difference(a1, b1);
		
		//assert
		assertEquals(10.0,actual.getxDir(),0.00001);
		assertEquals(9.0,actual.getyDir(),0.00001);
		assertEquals(8.0,actual.getzDir(),0.00001);
	}

	@Test
	public void testDoesIntersect_Intersection() throws Exception {
		//arrange
		Point3D basePoint = new Point3D(-1.0,0.0,0.0);
		Dir3D direection = new Dir3D(1.0,0,0.0);
		Vector3D ray = new Vector3D(basePoint, direection);
		
		Point3D mittelPunkt = new Point3D(2.0,0.0,0.0);
		double radius = 1.0;
		Sphere3D sphere = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);
		//act 
		// boolean actual = Util.doesIntersect(ray, sphere);
		boolean actual = sphere.doesIntersectRay(ray);
				
		//assert
		assertEquals(true, actual);
	}

	@Test
	public void testDoesIntersect_NotIntersection() throws Exception {
		//arrange
		Point3D basePoint = new Point3D(-1.0,0.0,0.0);
		Dir3D direection = new Dir3D(0.0,1,0.0);
		Vector3D ray = new Vector3D(basePoint, direection);
		
		Point3D mittelPunkt = new Point3D(2.0,0.0,0.0);
		double radius = 1.0;
		Sphere3D sphere = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);
		//act 
		// boolean actual = Util.doesIntersect(ray, sphere);
		boolean actual = sphere.doesIntersectRay(ray);
				
		//assert
		assertEquals(false, actual);
	}


	@Test
	public void testDoesIntersect_Touch() throws Exception {
		//arrange
		Point3D basePoint = new Point3D(-1.0,0.0,0.0);
		Dir3D direection = new Dir3D(0.0,1,0.0);
		Vector3D ray = new Vector3D(basePoint, direection);
		
		Point3D mittelPunkt = new Point3D(1.0,0.0,0.0);
		double radius = 1.0;
		Sphere3D sphere = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN);
		//act 
		// boolean actual = Util.doesIntersectRay(ray, sphere);
		boolean actual = sphere.doesIntersectRay(ray);
				
		//assert
		assertEquals(false, actual);
	}

	
	@Test
	public void testGetLength() throws Exception {
		// arrange
		Dir3D vector = new Dir3D(-2.0,2.0,2.0);
		
		// act 
		double actual = Util.getLength(vector);
		
		// assert
		assertEquals(2.0*Math.sqrt(3.0), actual,0.0001);
	}

	@Test
	public void testNormalVector() throws Exception {
		Dir3D test1=new Dir3D(1.0,0.0,0.0);
		Dir3D test2=new Dir3D(0.0,1.0,0.0);
		
		Dir3D result=Util.normalVector(test1, test2);
		
		 assertEquals(result.getxDir(), 0.0, 0.0001);
		 assertEquals(result.getyDir(), 0.0, 0.0001);
		 assertEquals(result.getzDir(), 1.0, 0.0001);
	}

	@Test
	public void testReflect_45() throws Exception {
		Dir3D ri = new Dir3D(1.0, 0.0, -1.0);
		Dir3D n  = new Dir3D(0.0, 0.0, 1.0);
		
		ri=ri.normalize();
		n = n.normalize();
		
		Dir3D _reflect = Util.reflect(n, ri);
		
		assertEquals(_reflect.getxDir(), 0.5*Math.sqrt(2), 0.0001 );
		assertEquals(_reflect.getyDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getzDir(), 0.5*Math.sqrt(2), 0.0001 );
	}
	@Test
	public void testReflect_0() throws Exception {
		Dir3D ri = new Dir3D(0.0, 0.0, -1.0);
		Dir3D n  = new Dir3D(0.0, 0.0,  1.0);
		
		ri=ri.normalize();
		n = n.normalize();
		
		Dir3D _reflect = Util.reflect(n, ri);
		
		assertEquals(_reflect.getxDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getyDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getzDir(), 1.0, 0.0001 );
	}
}
