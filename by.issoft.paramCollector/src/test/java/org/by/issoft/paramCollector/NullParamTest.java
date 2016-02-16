package org.by.issoft.paramCollector;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test checking if result .
 */
// user junit 4
public class NullParamTest extends TestCase {
	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public NullParamTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NullParamTest.class);
	}

	public void testNullParam() {
		// assertTrue( true );

		List<ParamObtainer> obtainers = ObtainerRegistry.getObtainers();
		for (ParamObtainer obtainer : obtainers) {

			// use assert not null
			assertTrue(obtainer.getCurrentParamValue() != null);

		}
	}
}
