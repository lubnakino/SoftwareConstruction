package edu.birzeit.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class will be a place holder for the URL utils and common functions we
 * are going to use inside the project components
 * 
 * @author Hadi-Awad
 *
 */
public class URLUtils {

    private static final Logger LOG = LoggerFactory.getLogger(URLUtils.class);

    /**
     * This function will get the content type of URL
     * 
     * @param urlString
     *            url
     * @return the content type
     */
    public static String openURLConnectionAndGetContentType(String urlString) {
        HttpURLConnection connection = null;
        try {
            URL urlObject = new URL(urlString);
            connection = (HttpURLConnection) urlObject.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            String contentType = connection.getContentType();
            LOG.debug("the content type is {}", contentType);
            return contentType;
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(" IO Exception was thrown while trying to get the url content type", e);
        } finally {
            LOG.debug("trying to close the connection if it was opened!");
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    /**
     * This function will get the URL content as a BufferedReader
     * 
     * @param urlString
     *            file URL as String
     * @return BufferedReader that waps the url connection
     */
    public static BufferedReader getUrlContentAsBufferReader(String urlString) {
        HttpURLConnection connection = null;
        URL urlObject;
        BufferedReader bufferedReader = null;
        try {
            urlObject = new URL(urlString);
            connection = (HttpURLConnection) urlObject.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            LOG.error(" Malformed URL  Exception was thrown while trying to get the url content as buffer reader", e);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error(" IO Exception was thrown while trying to get the url content as buffer reader", e);
        }

        return bufferedReader;
    }

    /**
     * This function will get the file type out of the URL segment
     * 
     * @param urlFromSegments
     *            http://machine1.birzeit.edu/picture.png-segment1
     * @return file-type .png for example
     */
    public static String getFileTypeFromURL(String urlFromSegments) {
        String urlParts[] = urlFromSegments.split("-segment.*");
        LOG.debug("Count of tokens {} ", urlParts.length);
        String url = urlParts[0];
        LOG.debug("url is {} ", url);
        String fileType = url.substring(url.lastIndexOf('.'));
        LOG.debug("fileType is {} ", fileType);
        return fileType;

    }
}
