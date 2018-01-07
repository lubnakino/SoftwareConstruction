package edu.birzeit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.InvalidInputException;

public class InputOutputUtils {
    private static final Logger LOG = LoggerFactory.getLogger(InputOutputUtils.class);

    public static String writeBufferedReaderToString(BufferedReader bufferReader)
            throws IOException, InvalidInputException {

        StringBuilder stringBuilder = new StringBuilder();

        if (bufferReader == null) {
            LOG.error("bufferReader was null! cannot read URL");
            throw new InvalidInputException("bufferReader was null! cannot read URL");
        }

        String line;
        try {
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception was thrown while reading the content of buffered reader");
            throw e;
        } finally {
            bufferReader.close();
        }
    }

    public static ArrayList<Integer> writeBufferedReaderToBytes(BufferedReader bufferReader)
            throws IOException, InvalidInputException {

        ArrayList<Integer> result = new ArrayList<Integer>();
        if (bufferReader == null) {
            LOG.error("bufferReader was null! cannot read URL");
            throw new InvalidInputException("bufferReader was null! cannot read URL");
        }

        int charAsInt;
        try {
            while ((charAsInt = bufferReader.read()) != -1) {
                result.add(charAsInt);
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception was thrown while reading the content of buffered reader");
            throw e;
        } finally {
            bufferReader.close();
        }
    }
}
