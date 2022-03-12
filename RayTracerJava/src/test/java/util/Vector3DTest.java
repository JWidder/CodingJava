package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * @author Johannes Widder
 *
 */
public class Vector3DTest {

	@Nested
	class test_constructor {
		@Test
		public void test_basic_constructor() {
			Vector3D testVector3d = new Vector3D();

			assertEquals(testVector3d.getBasis().getxPos(), 0);
			assertEquals(testVector3d.getBasis().getyPos(), 0);
			assertEquals(testVector3d.getBasis().getzPos(), 0);

			assertEquals(testVector3d.getDirection().getxDir(), 1.0);
			assertEquals(testVector3d.getDirection().getyDir(), 0.0);
			assertEquals(testVector3d.getDirection().getzDir(), 0.0);
		}
	}

	@Nested
	class test_getter_setter {
		/**
		 * @throws Exception
		 */
		@Test
		public void testGetter() throws Exception {
			Point3D testPoint = new Point3D(-1.0, 0.0, 1.0);
			Dir3D testDir = new Dir3D(0.0, 1.0, 2.0);

			Vector3D testVector3d = new Vector3D(testPoint, testDir);

			assertEquals(-1.0, testVector3d.getBasis().xPos, 0.0001);
			assertEquals(0.0, testVector3d.getBasis().yPos, 0.0001);
			assertEquals(1.0, testVector3d.getBasis().zPos, 0.0001);

			assertEquals(testVector3d.getDirection().getxDir(), 0.0);
			assertEquals(testVector3d.getDirection().getyDir(), 1.0);
			assertEquals(testVector3d.getDirection().getzDir(), 2.0);
		}

		/**
		 * @throws Exception
		 */
		@Test
		public void testGetPoint() throws Exception {
			// given
			Point3D start = new Point3D(0.0, 0.0, 0.0);
			Dir3D dir = new Dir3D(1.0, 2.0, 3.0);
			Vector3D vector = new Vector3D(start, dir);

			// action
			Point3D actual = vector.getPoint(2.0);

			// assert
			assertEquals(2.0, actual.getxPos(), 0.0001);
			assertEquals(4.0, actual.getyPos(), 0.0001);
			assertEquals(6.0, actual.getzPos(), 0.0001);
		}
	}

}
