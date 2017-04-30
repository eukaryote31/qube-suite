package qube;

public interface QTest {
	/**
	 * This method should test the data and return a double indicating how
	 * successful the test was.
	 */
	public double test(byte[] data);

	/**
	 * This method should take the score given by {@link QTest#test(byte[])} and
	 * determine if the value indicates a success.
	 */
	public boolean isSuccess(double testResult);

	/**
	 * The name to display on the report
	 */
	public String getTestName();
}
