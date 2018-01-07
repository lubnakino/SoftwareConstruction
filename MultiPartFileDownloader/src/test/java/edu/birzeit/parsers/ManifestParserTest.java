/**
 * 
 */
package edu.birzeit.parsers;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.sun.net.httpserver.Authenticator.Success;

import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.structures.Segment;

/**
 * Test edu.birzeit.parsers.ManifestParser
 * 
 * @author AhdRadwan
 *
 */
public class ManifestParserTest {
	ManifestParser parser = new ManifestParser();

	/**
	 * Test method for {@link edu.birzeit.parsers.ManifestParser#parseManifestURLContent(java.lang.String)}.
	 */
	@Test
	public void testParseManifestURLContentWithValidContent() {
		String fileContent = ""
				+ "http://machine1.birzeit.edu/picture.jpg-segment1\n"
				+ "http://machine2.birzeit.edu/picture.jpg-segment1\n"
				+ "**\n"
				+ "http://machine1.birzeit.edu/picture.jpg-segment2\n"
				+ "http://machine2.birzeit.edu/picture.jpg-segment2\n"
				+ "**\n"
				+ "http://machine1.birzeit.edu/picture.jpg-segment3\n"
				+ "http://machine2.birzeit.edu/picture.jpg-segment3\n"
				+ "";
	
		try {
			Map<String, Segment> segmants =	parser.parseManifestURLContent(fileContent);
			Assert.assertEquals("Provided a valid file content and the expexted number of sengements is 3", segmants.size(), 3 );
			System.out.println("segmat response" + segmants);

			
		} catch (ManiFestParserException e) {
			e.printStackTrace();
			fail();
		}

	}

	/**
	 * Test
	 * {@link edu.birzeit.parsers.ManifestParser#parseManifestURLContent(java.lang.String)}
	 * . with invalid segments file content. And expecting a
	 * ManiFestParserException.
	 *
	 */
	@Test (expected = ManiFestParserException.class)
	public void testParseManifestURLContentWithnValidContent() throws ManiFestParserException {
		String fileContent = "invalid segments";

		Map<String, Segment> segmants = parser.parseManifestURLContent(fileContent);
	}
}
