package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


/**
 * Test der Klasse Colour. 
 * 
 * @author Johannes Widder
 *
 */
public class ColorTest {

	@Nested
	class TestContructor{
		@Test
		public void testDefaultContructor() {
			Color refColor = new Color();
			
			assertEquals(0, refColor.getColorValues()[0]);
			assertEquals(0, refColor.getColorValues()[1]);
			assertEquals(0, refColor.getColorValues()[2]);
			
		}
	}
	
	@Nested
	class testSetColor{
		@Test
		public void testSetGetColor() {
			Color refColor = new Color();
			
			refColor.setColor(10,20,30);;
			
			assertEquals(10, refColor.getColorValues()[0]);
			assertEquals(20, refColor.getColorValues()[1]);
			assertEquals(30, refColor.getColorValues()[2]);
		}

		@Test
		public void testSetGetColorLimmited() {
			Color refColor = new Color();
			
			refColor.setColor(1010,1020,1030);
			
			assertEquals(255, refColor.getColorValues()[0]);
			assertEquals(255, refColor.getColorValues()[1]);
			assertEquals(255, refColor.getColorValues()[2]);
		}

		@Test
		public void testSetColorValue() {
			Color refColor = new Color();
			
			refColor.setColor(ColorValue.RED);;
			
			assertEquals(255, refColor.getColorValues()[0]);
			assertEquals(59, refColor.getColorValues()[1]);
			assertEquals(48, refColor.getColorValues()[2]);
		}

	}
	

	@Nested
	class testAddColor{
		@Test
		public void testAddColorNormal() {
			Color refColor = new Color(50,50,50);
			Color addColor = new Color(50,60,70);
			
			Color result = refColor.addColor(addColor);
			
			assertEquals(100, result.getColorValues()[0]);
			assertEquals(110, result.getColorValues()[1]);
			assertEquals(120, result.getColorValues()[2]);
		}
		
		@Test
		public void testAddColorLimit() {
			Color refColor = new Color(50,50,50);
			Color addColor = new Color(250,220,230);
			
			Color result = refColor.addColor(addColor);
			
			assertEquals(255, result.getColorValues()[0]);
			assertEquals(255, result.getColorValues()[1]);
			assertEquals(255, result.getColorValues()[2]);
		}
	}

	@Test
	public void testReflectColor() {
		Color testColor = new Color(50,60,70);
		
		Color result = testColor.reflectColor(0.1);
		
		assertEquals(5, result.getColorValues()[0]);
		assertEquals(6, result.getColorValues()[1]);
		assertEquals(7, result.getColorValues()[2]);
	}

	@Nested
	class Test_ReflectColor{
		@Test
		public void test_reflectColor_basic() {
			Color testColor = new Color(50,60,70);
			Color inColor=new Color(255,255,255);
			
			Color result = testColor.refelctColor(inColor);
			
			assertEquals(50, result.getColorValues()[0]);
			assertEquals(60, result.getColorValues()[1]);
			assertEquals(70, result.getColorValues()[2]);
			
		}
	}
}
