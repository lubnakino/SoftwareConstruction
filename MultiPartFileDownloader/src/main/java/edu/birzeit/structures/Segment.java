package edu.birzeit.structures;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    private String mainUrl;

    private List<String> urlMirrors;

    public Segment() {
        urlMirrors = new ArrayList<String>();
    }

    public String getMainUrl() {
        return mainUrl;
    }

    public void setMainUrl(String mainUrl) {
        this.mainUrl = mainUrl;
    }

    public List<String> getUrlMirrors() {
        return urlMirrors;
    }

    public void setUrlMirrors(List<String> urlMirrors) {
        this.urlMirrors = urlMirrors;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append("Mai-URL: ");
        strBuilder.append(mainUrl);
        strBuilder.append(", URL-Mirors: ");
        strBuilder.append(urlMirrors);
        return strBuilder.toString();
    }

}
