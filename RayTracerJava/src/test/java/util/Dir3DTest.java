package util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Dir3DTest {

	@Test
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

}
