package edu.birzeit.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public static ByteArrayOutputStream writeBufferedReaderToBytes(InputStream stream)
            throws IOException, InvalidInputException {

        if (stream == null) {
            LOG.error("bufferReader was null! cannot read URL");
            throw new InvalidInputException("bufferReader was null! cannot read URL");
        }

        try {
            byte[] buffer = new byte[8192];
            int length;
            ByteArrayOutputStream writer = new ByteArrayOutputStream();
            while ((length = stream.read(buffer)) != -1) {
                LOG.debug("buffer, {} ", buffer);
                writer.write(buffer, 0, length);
            }
            LOG.debug("length is, {} ", writer.size());
            return writer;
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception was thrown while reading the content of buffered reader");
            throw e;
        } finally {
            stream.close();
        }
    }
}
