package spikes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import stringHandling.BasicException;
import stringHandling.ExampleExceptionHandling;

class Spike_ExceptionHandling {

	@Test
	void spike_BasicExcepton() {
		ExampleExceptionHandling test = new ExampleExceptionHandling();
		double result;
		try {
			result = test.calculateResult(2.0);
			assertEquals(4, result);
		} catch (BasicException e) {
			assertTrue(false);
		}
	}

}
