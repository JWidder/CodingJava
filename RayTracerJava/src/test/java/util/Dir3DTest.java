package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Johannes Widder
 *
 */
public class Dir3DTest {

	/**
	 * @throws Exception
	 */
	@Test
	@DisplayName("Simple Constructor")
	public void testDir3D() throws Exception {
		// prepare
		Dir3D testDir3D = new Dir3D();

		// action
		testDir3D.setxDir(1.0);
		testDir3D.setyDir(2.0);
		testDir3D.setzDir(3.0);

		double actualxValue=testDir3D.getxDir();
		double actualyValue=testDir3D.getyDir();
		double actualzValue=testDir3D.getzDir();

		// assert
		assertEquals(1.0, actualxValue,0.0001);
		assertEquals(2.0, actualyValue,0.0001);
		assertEquals(3.0, actualzValue,0.0001);
	}

	/**
	 * @throws Exception
	 */
	@Test
	public void testDir3DDoubleDoubleDouble() throws Exception {
		Dir3D testDir3D = new Dir3D(4.0,5.0,6.0);

		double actualxValue=testDir3D.getxDir();
		double actualyValue=testDir3D.getyDir();
		double actualzValue=testDir3D.getzDir();

		// assert
		assertEquals(4.0, actualxValue,0.0001);
		assertEquals(5.0, actualyValue,0.0001);
		assertEquals(6.0, actualzValue,0.0001);
	}

	/**
	 * 
	 */
	@Test
	public void testLen() {
		Dir3D testDir=new Dir3D(1.0,0.0,0.0);
		assertEquals(testDir.len(),1.0,0.0001);

		testDir=new Dir3D(-2.0,0.0,0.0);
		assertEquals(testDir.len(),2.0,0.0001);

		testDir=new Dir3D(0.0,0.0,0.0);
		assertEquals(testDir.len(),0.0,0.0001);

		testDir=new Dir3D(4.0,4.0,0.0);
		assertEquals(testDir.len(),4.0*Math.sqrt(2.0),0.0001);
	}

	/**
	 * @author johan
	 *
	 */
	@Nested
	public class testNormalize{

		/**
		 * 
		 */
		@Test
		public void testNormalize1() {
			Dir3D testDir=new Dir3D(1.0,0.0,0.0);
			Dir3D result=testDir.normalize();
			assertEquals(result.len(),1.0,0.0001);
		}
		/**
		 * 
		 */
		@Test
		public void testNormalize2() {
			Dir3D testDir=new Dir3D(10.0,0.0,0.0);
			Dir3D result=testDir.normalize();
			assertEquals(result.len(),1.0,0.0001);
		}
		/**
		 * 
		 */
		@Test
		public void testNormalize3() {
			Dir3D testDir=new Dir3D(-10.0,0.0,0.0);
			Dir3D result=testDir.normalize();
			assertEquals(result.len(),1.0,0.0001);
			assertEquals(result.getxDir(),-1.0,0.001);
			assertEquals(result.getyDir(),0.0,0.001);
			assertEquals(result.getzDir(),0.0,0.001);
		}
	}

	/**
	 * 
	 */
	@Test
	public void testScale() {
		Dir3D test= new Dir3D(1.0,1.0,1.0);
		Dir3D result = test.scale(2.0);

		assertEquals(result.getxDir(), 2.0 , 0.001);
		assertEquals(result.getyDir(), 2.0 , 0.001);
		assertEquals(result.getzDir(), 2.0 , 0.001);
	}

	/**
	 * 
	 */
	@Test
	public void testMinus() {
		Dir3D test= new Dir3D(1.1,1.1,10.0);
		Dir3D delta= new Dir3D(1.1,1.0,1.0);
		Dir3D result = test.minus(delta);

		assertEquals(result.getxDir(), 0.0 , 0.001);
		assertEquals(result.getyDir(), 0.1 , 0.001);
		assertEquals(result.getzDir(), 9.0 , 0.001);
	}

	/**
	 * 
	 */
	@Test
	public void testPlus() {
		Dir3D test= new Dir3D(1.0,1.0,1.0);
		Dir3D delta= new Dir3D(1.1,1.0,10.0);
		Dir3D result = test.plus(delta);

		assertEquals(result.getxDir(), 2.1 , 0.001);
		assertEquals(result.getyDir(), 2.0 , 0.001);
		assertEquals(result.getzDir(), 11.0 , 0.001);
	}

	@Nested
	class getterSetter{
		Dir3D test;
		@BeforeEach
		void createDir() {
			this.test= new Dir3D(1.0,1.0,1.0);
		}

		@Test
		public void testxDir()
		{
			assertEquals(1.0, this.test.getxDir());
		}

	}

}
