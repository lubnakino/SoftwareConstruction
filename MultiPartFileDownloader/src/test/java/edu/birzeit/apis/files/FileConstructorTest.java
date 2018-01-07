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
	 * Test method for {@link edu.birzeit.apis.files.FileConstructor#fetchURLsAndGatherStream(java.util.Map)}.
	 */
	@Test
	public void testFetchURLsAndGatherStream() {

		try {
			//             1.read content
			String validURL = "https://gist.githubusercontent.com/HadiAwad/0b8b94585261b1b8c04fdc5a80569c46/raw/f2bff7bfbfb31a1378131faaa69b6002e5991752/test.txt.segments";
			ManifestReader manifestReader = new ManifestReader(validURL);
			String urlContent = manifestReader.readManifestFile(validURL);

			// 2. parse content
			ManifestParser manifestParser = new ManifestParser();
			Map<String, Segment> segmentsMap = manifestParser.parseManifestURLContent(urlContent);

			InputStream inptStream = fileConstructor.fetchURLsAndGatherStream(segmentsMap);
			System.out.println(inptStream.toString());
			Assert.assertNotNull("Provide a valid segmentsMap and expect a not null inputStream", inptStream);

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

	@Test
	public void testFetchURLsAndGatherStreamWithManualyAddedSegments() {
		Map<String, Segment> segmentsMap = new HashMap<String, Segment>();

		try {

			Segment segment1 = new Segment();
			LinkedList<String>list1 = new LinkedList<>();
			list1.add("http://machine2.birzeit.edu/picture.jpg-segment1");
			segment1.setMainUrl("http://machine1.birzeit.edu/picture.jpg-segment1");
			segment1.setUrlMirrors(list1);

			Segment segment2 = new Segment();
			LinkedList<String>list2 = new LinkedList<>();
			list1.add("http://machine1.birzeit.edu/picture.jpg-segment2");
			segment2.setMainUrl("http://machine2.birzeit.edu/picture.jpg-segment2");
			segment2.setUrlMirrors(list2);
			segmentsMap.put("segment-1", segment1);
			segmentsMap.put("segment-2", segment2);

			InputStream inptStream = fileConstructor.fetchURLsAndGatherStream(segmentsMap);
			System.out.println(inptStream.toString());
			Assert.assertNotNull("Provide a valid segmentsMap and expect a not null inputStream", inptStream);

		} catch (UnreachableURLException | IOException | InvalidInputException e) {
			e.printStackTrace();
			fail("Test failed, throws exception for valid segmentsMap");

		}
	}
}
