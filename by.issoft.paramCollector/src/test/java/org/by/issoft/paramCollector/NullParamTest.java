package org.by.issoft.paramCollector;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.by.issoft.paramCollector.reflection.ObtainerRegistry;
import org.junit.Test;

/**
 * Unit test checking if result .
 */
// user junit 4
public class NullParamTest {

	@Test
	public void testNullParam() {
		// assertTrue( true );

		List<ParamObtainer<?>> obtainers = ObtainerRegistry.getObtainers();
		for (ParamObtainer<?> obtainer : obtainers) {

			// use assert not null

			assertNotNull(obtainer.getCurrentParamValue());

		}
	}
}
