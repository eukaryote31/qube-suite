package qube.rngs;

import org.apache.commons.math3.random.RandomGenerator;

public class RANDU implements RandomGenerator {
	int prevval = 1;

	@Override
	public void setSeed(int seed) {
		prevval = seed;
	}

	@Override
	public void setSeed(int[] seed) {
		prevval = seed[0];
	}

	@Override
	public void setSeed(long seed) {
		prevval = (int) seed;

	}

	@Override
	public void nextBytes(byte[] bytes) {
	}

	@Override
	public int nextInt() {
		prevval *= 65539;
		
		return prevval;
	}

	@Override
	public int nextInt(int n) {
	       if (n <= 0)
	            throw new IllegalArgumentException("n must be positive");
	
	        if ((n & -n) == n)  // i.e., n is a power of 2
	            return (int)((n * (long)nextInt()) >> 31);
	
	        int bits, val;
	        do {
	            bits = nextInt(31);
	            val = bits % n;
	        } while (bits - val + (n-1) < 0);
	        return val;
	}

	@Override
	public long nextLong() {
		return ((long)(nextInt()) << 32) + nextInt();
	}

	@Override
	public boolean nextBoolean() {
		return (nextInt() & 0x1000) != 0;
	}

	@Override
	public float nextFloat() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double nextDouble() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double nextGaussian() {
		throw new UnsupportedOperationException();
	}

}
