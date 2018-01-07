package edu.birzeit.tests.suite;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiPartTestSuiteRunner {

    private static final Logger LOG = LoggerFactory.getLogger(MultiPartTestSuiteRunner.class);

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MultiPartTestSuite.class);

        for (Failure failure : result.getFailures()) {
            LOG.error(failure.toString());
        }

        LOG.info("The test suite execution of the MultipPart was ", result.wasSuccessful());
    }
}
