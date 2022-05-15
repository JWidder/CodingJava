package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
	public void testIntersectRaya() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

	@Test
	public void testGetNextRay() {
		// ToDo Test einbauen
		assertEquals(true,true);
	}

}
