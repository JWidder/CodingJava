package spikes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import stringHandling.ColorValue;

public class Spike_EnumToString {
	@Test
	public void spike_StringToEnum() {
		ColorValue test = ColorValue.valueOf("RED");
		assertEquals(test.getRedValue(),255);
		assertEquals(test.getGreenValue(),59);
		assertEquals(test.getBlueValue(),48);
	}
}
