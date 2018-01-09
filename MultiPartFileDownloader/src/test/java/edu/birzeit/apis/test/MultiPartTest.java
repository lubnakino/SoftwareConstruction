package edu.birzeit.apis.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.apis.MultiPart;
import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.InvalidManifestURLException;
import edu.birzeit.exceptions.InvalidSegmentURLException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;

public class MultiPartTest {
    private static final Logger LOG = LoggerFactory.getLogger(MultiPartTest.class);

    @Test(expected = InvalidManifestURLException.class)
    public void testUnreachanleOpenStream()
            throws InvalidManifestURLException, ManifestReaderException, UnreachableURLException,
            ManiFestParserException, InvalidInputException, IOException, InvalidSegmentURLException {
        String url = "http://host.bzu.edu/unreachable/manifestSample";
        MultiPart.openStream(url);
    }

    @Test
    public void testReachableOpenStream() {

        String url = "https://raw.githubusercontent.com/HadiAwad/SoftwareConstruction/master/TestingURLs/test-urls.txt.segments";
        InputStream inptStream = null;
        try {
            inptStream = MultiPart.openStream(url);
        } catch (Exception e) {
            LOG.error("Error", e);
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
