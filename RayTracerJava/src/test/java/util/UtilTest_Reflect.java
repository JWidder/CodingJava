package util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * @author johan
 *
 */
public class UtilTest_Reflect {
	/**
	 * @throws Exception
	 */
	@Test
	public void testReflect_45() throws Exception {
		Dir3D ri = new Dir3D(1.0, 0.0, -1.0);
		Dir3D n  = new Dir3D(0.0, 0.0, 1.0);
		
		ri=ri.normalize();
		n = n.normalize();
		
		Dir3D _reflect = Util.reflect(n, ri);
		
		assertEquals(_reflect.getxDir(), 0.5*Math.sqrt(2), 0.0001 );
		assertEquals(_reflect.getyDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getzDir(), 0.5*Math.sqrt(2), 0.0001 );
	}
	
	/**
	 * @throws Exception
	 */
	@Test
	public void testReflect_0() throws Exception {
		Dir3D ri = new Dir3D(0.0, 0.0, -1.0);
		Dir3D n  = new Dir3D(0.0, 0.0,  1.0);
		
		ri=ri.normalize();
		n = n.normalize();
		
		Dir3D _reflect = Util.reflect(n, ri);
		
		assertEquals(_reflect.getxDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getyDir(), 0.0, 0.0001 );
		assertEquals(_reflect.getzDir(), 1.0, 0.0001 );
	}
}
