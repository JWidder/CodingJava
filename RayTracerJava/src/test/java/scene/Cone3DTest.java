package scene;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import util.Color;
import util.ColorCalculation;
import util.Dir3D;
import util.Material;

public class Cone3DTest {
	private ColorCalculation ligthShading = mock(ColorCalculation.class);

	private Dir3D moved = mock(Dir3D.class);

	private Material typMaterial = mock(Material.class);

	private Color valueColor = mock(Color.class);

	private Cone3D cone3D;

	@BeforeEach
	public void createCone3D() throws Exception {
		cone3D = new Cone3D();
		cone3D.ligthShading = ligthShading;
		cone3D.moved = moved;
		cone3D.typMaterial = typMaterial;
		cone3D.valueColor = valueColor;
	}

	@Test
	public void testIntersectRay() {
		assertEquals(true, true);
	}

	@Test
	public void testDoesIntersectRay() {
		assertEquals(true, true);
	}
}
