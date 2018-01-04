package edu.birzeit.parsers;

import java.io.BufferedReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.utils.StringUtils;
import edu.birzeit.utils.URLUtils;

/**
 * This function will have a function to read URL content into different I/O
 * formats
 * 
 * @author Hadi-Awad
 *
 */
public class ManifestReader {
    private static final Logger LOG = LoggerFactory.getLogger(ManifestReader.class);

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ManifestReader(String url) {
        setUrl(url);
    }

    /**
     * Read file and get the content as text
     * 
     * @param url
     *            input url as String
     * @return
     * @throws ManifestReaderException
     * @throws UnreachableURLException
     */
    public String readManifestFile(String url) throws ManifestReaderException, UnreachableURLException {
        if (StringUtils.isBlank(url)) {
            LOG.warn("Blank URL was provided ");
            throw new ManifestReaderException("Blank URL was provided");
        }

        setUrl(url);

        LOG.info("Started reading the content of the following URL {}", url);

        StringBuilder stringBuilder = new StringBuilder();

        BufferedReader bufferReader = URLUtils.getUrlContentAsBufferReader(url);

        if (bufferReader == null) {
            LOG.warn("bufferReader was null! cannot read URL");
            throw new UnreachableURLException("Blank URL was provided");
        }

        String line;
        try {
            while ((line = bufferReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("Exception was thrown while reading the content of the following URL {}", url, e);
            throw new ManifestReaderException("IO Exception while reading file content. message = " + e.getMessage());
        }

        return stringBuilder.toString();
    }
}
