/**
 * 
 */
package edu.birzeit.apis.files;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import ch.qos.logback.classic.net.SyslogAppender;

import java.util.Map;

import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.ManiFestParserException;
import edu.birzeit.exceptions.ManifestReaderException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.parsers.ManifestParser;
import edu.birzeit.parsers.ManifestReader;
import edu.birzeit.structures.Segment;

/**
 * @author AhdRadwan
 *
 */

public class FileConstructorTest {
	FileConstructor fileConstructor = new FileConstructor();;
	/**
	 * Test method for {@link edu.birzeit.apis.files.FileConstructor#fetchURLsAndGatherStream(Segment)}.
	 */
	@Test
	public void testFetchURLsAndGatherStream() {

		try {
			//             1.read content
			String validURL = "https://raw.githubusercontent.com/HadiAwad/SoftwareConstruction/master/TestingURLs/test-urls1.segments";
			ManifestReader manifestReader = new ManifestReader(validURL);
			String urlContent = manifestReader.readManifestFile(validURL);

			// 2. parse content
			ManifestParser manifestParser = new ManifestParser();
			Map<String, Segment> segmentsMap = manifestParser.parseManifestURLContent(urlContent);
			Segment segement = (Segment)segmentsMap.values().toArray()[0];
			BufferedReader bufferedReader = fileConstructor.fetchURLsAndGatherStream(segement);
			System.out.println("bufferedReaderbufferedReaderbufferedReader" + segement);
			Assert.assertNotNull("Provide a valid segmentsMap and expect a not null inputStream", bufferedReader);

		} catch (UnreachableURLException | IOException | InvalidInputException e) {
			e.printStackTrace();
			fail("Test failed, throws exception for valid segmentsMap");

		} catch (ManiFestParserException e) {
			fail("Test failed, throws exception for valid segmentsMap");			
			e.printStackTrace();
		} catch (ManifestReaderException e) {
			fail("Test failed, throws exception for valid segmentsMap");
			e.printStackTrace();
		}}


	/**
	 * Test method for {@link edu.birzeit.apis.files.FileConstructor#fetchURLsAndGatherStream(Segment)}.
	 *  With invalid segments URLs and expected  UnreachableURLException exception
	 */
	@Test (expected = UnreachableURLException.class)
	public void testFetchURLsAndGatherStreamWithManualyAddedInvalidSegments() throws UnreachableURLException, IOException, InvalidInputException {

		Segment segment1 = new Segment();
		LinkedList<String>list1 = new LinkedList<>();
		list1.add("http://machine2.birzeit.edu/picture.jpg-segment1");
		segment1.setMainUrl("http://machine1.birzeit.edu/picture.jpg-segment1");
		segment1.setUrlMirrors(list1);

		BufferedReader bufferedReader = fileConstructor.fetchURLsAndGatherStream(segment1);
		Assert.assertNotNull("Provide am valid segmentsMap and expect a not null inputStream", bufferedReader);

	}

	/**
	 * Test method for {@link edu.birzeit.apis.files.FileConstructor#fetchURLsAndGatherStream(Segment)}.
	 *  With valid segments and expect to success.
	 */
	@Test
	public void testFetchURLsAndGatherStreamWithManualyAddedSegments() {

		try {

			Segment segment1 = new Segment();
			LinkedList<String>list1 = new LinkedList<>();
			list1.add("http://machine1.birzeit.edu/picture.png-segment3");
			segment1.setMainUrl("https://github.com/HadiAwad/SoftwareConstruction/blob/master/TestingURLs/full-Image.png-segment3?raw=true");
			segment1.setUrlMirrors(list1);

			BufferedReader inptStream = fileConstructor.fetchURLsAndGatherStream(segment1);
			System.out.println(inptStream.toString());
			Assert.assertNotNull("Provide a valid segmentsMap and expect a not null inputStream", inptStream);

		} catch (UnreachableURLException | IOException | InvalidInputException e) {
			e.printStackTrace();
			fail("Test failed, throws exception for valid segmentsMap");

		}
	}

}
