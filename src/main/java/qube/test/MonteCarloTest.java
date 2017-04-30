package qube.test;

import java.util.Arrays;

import qube.QTest;

public class MonteCarloTest implements QTest {

	@Override
	public double test(byte[] data) {
		double[] vals = doublesFromBytes(data);
		
		long incircle = 0;
		long outcircle = 0;
		
		for(int i = 0; i < (vals.length / 2) * 2; i += 2) {
			double dist = Math.hypot(vals[i], vals[i + 1]);
			if(dist < 1)
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
		// use only some of the bytes
		int iters = len / 16;
		
		double[] ret = new double[iters];
		
		for(int i = 0; i < iters; i++) {
			ret[i] = ((double)toInt(data, i * 16) / Integer.MAX_VALUE);
		}
		
		return ret;
	}
	
	// returns long to prevent sign being wrong
	private static long toInt(byte[] bytes, int offset) {
		  long ret = 0;
		  for (int i=0; i < 4 && i + offset < bytes.length; i++) {
		    ret <<= 8;
		    ret |= (int)bytes[i + offset] & 0xFF;
		  }
		  return ret & 0x7FFFFFFF;
		}

}
