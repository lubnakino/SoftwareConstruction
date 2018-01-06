package edu.birzeit.validators;

import org.junit.Assert;
import org.junit.Test;

public class URLValidatorTest {

    URLValidator urlValidator = new URLValidator();

    @Test
    public void testValidteURL() {
        boolean result = urlValidator.validteURL("http://www.google.com");
        Assert.assertTrue("Provided url should be valid and  result should be true", result);
    }

    @Test
    public void testInvalidURL() {
        boolean result = urlValidator.validteURL("test");
        Assert.assertTrue("Provided url should be invalid and result should be false", !result);
    }

    @Test
    public void testIsManifestURLValid() {
    }

}
