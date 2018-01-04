package edu.birzeit.validators;

import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.utils.StringUtils;
import edu.birzeit.utils.URLUtils;

/**
 * This function will contain all methods used in URL validation
 * 
 * @author Hadi-Awad
 *
 */
public class URLValidator {
    private final Logger log = LoggerFactory.getLogger(URLValidator.class);

    private final static String MANIFEST_URL_SUFFIX = ".segments";

    private final static String MANIFEST_URL_CONTENT_TYPE = "text/segments-manifest";

    /**
     * Will validate the url. Only basic validator
     * 
     * @param url
     * @return
     */
    public boolean validteURL(String url) {

        if (StringUtils.isBlank(url)) {
            log.info("String is empty! URL validation failed. ");
            return false;
        }

        // DEFAULT schemes = "http","https"
        String[] schemes = { "http", "https" };
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid(url) == false) {
            log.info("Validation of URL has failed by common HTTP validator!");
            return false;
        }

        log.info("Validation of URL has PASSED!");
        return true;
    }

    /**
     * This function will check if the URL is a real manifest url thats to say
     * either it ends with .segment suffix or its content type is
     * text/segments-manifest
     * 
     * @param manifestURL
     * @return
     */
    public boolean isManifestURLValid(String manifestURL) {
        log.debug("isManifestURLValid started for the following URL {}.", manifestURL);
        if (validteURL(manifestURL) == false) {
            log.warn("manifest URL is not a valid URL {}.", manifestURL);
            return false;
        }

        if (manifestURL.endsWith(MANIFEST_URL_SUFFIX)
                || MANIFEST_URL_CONTENT_TYPE.equals(URLUtils.openURLConnectionAndGetContentType(manifestURL))) {
            log.debug("manifest URL is  a valid Manifest URL {}.", manifestURL);
            return true;
        }
        log.warn("validation failed for the URL {}.", manifestURL);
        return false;
    }
}
