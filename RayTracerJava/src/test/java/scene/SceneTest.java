package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import util.Color;
import util.ColorValue;
import util.Dir3D;
import util.Intersection;
import util.BasicColorCalculation;
import util.Point3D;
import util.ReflectedColor;
import util.Util;

/**
 * @author Johannes Widder
 *
 */
public class SceneTest {

	/**
	 * 
	 */
	@SuppressWarnings("nls")
	@Test
	public void testBuildScene() {
		new Scene();
		String test = "kkvarun32 test";

		// StreamTokenizer streamTokenizer = new StreamTokenizer(
		// new StringReader("Mary had 1 little lamb..."));

		Set<String> keyList = new HashSet<String>();
		Pattern regex = Pattern.compile("#\\{(.*?)\\}");
		Matcher matcher1 = regex.matcher("Content goes here");
		while (matcher1.find()) {
			keyList.add(matcher1.group(1));
		}

		try {
			BufferedReader br = new BufferedReader(new StringReader(
					"sphere ( 12.0 , 12,12) 3\n" + "# Dies ist ein Test\n" + "box ( 0.1,0.2,0.3) (3.2,3.2,3.2)\n"));
			String line;
			while ((line = br.readLine()) != null) {
				line.split("\\s");
			}

			String testString = "shere ( 0.1,0.2,0.3 ) (3.2,3.2,3.2)\\n";
			testString.matches("sphere.?");
			testString.split(testString);

			String patternString1 = "box|shere\\s?\\(\\s?\\p{Digit}\\.?\\d?";

			Pattern pattern = Pattern.compile(patternString1);
			Matcher matcher = pattern.matcher("shere ( 0.1,0.2,0.3 ) (3.2,3.2,3.2)\n");

			if (matcher.find()) {
				System.out.println("Test1 " + matcher.group(0));
			} else {
				System.out.println("Nothing found");
			}
			System.out.println("Test");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertTrue(Pattern.matches("[a-zA-Z0-9]+ [a-zA-Z0-9]+", test));

		test.split("\\s+");
		test.split("[a-zA-Z0-9]+ [a-zA-Z0-9]+");

		String patternString = "[a-zA-Z0-9]+ [a-zA-Z0-9]+";

		Pattern pattern = Pattern.compile(patternString);

		pattern.matcher(test);

		// String test1 = matcher.group(0);

		assertEquals(1, 1);
	}

	/**
	 * 
	 */
	@SuppressWarnings("nls")
	@Test
	public void test_buildScreen_normal() {
		new BufferedReader(new StringReader("Sphere ( 12.0 , 12 , -12.3) , 3.1 , RED\n"
				+ "Sphere ( 12.1 , 12 , -12.3) , 5 , GREEN\n" + "Box (0.0,0.0,0.0) , 1.0,1.0,1.0 , GREEN"));

		new Scene();

		return;
	}

	/**
	 * 
	 */
	@SuppressWarnings("nls")
	@Test
	public void testGetKey() {
		Exception exception = assertThrows(ExceptionSyntaxError.class, () -> {
			Scene.getKey("Sphere)");
		});

		String testMessage = exception.getMessage();
		assertEquals("getKey_Sphere)", testMessage);

		result test = null;
		try {
			test = Scene.getKey("Sphere (Test)");
		} catch (ExceptionSyntaxError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(" (Test)", test.Remainder);
		assertEquals("Sphere", test.Key);

	}

	@Nested
	class testRayTracing {

		/**
		 * 
		 */
		@Test
		public void testTarceRay() {
			final double r = 2.0;
			final double d = 5.0 * r;

			Scene testScene = new Scene();
			ReflectedColor testShading = new BasicColorCalculation(0.1);
			 
			testScene.addElement(new Sphere3D(new Point3D(d, -r * 0.5 * Math.sqrt(2.0), 0), r, ColorValue.RED,testShading));
			testScene.addElement(new Sphere3D(new Point3D(d - Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading));
			testScene.addElement(new Sphere3D(new Point3D(d + Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading));

			testScene.addElement(new Light(1.0));

			LightRay testLightRay = new LightRay(new Point3D(0.0, 0.0, 0.0), new Dir3D(1.0, 0.0, 0.0));

			testScene.traceLightRay(testLightRay);
			return;
		}
		
		/**
		 * This tests emulates the recusive call sequence of recursive Ray Tracing. 
		 */
		@Test
		public void testSteps() {
			final double r = 2.0;
			final double d = 5.0 * r;

			Scene testScene = new Scene();
			testScene.setAmbientLight(new Color(25,25,25));
			ReflectedColor testShading = new BasicColorCalculation(0.1);

			Sphere3D testSphere3d1 =new Sphere3D(new Point3D(d, -r * 0.5 * Math.sqrt(2.0), 0), r, ColorValue.RED,testShading);
			testScene.addElement(testSphere3d1);
			Sphere3D testSphere3d2 =new Sphere3D(new Point3D(d - Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading); 
			testScene.addElement(testSphere3d2);
			Sphere3D testSphere3d3=new Sphere3D(new Point3D(d + Math.sqrt(2.0) * r, d + r * 0.5 * Math.sqrt(2.0), 0), r,ColorValue.RED,testShading);
			testScene.addElement(testSphere3d3);

			testScene.addElement(new Light(1.0));

			LightRay testLightRay1 = new LightRay(new Point3D(0.0, 0.0, 0.0), new Dir3D(1.0, 0.0, 0.0));
			Intersection testIntersection1 = testScene.intersectRay(testLightRay1);
			assertEquals(testIntersection1.getRefElement(), testSphere3d1);
			assertEquals(testIntersection1.getIntersectionPoint().getxPos(), d-0.5*Math.sqrt(2.0)*r);
			assertEquals(testIntersection1.getIntersectionPoint().getyPos(), 0.0);
			assertEquals(testIntersection1.getIntersectionPoint().getzPos(), 0.0);

			LightRay testLightRay2 = Util.getNextRay(testLightRay1, testIntersection1);
			Intersection testIntersection2 = testScene.intersectRay(testLightRay2);
			assertEquals(testIntersection2.getRefElement(), testSphere3d2);
			assertEquals(testIntersection2.getIntersectionPoint().getxPos(), d-0.5*Math.sqrt(2.0)*r,0.0001);
			assertEquals(testIntersection2.getIntersectionPoint().getyPos(), 10.0,0.0001);
			assertEquals(testIntersection2.getIntersectionPoint().getzPos(), 0.0);

			LightRay testLightRay3 = Util.getNextRay(testLightRay1, testIntersection2);
			Intersection testIntersection3 = testScene.intersectRay(testLightRay3);
			assertEquals(testIntersection3.getRefElement(), testSphere3d3);
			assertEquals(testIntersection3.getIntersectionPoint().getxPos(), d+0.5*Math.sqrt(2.0)*r,0.0001);
			assertEquals(testIntersection3.getIntersectionPoint().getyPos(), 10.0,0.0001);
			assertEquals(testIntersection3.getIntersectionPoint().getzPos(), 0.0);

			LightRay testLightRay4 = Util.getNextRay(testLightRay1, testIntersection3);
			Intersection testIntersection4 = testScene.intersectRay(testLightRay4);
			assertEquals(testIntersection4.getRefElement(), testSphere3d1);
			assertEquals(testIntersection4.getIntersectionPoint().getxPos(), d+0.5*Math.sqrt(2.0)*r,0.0001);
			assertEquals(testIntersection4.getIntersectionPoint().getyPos(), 0.0,0.0001);
			assertEquals(testIntersection4.getIntersectionPoint().getzPos(), 0.0);

			LightRay testLightRay5 = Util.getNextRay(testLightRay1, testIntersection4);

			Color testColor;
			
			// Abbilden des zweiten Teils der Funktion traceRay.
			{
				Intersection testIntersection5 = testScene.intersectRay(testLightRay5);
				assertEquals(testIntersection5.getRefElement(), null);
				assertEquals(testIntersection5.getIntersectionPoint(), null);
				
				testColor = testIntersection5.getValueColor(new Color(0,0,0));
				assertEquals(testColor.getColorValues()[0],0);
				assertEquals(testColor.getColorValues()[1],0);
				assertEquals(testColor.getColorValues()[2],0);			
			}
			
//			testColor = testIntersection4.getValueColor(testColor);
//			assertEquals(testColor.getColorValues()[0],25);
//			assertEquals(testColor.getColorValues()[1],25);
//			assertEquals(testColor.getColorValues()[2],25);
//
//			testColor = testIntersection3.getValueColor(testColor);
//			assertEquals(testColor.getColorValues()[0],45);
//			assertEquals(testColor.getColorValues()[1],45);
//			assertEquals(testColor.getColorValues()[2],45);
//
//			testColor = testIntersection2.getValueColor(testColor);
//			assertEquals(testColor.getColorValues()[0],61);
//			assertEquals(testColor.getColorValues()[1],61);
//			assertEquals(testColor.getColorValues()[2],61);
//
//			testColor = testIntersection1.getValueColor(testColor);
//			assertEquals(testColor.getColorValues()[0],73);
//			assertEquals(testColor.getColorValues()[1],73);
//			assertEquals(testColor.getColorValues()[2],73);
		}
	}

//	@Test
//	public void testGetSphere() {
//		try {
//			result _result = Scene.getShere("(12,12,12)");
//			assertEquals(_result, null);
//		} catch (ExceptionSyntaxError e) {
//			assertTrue(false);
//		}
//	    Exception exception = assertThrows(ExceptionSyntaxError.class, () -> {
//	    	Scene.getShere("(a12,12)");
//	    });
//		assertEquals(1,1);
//	}
//
}
