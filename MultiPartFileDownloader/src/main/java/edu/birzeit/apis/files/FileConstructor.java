package edu.birzeit.apis.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.structures.Segment;
import edu.birzeit.utils.URLUtils;

/**
 * This class will be reading the url content and then construct the file
 * 
 * @author haawad
 *
 */
public class FileConstructor {

    private static final Logger LOG = LoggerFactory.getLogger(FileConstructor.class);

    public BufferedReader fetchURLsAndGatherStream(Segment segment)
            throws UnreachableURLException, IOException, InvalidInputException {

        BufferedReader bufferReader = URLUtils.getUrlContentAsBufferReader(segment.getMainUrl());
        if (bufferReader != null) {
            LOG.info("Main URL {} is  reachable and content was fetched", segment.getMainUrl());
            return bufferReader;
        } else if (!segment.getUrlMirrors().isEmpty()) {
            List<String> urlMirrors = segment.getUrlMirrors();
            for (String urlMirror : urlMirrors) {
                bufferReader = URLUtils.getUrlContentAsBufferReader(urlMirror);
                if (bufferReader != null) {
                    LOG.info("Mirror URL {} is  reachable and content was fetched", urlMirror);
                    return bufferReader;
                }
            }
            throw new UnreachableURLException("Segments URL and all of its mirrors are not reachable!");
        } else {
            throw new UnreachableURLException("Segments URL is not reachable and has no mirrors!");
        }
    }
}
