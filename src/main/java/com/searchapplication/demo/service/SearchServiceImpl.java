package com.searchapplication.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Impl service for search application
 *
 * @author sagarika.padhy478@gmail.com
 */

@Service
public class SearchServiceImpl implements SearchService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchServiceImpl.class);


    /**
     * get input from UI
     *
     * @param keyword , searchurl
     * @return string searchResult. e.g "1,20" or "20"
     */
    @Override
    public String getSearchResult(String keyword, String searchurl) throws Exception {

        String searchResult = "";
        StringBuilder builder = new StringBuilder();
        String delim = "";

        LOGGER.info("keyword  " + keyword + "search url " + searchurl);

        if (keyword.contains(",")) {
            String[] keywords = keyword.split(",");
            for (String singleKeyword : keywords) {

                builder.append(delim);
                delim = ",";
                builder.append(doSearchInGoogle(singleKeyword, searchurl));
            }
            searchResult = builder.toString();

        } else {
            searchResult = doSearchInGoogle(keyword, searchurl);


        }
        return searchResult;

    }


    private String doSearchInGoogle(String keyword, String searchurl) throws Exception {

        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        List<String> result;
        String searchTerm = keyword.trim().replaceAll(" ", "+");
        String path = "https://www.google.com/search?q=" + searchTerm + "&num=100";

        LOGGER.info("Path " + searchurl);

        URL url;
        /*
          perform search in google
          returns result in html stream.
         */
        url = new URL(path);
        final URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", agent);
        final InputStream stream = connection.getInputStream();

            /*
              call getString() method
              to convert into a string
             */
        String page = getString(stream);

            /*
              call parseSearchResult() method
              parsing the html string using java regex
             */
        result = parseSearchResult(page, searchurl);

        return String.valueOf(result.size());

    }


    /**
     * getString() method
     */
    private static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /* finally block to close the {@link BufferedReader} */
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    /**
     * parseSearchResult() method do to regex
     */

    private static List<String> parseSearchResult(final String html, String searchrl) throws Exception {
        List<String> result = new ArrayList<>();
        String pattern1 = "<a href=\"/url?q=";
        String pattern2 = "\">";
        Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
        Matcher m = p.matcher(html);

        while (m.find()) {
            String domainName = m.group(0);
            LOGGER.info("after 1st regex " + domainName);

            Pattern urlPattern = Pattern.compile(Pattern.quote(searchrl));
            Matcher urlMatcher = urlPattern.matcher(domainName);
            while (urlMatcher.find()) {
                String domain = urlMatcher.group(0);
                LOGGER.info("after final regex " + domainName);

                result.add(domain);
            }

        }
        return result;
    }

}
