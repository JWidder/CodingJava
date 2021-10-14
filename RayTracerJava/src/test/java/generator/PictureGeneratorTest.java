package generator;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import util.Vector3D;

public class PictureGeneratorTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreatePicture() throws Exception {
		PictureGenerator testPictureGenerator = new PictureGenerator(800,600);
		testPictureGenerator.createPicture(1);
		assertEquals(1,1);
	}

	@Test
	public void testGetNextRay() throws Exception {
		Vector3D ray = new Vector3D();
		assertEquals(1,1);
	}
}
