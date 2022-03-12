package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Johannes Widder
 *
 */
public class Point3DTest {

	/**
	 * @author Johannes Widder
	 *
	 */
	@Nested
	public class TestKonstruktor
	{
		/**
		 * 
		 */
		@Test 
		public void testDefaultKonstruktor()
		{
			Point3D testPoint = new Point3D();
			
			assertEquals(0.0, testPoint.getxPos(),0.0001);
			assertEquals(0.0, testPoint.getyPos(),0.0001);
			assertEquals(0.0, testPoint.getzPos(),0.0001);
			
		}

		/**
		 * 
		 */
		@Test 
		public void testParameterKonstruktor()
		{
			Point3D testPoint = new Point3D(1.0,2.0,3.0);
			
			assertEquals(1.0, testPoint.getxPos(),0.0001);
			assertEquals(2.0, testPoint.getyPos(),0.0001);
			assertEquals(3.0, testPoint.getzPos(),0.0001);
			
		}

	}
		
	@Nested
	class test_MovePoint{
		Point3D testPoint;

		@BeforeEach
		public void createTestPoint()
		{
			this.testPoint = new Point3D();			
		}
		
		@Test
		public void testMovePoint() {	
			this.testPoint.movePoint(1.0, 2.0, 3.0 );
			
			assertEquals(1.0, this.testPoint.getxPos(),0.0001);
			assertEquals(2.0, this.testPoint.getyPos(),0.0001);
			assertEquals(3.0, this.testPoint.getzPos(),0.0001);
		}	

		@Test
		public void testMovePointDir3D() {	
			this.testPoint.movePoint(new Dir3D());
			
			assertEquals(1.0, this.testPoint.getxPos(),0.0001);
			assertEquals(0.0, this.testPoint.getyPos(),0.0001);
			assertEquals(0.0, this.testPoint.getzPos(),0.0001);
		}	
	}
	
	@Nested
	class Test_GetterSetter{
		Point3D testPoint;

		@BeforeEach
		public void createTestPoint()
		{
			this.testPoint = new Point3D();			
		}

		@Test
		public void  testGetterSetter_x()
		{
			this.testPoint.setxPos(1.0);
			assertEquals(1.0, this.testPoint.getxPos(),0.001);
			assertEquals(0.0, this.testPoint.getyPos(),0.001);
			assertEquals(0.0, this.testPoint.getzPos(),0.001);			
		}

		@Test
		public void  testGetterSetter_y()
		{
			this.testPoint.setyPos(1.0);
			assertEquals(0.0, this.testPoint.getxPos(),0.001);
			assertEquals(1.0, this.testPoint.getyPos(),0.001);
			assertEquals(0.0, this.testPoint.getzPos(),0.001);			
		}
		@Test
		public void  testGetterSetter_z()
		{
			this.testPoint.setzPos(1.0);
			assertEquals(0.0, this.testPoint.getxPos(),0.001);
			assertEquals(0.0, this.testPoint.getyPos(),0.001);
			assertEquals(1.0, this.testPoint.getzPos(),0.001);			
		}

	}
}
