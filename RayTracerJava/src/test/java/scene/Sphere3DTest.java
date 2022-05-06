package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import generator.Intersection;
import util.BasicColorCalculation;
import util.ColorCalculation;
import util.ColorValue;
import util.Dir3D;
import util.Material;
import util.Point3D;
import util.TypeIntersection;

/**
 * UnitTest Klasse Spere3D
 * 
 * @author Johannes Widder
 *
 */
public class Sphere3DTest {

    @DisplayName("Test 1 Should calculate the correct sum")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, sum={2}")	
    @CsvSource({
        "1, 1, 2 , 0.0 , 0.0, 0.0, ",
        "2, 3, 5"
    })
    void sum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }
    
    @DisplayName("Test Intersection Spere Lightray. Spere in the center of coordination system")
    @ParameterizedTest (name="{index} => no: {0} Start({1}|{2}|{3}) Dir({4}|{5}|{6}) Schnitt:({7}|{8}|{9}) Typ:{10}  Stats:{11}")
    @CsvFileSource(resources = "/TestIntersectSphere.csv", numLinesToSkip = 0)
    void testIntersection(double x_point,double y_point,double z_point,double x_dir,double y_dir,double z_dir, double x_pos,double y_pos,double z_pos,String typ,String status) {
		double radius = 2.0;
		Point3D mittelPunkt = new Point3D(0.0, 0.0, 0.0);
		Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
		ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

		Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
		LightRay testRay = new LightRay(new Point3D(x_point,y_point,z_point), new Dir3D(x_dir,y_dir,z_dir));

		Intersection result = sphere.intersectRay(testRay);
		
		switch(typ) {
			case "INTERSECTION":
			case "TOUCH":
				assertEquals(x_pos, result.getIntersectionPoint().getxPos());
				assertEquals(y_pos, result.getIntersectionPoint().getyPos());
				assertEquals(z_pos, result.getIntersectionPoint().getzPos());
				assertEquals(typ, result.getTypeIntersection().toString());
				assertEquals(status, result.getStatusIntersection().toString());
				break;
			case "MISS":
				assertEquals(null,result.getIntersectionPoint());
				break;
		}
    }
    

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
	    @DisplayName("Test 2 Should calculate the correct sum")
	    @ParameterizedTest (name="x_point{0},y_point{1},z_point{2}  x_dir{3},y_dir{4},z_dir{5}  x_pos{6},y_pos{7},z_pos{8} ")
	    @CsvFileSource(resources = "/TestIntersectSphere.csv", numLinesToSkip = 1)
	    void sum1(double x_point, double y_point, double z_point, double x_dir, double y_dir, double z_dir, double x_pos, double y_pos, double z_pos)  {
	        assertEquals(x_pos,x_pos);
	    }
		/**
		 * Designbeschreibung siehe Dokument
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

//		@Test
//		@ExtendWith(MockitoExtension.class)
//		public void test_2a_LightRayTouchesSphere() throws Exception {
//			double radius = 1.0;
//
//			Point3D mittelPunkt = new Point3D(radius, radius, 0.0);
//			Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
//			ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 
//
//			Sphere3D sphere = new Sphere3D(mittelPunkt, radius, ColorValue.GREEN,testShading,testMaterial);
//			LightRay testRay = new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));
//
//			Intersection result = sphere.intersectRay(testRay);
//			
//			assertEquals(radius,result.getParameter(), 0.00001);
//			assertEquals(sphere,result.getRefElement());
//			assertEquals(TypeIntersection.TOUCH, result.getTypeIntersection());
//			assertEquals(StatusIntersection.INTERSECT, result.getStatusIntersection());
//			
//			//TODO Add does intersect 
//		}

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

		@Nested
		@ExtendWith(MockitoExtension.class)
		class testMoveShere{
			@Test
			public void test_moveSphereNormal() {
				Material testMaterial = Mockito.mock(Material.class, RETURNS_DEEP_STUBS);
				ColorCalculation testShading = Mockito.mock(ColorCalculation.class,RETURNS_DEEP_STUBS); 

				double radius=2.0;
				Point3D mittelPunkt = new Point3D(0.0,0.0,0.0);
				Sphere3D testSphere = new Sphere3D(mittelPunkt,radius,ColorValue.BLACK,testShading,testMaterial);
				
				LightRay testLightRay = new LightRay(new Point3D(-2*radius,0.0,0.0),new Point3D(-radius,0.0,0.0));

				// Abstand in Ursprungslage
				testSphere.move(new Dir3D (0.0,0.0,0.0));
				Intersection testIntersection = testSphere.intersectRay(testLightRay);
				assertEquals (2.0,testIntersection.getParameter());

				// Verschieben
				testSphere.move(new Dir3D (1.0,0.0,0.0));
				testIntersection = testSphere.intersectRay(testLightRay);
				assertEquals (3.0,testIntersection.getParameter());

				// Zurückbewegen
				testSphere.move(new Dir3D (-1.0,0.0,0.0));
				testIntersection = testSphere.intersectRay(testLightRay);
				assertEquals (2.0,testIntersection.getParameter());
			}
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
