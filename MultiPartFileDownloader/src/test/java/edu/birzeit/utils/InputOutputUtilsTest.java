/**
 * 
 */
package edu.birzeit.utils;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import edu.birzeit.apis.MultiPart;
import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.structures.Segment;

/**
 * Test cases for {@link edu.birzeit.utils.InputOutputUtils}
 * 
 * @author AhdRadwan
 *
 */
public class InputOutputUtilsTest {

    /**
     * Test method for
     * {@link edu.birzeit.utils.InputOutputUtils#writeBufferedReaderToBytes(java.io.BufferedReader)}.
     * 
     * @throws FileNotFoundException
     */
    @Test
    public void testWriteBufferedReaderToBytes() throws FileNotFoundException {

        Segment segment = new Segment();
        LinkedList<String> list1 = new LinkedList<>();
        list1.add("http://machine1.birzeit.edu/picture.png-segment3");
        segment.setMainUrl(
                "https://github.com/HadiAwad/SoftwareConstruction/blob/master/TestingURLs/full-Image.png-segment3?raw=true");
        segment.setUrlMirrors(list1);

        InputStream inputStream;
        try {
            // init bufferReader
            inputStream = MultiPart.getInstance().getFileConstructor().fetchURLsAndGatherStream(segment);
            // Test writeBufferedReaderToBytes.
            ByteArrayOutputStream partialContent = InputOutputUtils.writeBufferedReaderToBytes(inputStream);
            Assert.assertNotNull("Teset Pass, succsfully writes bufferedReader to bytes", partialContent);

        } catch (UnreachableURLException | IOException | InvalidInputException e1) {
            // TODO Auto-generated catch block
            fail("Throws " + InvalidInputException.class.getName() + "For valid bufferReader");
        }

    }

    /**
     * Test method for
     * {@link edu.birzeit.utils.InputOutputUtils#writeBufferedReaderToBytes(java.io.BufferedReader)}.
     * Test with Null bufferReader and expect a InvalidInputException.
     * 
     * @throws InvalidInputException
     * @throws IOException
     */
    @Test(expected = InvalidInputException.class)
    public void testWriteBufferedReaderToBytesWithNullBufferReader() throws IOException, InvalidInputException {

        InputStream inputStream = null;
        ByteArrayOutputStream partialContent = InputOutputUtils.writeBufferedReaderToBytes(inputStream);
        System.out.println(partialContent.toString());
    }

}
