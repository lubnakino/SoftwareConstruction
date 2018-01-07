package edu.birzeit.tests.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.birzeit.apis.test.MultiPartTest;
import edu.birzeit.parsers.ManifestParserTest;
import edu.birzeit.utils.StringUtilsTest;
import edu.birzeit.validators.URLValidatorTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ MultiPartTest.class, ManifestParserTest.class, StringUtilsTest.class, URLValidatorTest.class })
public class MultiPartTestSuite {
}
