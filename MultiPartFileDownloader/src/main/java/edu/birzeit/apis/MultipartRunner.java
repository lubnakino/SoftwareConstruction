package edu.birzeit.apis;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.InvalidManifestURLException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;

public class MultipartRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MultipartRunner.class);

    public static void main(String[] args) {

        String inputURL = "https://raw.githubusercontent.com/HadiAwad/SoftwareConstruction/master/TestingURLs/test-urls1.segments";
        LOG.info("inputURL {}", inputURL);
        try {
            MultiPart.openStream(inputURL);
        } catch (InvalidManifestURLException e) {
            e.printStackTrace();
            LOG.error("Invalid Manifest URL Exception was thrown for the following URL {}", inputURL, e);
        } catch (ManifestReaderException e) {
            e.printStackTrace();
            LOG.error("Manifest Reader Exception was thrown for the following URL {}", inputURL, e);
        } catch (UnreachableURLException e) {
            e.printStackTrace();
            LOG.error("Unreachable URL Exception was thrown for the following URL {}", inputURL, e);
        } catch (ManiFestParserException e) {
            e.printStackTrace();
            LOG.error("ManiFest Parser Exception was thrown for the following URL {}", inputURL, e);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("IO Exception was thrown for the following URL {}", inputURL, e);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            LOG.error("Invalid Input Exception was thrown for the following URL {}", inputURL, e);
        }
    }

}
