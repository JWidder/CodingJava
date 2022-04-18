package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SceneTracerTest {
	@Mock
	private Scene inScene;
	@InjectMocks
	private SceneTracer sceneTracer;

	@Test
	public void testSceneTracer() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

	@Test
	public void testTraceLightRay() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

	@Test
	public void testIntersectRay() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

	@Test
	public void testGetNextRay() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

}
