package edu.birzeit.utils;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test the string Utils.
 * 
 * @author AhdRadwan
 *
 */
public class StringUtilsTest {

	@Test
	public void testIsEmpty() {
		boolean result = StringUtils.isEmpty("");
		Assert.assertTrue("Provided string should be empty and  result should be true", result);
	}
	
	@Test
	public void testIsEmptyWithNonEmptyString() {
		boolean result = StringUtils.isEmpty("This is a string");
		Assert.assertFalse("Provided non empty string and  result should be false", result);
	}


	@Test
	public void testIsNotEmpty() {
		boolean result = StringUtils.isNotEmpty("Non Empty String");
		Assert.assertTrue("Provided non empty string and  result should be true", result);
	}

	@Test
	public void testIsBlank() {
		boolean result = StringUtils.isBlank("     ");
		Assert.assertTrue("Provided a blank string and result should be true", result);		
	}

	@Test
	public void testIsNotBlank() {
		boolean result = StringUtils.isNotBlank("This is a string");
		Assert.assertTrue("Provided a not blank string and result should be true", result);		
	}

}
