/**
 * 
 */
package edu.birzeit.parsers;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;

/**
 * Test {@link edu.birzeit.parsers.ManifestReader}.
 * @author AhdRadwan
 *
 */
public class ManifestReaderTest {
	String validInputURL = "https://raw.githubusercontent.com/HadiAwad/SoftwareConstruction/master/TestingURLs/test-urls1.segments";
	ManifestReader reader = new ManifestReader(validInputURL);
	
	/**
	 * Test method for {@link edu.birzeit.parsers.ManifestReader#readManifestFile(java.lang.String)}.
	 * Test with valid URL of a valid file content.
	 * Expect not null string.
	 */
	@Test
	public void testReadManifestFile() {
		try {
			String content = reader.readManifestFile(validInputURL);
			assertNotNull("Teset Pass, succsfully read a valid file",content);
		} catch (ManifestReaderException | UnreachableURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Test fails,"+ e.getClass().getName() + "for a valid url with valid content type.");
		}
	}
	
	/**
	 * Test method for {@link edu.birzeit.parsers.ManifestReader#readManifestFile(java.lang.String)}.
	 * Test with invalid URL.
	 * Expect not null string.
	 * @throws UnreachableURLException 
	 * @throws ManifestReaderException 
	 */
	@Test(expected = UnreachableURLException.class)
	public void testReadManifestFileWithInvalidURL() throws ManifestReaderException, UnreachableURLException {
			String content = reader.readManifestFile("https://this/is/invalid/url");
			assertNotNull("Teset Pass, succsfully read a valid file",content);
	}


}
