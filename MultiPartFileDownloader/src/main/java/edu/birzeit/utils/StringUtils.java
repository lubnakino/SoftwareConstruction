package edu.birzeit.utils;

/**
 * This class is a place holder for a set of Utils common methods that is used
 * against String Object
 * 
 * @author Hadi-Awad
 *
 */
public class StringUtils {

    /**
     * Check if the String is empty
     * 
     * @param string
     *            input string
     * @return true if string is empty
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
    }

    /**
     * Check if the String is not empty
     * 
     * @param string
     *            input string
     * @return true if string is not empty
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * Check if the String is blank, that is it does contain a whitespace
     * 
     * @param string
     *            input string
     * @return true if string is blank
     */
    public static boolean isBlank(String string) {
        int strLen;
        if (string != null && (strLen = string.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(string.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    /**
     * Check if the String is not blank, that is it does not contain a white
     * space
     * 
     * @param string
     *            input string
     * @return true if string is not blank
     */
    public static boolean isNotBlank(String string) {
        return !isBlank(string);
    }
}
