package qube.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import qube.QTest;

public class CompressionTest implements QTest {

	@Override
	public double test(byte[] data) {
		int origlen = data.length;
		int compresslen = compress(data).length;
		
		return (double) compresslen / origlen;
	}

	@Override
	public boolean isSuccess(double testResult) {
		return testResult > 1;
	}

	@Override
	public String getTestName() {
		return "GZIP Compression";
	}

    private static byte[] compress(byte[] b) {
    	try {
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        GZIPOutputStream gzip = new GZIPOutputStream(out);
	        gzip.write(b);
	        gzip.close();
	        return out.toByteArray();
    	} catch(IOException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
}
