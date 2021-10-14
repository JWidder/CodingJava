package util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class Point3DTest {

	@Test
	public void testPoint3D() {
		Point3D testPoint;
		
		testPoint = new Point3D(1.0,2.0,3.0);
		
		assertEquals(1.0, testPoint.getxPos(),0.0001);
		assertEquals(2.0, testPoint.getyPos(),0.0001);
		assertEquals(3.0, testPoint.getzPos(),0.0001);
	}

	@Test
	public void testMovePoint() {
		Point3D testPoint;
		
		testPoint = new Point3D();
		testPoint.movePoint(1.0, 2.0, 3.0 );
		
		assertEquals(1.0, testPoint.getxPos(),0.0001);
		assertEquals(2.0, testPoint.getyPos(),0.0001);
		assertEquals(3.0, testPoint.getzPos(),0.0001);
	}

}
