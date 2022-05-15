package scene;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Johannes Widder
 *
 */
public class SceneTest extends Scene {

	@Nested
	public class SceneTest_AddElement {
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testAddElementISceneElement() {
			Scene testScene = new Scene();
			
			ISceneElement refISceneElement1 = Mockito.mock(ISceneElement.class, RETURNS_DEEP_STUBS);
			ISceneElement refISceneElement2 = Mockito.mock(ISceneElement.class, RETURNS_DEEP_STUBS);
			ISceneElement refISceneElement3 = Mockito.mock(ISceneElement.class, RETURNS_DEEP_STUBS);
			ISceneElement refISceneElement4 = Mockito.mock(ISceneElement.class, RETURNS_DEEP_STUBS);

			testScene.addElement(refISceneElement1);
			testScene.addElement(refISceneElement2);
			testScene.addElement(refISceneElement3);
			testScene.addElement(refISceneElement4);
			
			assertEquals(4,testScene.getElements().size());
		}
	}

	@Nested
	public class SceneTest_AddLight {
		@Test
		@ExtendWith(MockitoExtension.class)
		public void testAddElementISceneElement() {
			Scene testScene = new Scene();
			
			ILight refILightElement1 = Mockito.mock(ILight.class, RETURNS_DEEP_STUBS);
			ILight refILightElement2 = Mockito.mock(ILight.class, RETURNS_DEEP_STUBS);
			ILight refILightElement3 = Mockito.mock(ILight.class, RETURNS_DEEP_STUBS);
			ILight refILightElement4 = Mockito.mock(ILight.class, RETURNS_DEEP_STUBS);

			testScene.addElement(refILightElement1);
			testScene.addElement(refILightElement2);
			testScene.addElement(refILightElement3);
			testScene.addElement(refILightElement4);
			
			assertEquals(4,testScene.getLights().size());
		}
	}

}
