package qube.test;

// import static org.junit.Assert.*;

import org.apache.commons.math3.random.JDKRandomGenerator;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well512a;
import org.junit.Test;

import qube.QTest;
import qube.TestRunner;
import qube.rngs.RANDU;

public class TestMain {

	@Test
	public void test() {
		TestRunner t = new TestRunner(new QTest[] { new FrequencyTest(), new MonteCarloTest(), new CompressionTest() });

		RandomGenerator[] generators = { new RANDU(), new MersenneTwister(), new JDKRandomGenerator(), new Well512a() };

		for (RandomGenerator r : generators) {
			System.out.println("Test results for " + r.getClass().getCanonicalName());
			byte[] bytes = new byte[200 * 1024 * 1024];
			r.nextBytes(bytes);
			t.runSuite(bytes);
			System.out.println();
		}
	}

}
