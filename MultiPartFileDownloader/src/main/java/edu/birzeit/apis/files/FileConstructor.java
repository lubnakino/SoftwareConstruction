package edu.birzeit.apis.files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.birzeit.exceptions.InvalidInputException;
import edu.birzeit.exceptions.UnreachableURLException;
import edu.birzeit.structures.Segment;
import edu.birzeit.utils.InputOutputUtils;
import edu.birzeit.utils.URLUtils;

/**
 * This class will be reading the url content and then construct the file
 * 
 * @author haawad
 *
 */
public class FileConstructor {

    private static final Logger LOG = LoggerFactory.getLogger(FileConstructor.class);

    private static final String FILE_NAME_PREFIX = "SCP-";

    public InputStream fetchURLsAndGatherStream(Map<String, Segment> segmentsMap)
            throws UnreachableURLException, IOException, InvalidInputException {
        String fileName = FILE_NAME_PREFIX + Math.random();
        String fileType = getFileType(segmentsMap.values().iterator().next());
        fileName += fileType;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
        for (Segment segments : segmentsMap.values()) {
            BufferedReader bufferReader = URLUtils.getUrlContentAsBufferReader(segments.getMainUrl());
            if (bufferReader != null) {
                LOG.info("Main URL {} is  reachable and content was fetched", segments.getMainUrl());
            } else if (!segments.getUrlMirrors().isEmpty()) {
                List<String> urlMirrors = segments.getUrlMirrors();
                for (String urlMirror : urlMirrors) {
                    bufferReader = URLUtils.getUrlContentAsBufferReader(urlMirror);
                    if (bufferReader != null) {
                        LOG.info("Mirror URL {} is  reachable and content was fetched", urlMirror);
                        break;
                    }
                }
                if (bufferReader == null) {
                    throw new UnreachableURLException("Segments URL and all of its mirrors are not reachable!");
                }

            } else {
                throw new UnreachableURLException("Segments URL is not reachable and has no mirrors!");
            }
            ArrayList<Integer> partialContent = InputOutputUtils.writeBufferedReaderToBytes(bufferReader);
            LOG.info("URL Content is {} ", partialContent);
            for (Integer integer : partialContent) {
                writer.append((char) integer.intValue());
            }
        }

        writer.close();
        return new FileInputStream(fileName);
    }

    private String getFileType(Segment segment) {
        String urlParts[] = segment.getMainUrl().split("-segment.*");
        LOG.debug("Count of tokens {} ", urlParts.length);
        String url = urlParts[0];
        LOG.debug("url is {} ", url);
        String fileType = url.substring(url.lastIndexOf('.'));
        LOG.debug("fileType is {} ", fileType);
        return fileType;

    }
}
