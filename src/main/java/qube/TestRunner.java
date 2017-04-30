package qube;

public class TestRunner {
	private QTest[] tests;

	public TestRunner(QTest[] tests) {
		this.tests = tests;
	}

	public void runSuite(byte[] data) {
		int successes = 0;
		int fails = 0;
		int total = tests.length;

		for (QTest t : tests) {
			System.out.print(t.getTestName() + ".. ");

			double result = t.test(data);
			if (t.isSuccess(result)) {
				System.out.println("PASS (" + result + ")");
				successes++;
			} else {
				System.out.println("FAIL (" + result + ")");
				fails++;
			}
		}

		System.out.println("Result: " + successes + " pass, " + fails + " failures, " + total + " total");
	}
}
