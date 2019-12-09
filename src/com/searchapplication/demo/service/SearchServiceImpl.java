package com.searchapplication.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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
    public String getSearchResult(String keyword, String searchurl) {

        String searchResult = "";
        String res = "";

        LOGGER.info("keyword  " + keyword + "search url " + searchurl);

        if (keyword.contains(",")) {
            String[] keywords = keyword.split(",");
            for (int i = 0; i < keywords.length; i++) {

                res = doSearchInGoogle(keywords[i], searchurl);
                searchResult += "," + res;
            }


        } else {
            searchResult = doSearchInGoogle(keyword, searchurl);

        }
        return searchResult;

    }


    public String doSearchInGoogle(String keyword, String searchurl) {

        final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
        List<String> result = new ArrayList<String>();
        String searchTerm = keyword.trim().replaceAll(" ", "+");
        String path = "https://www.google.com/search?q=" + searchTerm + "&num=100";

        LOGGER.info("Path " + searchurl);

        URL url;
        try {
            /**
             * perform search in google
             * returns result in html stream.
             */
            url = new URL(path);
            final URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", agent);
            final InputStream stream = connection.getInputStream();

            /**
             * call getString() method
             * to convert into a string
             */
            String page = getString(stream);

            /**
             * call parseSearchResult() method
             * parsing the html string using java regex
             */
            result = parseSearchResult(page, searchurl);

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return String.valueOf(result.size());

    }


    /**
     * getString() method
     */
    public static String getString(InputStream is) {
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
            /** finally block to close the {@link BufferedReader} */
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


    /**
     * parseSearchResult() method do to regex
     */

    public static List<String> parseSearchResult(final String html, String searchrl) throws Exception {
        List<String> result = new ArrayList<String>();
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
