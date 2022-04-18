package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generator.Intersection;
import util.ColorValue;
import util.Dir3D;
import util.Material;
import util.BasicColorCalculation;
import util.Point3D;
import util.TypeIntersection;
import util.ColorCalculation;

/**
 * UnitTest Klasse Spere3D
 * 
 * @author Johannes Widder
 *
 */
public class Sphere3DTest {

	@Nested
	class Test_Constructor{
		@Test
		@ExtendWith(MockitoExtension.class)
		public final void testSphere3D() throws Exception {
			Point3D mittelPunkt = new Point3D();
			double radius = 1.0;
			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);

			ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);

			Sphere3D testSphere3d = new Sphere3D(mittelPunkt, radius,ColorValue.GREEN,testShading,new Material());
			
			assertEquals(1.0, testSphere3d.getRadius(),0.001);
		}
	}
	
	@Nested
	class Test_GetterSetter{
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testBasicGetterSetter() throws Exception {
			// preparation
			double radius=1.0;
			Point3D center = new Point3D(1.0,2.0,3.0);
			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);

			ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);

			// action
			Sphere3D testSphere = new Sphere3D(center, radius,ColorValue.GREEN,testShading,new Material());
			
			// assert
			assertEquals(1.0, testSphere.getRadius(),0.0001);
			assertEquals(1.0, testSphere.getMittelPunkt().getxPos(),0.0001);
			assertEquals(2.0, testSphere.getMittelPunkt().getyPos(),0.0001);
			assertEquals(3.0, testSphere.getMittelPunkt().getzPos(),0.0001);
		}		
	}
	
	/**
	 * Test Schnitt LightRay Sphere different cases 
	 * 
	 * @author Johannes Widder
	 */
	@Nested
	class Test_Intersection {
		
		/**
		 * Designbeschreibung siehe dokuemnt
		 * @throws Exception 
		 */
		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_1a_LightRayMissesSphere() throws Exception {

			double radius = 1.0;
			Point3D mittelPunkt = new Point3D(-2*radius, 2*radius, 0.0);

			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0, 0.0, 0.0), new Dir3D(1.0,0,0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(Double.MAX_VALUE,result.getParameter(), 0.00001);
			assertEquals(null,result.getRefElement());
			assertEquals(TypeIntersection.MISSES, result.getTypeIntersection());
			assertEquals(StatusIntersection.MISS, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_2a_LightRayTouchesSphere() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(radius, radius, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(radius,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.TOUCH, result.getTypeIntersection());
			assertEquals(StatusIntersection.INTERSECT, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_2b_LightRayTouchesBehindSphere() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(-radius, radius, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(-radius,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.TOUCH, result.getTypeIntersection());
			assertEquals(StatusIntersection.MISS, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_2c_LightRayStartsTouchingOnSphere() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(0.0, radius, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(0,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.TOUCH, result.getTypeIntersection());
			assertEquals(StatusIntersection.MISS, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_3a_LightRayStandardIntersection() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(2*radius, 0.0, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(radius,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.INTERSECTION, result.getTypeIntersection());
			assertEquals(StatusIntersection.INTERSECT, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_3b_LightRayBehindIntersection() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(-2*radius, 0.0, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(-radius,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.BEHIND_INTERSECTION, result.getTypeIntersection());
			assertEquals(StatusIntersection.MISS, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_3c_LightRayInnerIntersection() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(radius,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.INNER_INTERSECTION, result.getTypeIntersection());
			assertEquals(StatusIntersection.INTERSECT, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

		@Test
		@ExtendWith(MockitoExtension.class)
		public void test_3d_LightRayStartOnSphere() throws Exception {
			double radius = 1.0;

			Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
			LightRay testRay = new LightRay(new Point3D(radius,0.0,0.0), new Dir3D(1.0,0.0,0.0));

			Intersection result = sphere.intersectRay(testRay);
			
			assertEquals(Double.MAX_VALUE,result.getParameter(), 0.00001);
			assertEquals(sphere,result.getRefElement());
			assertEquals(TypeIntersection.BEHIND_TOUCH, result.getTypeIntersection());
			assertEquals(StatusIntersection.MISS, result.getStatusIntersection());
			
			//TODO Add does intersect 
		}

	}

	/**
	 * @author Johanns Widder
	 */
//	@Nested
//	class Test_DoesIntersectRay{
//		/**
//		 * Situation:
//		 * 
//		 * Schnitt mit einer Kugel im Abstand 2*Radius. Der Strahl zeigt direkt auf die Kugel.
//		 */
//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_LightRayIntersectsSphere() {
//			double radius = 5.0;
//			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);
//
//			ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);
//
//			Point3D mittelPunkt = new Point3D(3*radius, 0.0 , 0.0);
//			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());
//			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0),new Dir3D(1.0,0.0,0.0));
//			
//			boolean doesIntersect = testSphere.intersectRay(testRay).getIntersectionPoint()==null;
//			
//			assertTrue(doesIntersect);	
//		}
//		
//		/**
//		 * Der Lichtstrahl berührt die Kugel tangential.
//		 */
//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_LightRayTouchesSphere() {
//			
//			double radius = 5.0;
//			
//			Point3D mittelPunkt = new Point3D(radius, radius , 0.0);
//			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS);
//			Material testMaterial = Mockito.mock(Material.class,RETURNS_DEEP_STUBS);
//			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
//
//			LightRay testLightRay = new LightRay(new Point3D(0.0,0.0,0.0),new Dir3D(1.0,0.0,0.0));
//			
//			boolean doesIntersect = testSphere.doesIntersectRay(testLightRay);
//			
//			assertTrue(doesIntersect);	
//		}
//		
//		/**
//		 * Der Strahl schneidet die Kugel nicht, sondern get daneben vorbei.
//		 */
//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_LightRayTouchesSphereBehind() {
//			// Arrange
//			double radius = 5.0;
//			Point3D mittelPunkt = new Point3D(-1*radius, radius , 0.0);
//			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS);
//			Material testMaterial = Mockito.mock(Material.class,RETURNS_DEEP_STUBS);
//
//			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
//			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0),new Dir3D(1.0,0.0,0.0));
//
//			// Act
//			boolean doesIntersect = testSphere.doesIntersectRay(testRay);
//			
//			// Assert
//			assertFalse(doesIntersect);	
//		}
//		
//		/**
//		 * Der Mittelpunkt des Strahl liegt innerhalb der Kugel.
//		 * 
//		 * Es wird false zurückgegeben, weil dies im Normalfall nicht als Schnittpunkt behandelt wird.
//		 * dazu wird aber ein gültiger Schnittpunkt zurückgegeben. So kann man diesen Fall unterscheiden.    
//		 *  
//		 * TODO Klären, Wie ist der Fall zu behandeln, dass Startpunkt des Strahl innerhalb der Kugel liegt.
//		 */
//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_LightRayStartsWithinSphere() {
//			double radius = 1.0;
//			Scene testScene= Mockito.mock(Scene.class, RETURNS_DEEP_STUBS);
//
//			ColorCalculation testShading = new BasicColorCalculation(0.1,0.5,testScene);
//
//			Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
//			Sphere3D testSphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,new Material());
//			LightRay testRay = new LightRay(mittelPunkt,new Dir3D(1.0, 0.0, 0.0));
//			
//			boolean doesIntersect = testSphere.doesIntersectRay(testRay);
//			
//			assertFalse(doesIntersect);	
//		}
//		/**
//		 * Der Mittelpunkt des Strahl liegt innerhalb der Kugel.
//		 * 
//		 * Es wird false zurückgegeben, weil dies im Normalfall nicht als Schnittpunkt behandelt wird.
//		 * dazu wird aber ein gültiger Schnittpunkt zurückgegeben. So kann man diesen Fall unterscheiden.    
//		 *  
//		 * TODO Klären, Wie ist der Fall zu behandeln, dass Startpunkt des Strahl innerhalb der Kugel liegt.
//		 */
//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_ShereBehindStartLightray() {
//			// TODO Test Sphere hinter Strahl ausprogrammieren. 
//			assertFalse(false);
//		}
//	}
}
