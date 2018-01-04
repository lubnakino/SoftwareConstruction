package edu.birzeit.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.utils.StringUtils;
import edu.birzeit.validators.URLValidator;

/**
 * This function will have a function to parse the URL content after reading it
 * 
 * @author Hadi-Awad
 *
 */
public class ManifestParser {

    private static final Logger LOG = LoggerFactory.getLogger(ManifestParser.class);

    private static final String LINE_DELIMITER = "**";

    private static final String KEY_MAP = "segment-";

    private URLValidator urlValidator;

    public ManifestParser() {
        urlValidator = new URLValidator();
    }

    /**
     * 
     * This function will return a list of objects that contains the URLs of the
     * manifest file
     * 
     * @param content
     * @throws ManiFestParserException
     */
    public void parseManifestURLContent(String content) throws ManiFestParserException {
        LOG.debug("Parse Manifest URL Content was called with following Content {}", content);
        if (StringUtils.isBlank(content)) {
            LOG.warn("Blank Content was provided ");
            throw new ManiFestParserException("Blank Content was provided");
        }

        LOG.trace(" \n*** content {}", content);

        String[] splittedSections = content.split("[\\s]+");

        Map<String, ArrayList<String>> segmentsURLs = new HashMap<String, ArrayList<String>>();
        int idx = 1;
        for (String line : splittedSections) {
            if (StringUtils.isNotBlank(line)) {
                String key = KEY_MAP + idx;
                if (line.equals(LINE_DELIMITER)) {
                    idx++;
                } else if (urlValidator.validteURL(line)) {
                    ArrayList<String> arrayList = segmentsURLs.get(key);
                    if (arrayList == null) {
                        arrayList = new ArrayList<String>();
                        segmentsURLs.put(key, arrayList);
                    }
                    arrayList.add(line);
                } else {
                    throw new ManiFestParserException(
                            "Manifest File contains something other than URLs, invalid line is: " + line);
                }
            }
        }
        LOG.info(" \n*** content {}", segmentsURLs);

    }
}
