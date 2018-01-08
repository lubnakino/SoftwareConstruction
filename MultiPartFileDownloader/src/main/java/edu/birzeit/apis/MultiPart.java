package edu.birzeit.apis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.apis.files.FileConstructor;
import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.InvalidManifestURLException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.parsers.ManifestParser;
import edu.birzeit.parsers.ManifestReader;
import edu.birzeit.structures.Segment;
import edu.birzeit.utils.InputOutputUtils;
import edu.birzeit.validators.URLValidator;

/**
 * The main entry point of the application. the design pattern used is singleton
 * and the public API can be called as MultiPart.openStream(inputURL);
 * 
 * @author Hadi-Awad
 *
 */
public final class MultiPart {

    private static final String FILE_NAME_PREFIX = "SCP-";

    private static final Logger LOG = LoggerFactory.getLogger(MultiPart.class);

    private static MultiPart _instance = new MultiPart();

    private URLValidator urlValidator;

    private FileConstructor fileConstructor;

    private MultiPart() {
        urlValidator = new URLValidator();
        fileConstructor = new FileConstructor();
    }

    public FileConstructor getFileConstructor() {
        return fileConstructor;
    }

    public void setFileConstructor(FileConstructor fileConstructor) {
        this.fileConstructor = fileConstructor;
    }

    // Get the only object available
    public static MultiPart getInstance() {
        return _instance;
    }

    public URLValidator getUrlValidator() {
        return urlValidator;
    }

    public void setUrlValidator(URLValidator urlValidator) {
        this.urlValidator = urlValidator;
    }

    /**
     * The main API of the application so far. As being described, this will be
     * handling the URLs and check whether they point to manifest files or not
     * according to url or content type
     * 
     * @param url
     *            the manifest URL
     * 
     * @return Input Stream of the manifest file
     * @throws InvalidManifestURLException
     * @throws UnreachableURLException
     * @throws ManifestReaderException
     * @throws ManiFestParserException
     * @throws InvalidInputException
     * @throws IOException
     */
    public static InputStream openStream(String url) throws InvalidManifestURLException, ManifestReaderException,
            UnreachableURLException, ManiFestParserException, IOException, InvalidInputException {

        String fileName = FILE_NAME_PREFIX + Math.random() + ".png";
        // +
        // URLUtils.getFileTypeFromURL(segmentsMap.values().iterator().next().getMainUrl());

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        InputStream inputStream = new FileInputStream(fileName);

        LOG.debug("File Name generated is {}", fileName);
        _openStream(writer, url, fileName);
        writer.close();
        return inputStream;

    }

    private static void _openStream(BufferedWriter writer, String url, String fileName)
            throws InvalidManifestURLException, ManifestReaderException, UnreachableURLException,
            ManiFestParserException, IOException, InvalidInputException {

        LOG.info("Open Stream was called with the following URL {}", url);
        if (_instance.getUrlValidator().isManifestURLValid(url) == false) {
            throw new InvalidManifestURLException("Invalid Manifest URL Exception");
        }

        // 1.read content
        LOG.debug("Validation Passed for the following URL {}", url);
        ManifestReader manifestReader = new ManifestReader(url);
        String urlContent = manifestReader.readManifestFile(url);

        // 2. parse content
        ManifestParser manifestParser = new ManifestParser();
        Map<String, Segment> segmentsMap = manifestParser.parseManifestURLContent(urlContent);

        // 3. read contents
        for (Segment segment : segmentsMap.values()) {
            if (_instance.getUrlValidator().isManifestURLValid(segment.getMainUrl()) == true) {
                _openStream(writer, url, fileName);
            } else {
                BufferedReader bufferReader = _instance.getFileConstructor().fetchURLsAndGatherStream(segment);
                boolean readWriteResult = InputOutputUtils.writeBufferedReaderToBytes(bufferReader, writer);
                writer.flush();
            }
        }
    }

}
