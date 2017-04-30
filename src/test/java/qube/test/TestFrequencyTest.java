package qube.test;

import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.Random;

import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well512a;
import org.junit.Test;

import qube.QTest;
import qube.TestRunner;
import qube.rngs.RANDU;

public class TestFrequencyTest {

	@Test
	public void test() {
		TestRunner t = new TestRunner(new QTest[] {new FrequencyTest(), new MonteCarloTest()});
		RandomGenerator r = new Well512a();
		byte[] bytes = new byte[200 * 1024 * 1024];
		r.nextBytes(bytes);
		t.runSuite(bytes);
	}

}
