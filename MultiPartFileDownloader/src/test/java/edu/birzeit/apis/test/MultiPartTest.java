package edu.birzeit.apis.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import edu.birzeit.apis.MultiPart;
import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.InvalidManifestURLException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;

public class MultiPartTest {

    @Test(expected = InvalidManifestURLException.class)
    public void testUnreachanleOpenStream() throws InvalidManifestURLException, ManifestReaderException,
            UnreachableURLException, ManiFestParserException, InvalidInputException, IOException {
        String url = "http://host.bzu.edu/unreachable/manifestSample";
        MultiPart.openStream(url);
    }

    @Test
    public void testReachableOpenStream() {

        String url = "https://gist.githubusercontent.com/HadiAwad/0b8b94585261b1b8c04fdc5a80569c46/raw/f2bff7bfbfb31a1378131faaa69b6002e5991752/test.txt.segments";
        InputStream inptStream = null;
        try {
            inptStream = MultiPart.openStream(url);
        } catch (Exception e) {
            Assert.assertTrue("Exception has been thrown while trying to open stream!", false);
        }

        try {
            ByteArrayOutputStream dest = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int readStream = 0;
            while (readStream != -1) {
                dest.write(buffer, 0, readStream);
                readStream = inptStream.read(buffer);
            }

            Assert.assertTrue("Reading the input stream produced an empty string so we should fail!",
                    !dest.toString().isEmpty());

        } catch (Exception e) {
            Assert.assertTrue("Exception has been thrown while trying to read from input stream!", false);
        }
    }

}
