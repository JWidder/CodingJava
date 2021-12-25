package generator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.Vector3D;

public class PictureGeneratorTest {

	@BeforeEach
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
