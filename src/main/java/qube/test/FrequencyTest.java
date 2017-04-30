package qube.test;

import qube.QTest;

public class FrequencyTest implements QTest {

	@Override
	public double test(byte[] data) {
		long[] frequencies = new long[256];
		long total = 0;

		for (byte b : data) {
			// bitmask to get rid of sign
			frequencies[b & 0xFF]++;
			total++;
		}

		final double expected = 1d / 256;

		double error = 0;
		for (int i = 0; i < 256; i++) {
			error += Math.abs((double) frequencies[i] / total - expected);
		}

		return error;
	}

	@Override
	public boolean isSuccess(double testResult) {
		return testResult < 0.001;
	}

	@Override
	public String getTestName() {
		return "Byte Frequency";
	}

}
