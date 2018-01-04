package edu.birzeit.apis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.InvalidManifestURLException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;

public class MultipartRunner {
    private static final Logger LOG = LoggerFactory.getLogger(MultipartRunner.class);

    public static void main(String[] args) {

        String inputURL = "https://gist.githubusercontent.com/HadiAwad/0b8b94585261b1b8c04fdc5a80569c46/raw/f2bff7bfbfb31a1378131faaa69b6002e5991752/test.txt.segments";
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
        }
    }

}
