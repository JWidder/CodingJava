package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import util.Dir3D;
import util.Point3D;

/**
 * @author johan
 *
 */
@DisplayName("Test der Klasse LightRay")
public class LightRayTest {

	@Nested
	class test_CaldulatePoint
	{
		ILightRay testLightRay;
		@BeforeEach
		void createTestRay()
		{
			this.testLightRay= new LightRay(new Point3D(0.0,0.0,0.0), new Dir3D(1.0,0.0,0.0));
		}
		
		@Test
		public void testSimpleParameter()
		{
			Point3D test = this.testLightRay.getPoint(2.0);	
			assertEquals(2.0, test.getxPos());
			assertEquals(0.0, test.getyPos());
			assertEquals(0.0, test.getzPos());
		}
			
		@Test
		public void testIntParameter()
		{
			int test1 = 12;
			Point3D test = this.testLightRay.getPoint(test1);	
			assertEquals(12.0, test.getxPos());
			assertEquals(0.0, test.getyPos());
			assertEquals(0.0, test.getzPos());
		}
	}	
	
	@Nested
	class test_ValidateGetters
	{
		ILightRay testLightRay;
		Point3D testPoint;
		Dir3D testDir;
		@BeforeEach
		void createTestRay()
		{
			this.testPoint = new Point3D();
			this.testDir = new Dir3D();
			this.testLightRay= new LightRay(this.testPoint,this.testDir);
		}
		
		@Test
		public void testGetBasis() {
			assertEquals(this.testPoint,this.testLightRay.getBasis());
		}
		
		@Test
		public void testGetDirection() {
			assertEquals(this.testDir, this.testLightRay.getDirection());
		}
		
	}
}
