package qube.test;

import qube.QTest;

public class MonteCarloTest implements QTest {

	@Override
	public double test(byte[] data) {
		double[] vals = doublesFromBytes(data);

		long incircle = 0;
		long outcircle = 0;

		for (int i = 0; i < (vals.length / 2) * 2; i += 2) {
			
			// calculate the squared distance (d^2 = a^2 + b^2)
			// its okay  to use squared distance because sqrt(1) = 1
			// but its much faster (sqrt is slow)
			double sqdist = vals[i] * vals[i] + vals[i + 1] * vals[i + 1];
			if (sqdist < 1)
				incircle++;
			else
				outcircle++;
		}

		return (((double) incircle) / (outcircle + incircle)) * 4;
	}

	@Override
	public boolean isSuccess(double testResult) {
		return Math.abs(testResult - Math.PI) <= 0.01;
	}

	@Override
	public String getTestName() {
		return "Monte Carlo Approximation";
	}

	private double[] doublesFromBytes(byte[] data) {
		int len = data.length;

		// one group of 8 bytes out of every `skip * 8` bytes will be used per
		// double.
		final int skip = 2;

		int iters = len / (8 * skip);

		double[] ret = new double[iters];

		for (int i = 0; i < iters; i++) {
			ret[i] = ((double) toInt(data, i * 16) / Integer.MAX_VALUE);
		}

		return ret;
	}

	// returns long to prevent sign being wrong
	private static long toInt(byte[] bytes, int offset) {
		long ret = 0;
		for (int i = 0; i < 4 && i + offset < bytes.length; i++) {
			ret <<= 8;
			ret |= (int) bytes[i + offset] & 0xFF;
		}

		// skim off sign bit
		return ret & 0x7FFFFFFF;
	}

}
