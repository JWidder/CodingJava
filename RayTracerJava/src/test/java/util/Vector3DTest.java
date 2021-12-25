package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Vector3DTest {

	@Test
	public void testGetter() throws Exception {
		Point3D testPoint = new Point3D(-1.0,0.0,1.0);
		Dir3D testDir = new Dir3D(0.0,1.0,2.0);
		
		Vector3D testVector3d = new Vector3D(testPoint,testDir);
		
		assertEquals(-1.0, testVector3d.getBasis().xPos,0.0001);	
		assertEquals(0.0, testVector3d.getBasis().yPos,0.0001);	
		assertEquals(1.0, testVector3d.getBasis().zPos,0.0001);	
	}

	@Test
	public void testGetPoint() throws Exception {
		// given
		Point3D start=new Point3D(0.0,0.0,0.0);
		Dir3D dir = new Dir3D(1.0,2.0,3.0);
		Vector3D vector = new Vector3D(start, dir);
						
		// action
		Point3D actual = vector.getPoint(2.0);		
		
		// assert
		assertEquals(2.0, actual.getxPos(),0.0001);
		assertEquals(4.0, actual.getyPos(),0.0001);
		assertEquals(6.0, actual.getzPos(),0.0001);
	}
	 
}
