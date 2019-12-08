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

import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

	@Override
	public String getSearchResult(String keyword, String searchrl) {
		
		String searchResult = "";
		String res = "";
		if(keyword.contains(",")) {
			String[] keywords = keyword.split(",");			
			for(int i=0; i<keywords.length; i++) {
				
				res = search(keywords[i],searchrl);
				searchResult += ","+res;								 
			}
			System.out.println("loop 1:"+searchResult);
			
		}else {
			searchResult = search(keyword,searchrl);
			System.out.println("loop 1 1:"+searchResult);
		}
		return searchResult;

	}
	
	public String search(String keyword, String searchrl) {
		
		final String agent = "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)";
		List<String> result = new ArrayList<String>();
		String searchTerm = keyword.trim().replaceAll(" ", "+");
		System.out.println("searchTerm 2"+searchTerm);
		String path = "https://www.google.com/search?q=" + searchTerm + "&num=100";
		URL url;
		try {
			url = new URL(path);
			final URLConnection connection = url.openConnection();			                
			connection.setRequestProperty("User-Agent", agent);
			final InputStream stream = connection.getInputStream();
			String page = getString(stream);
			
	//		System.out.println("google result "+ page);
			result = parseLinks(page,searchrl);
			
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
		System.out.println("result 2:"+result.size());
		return String.valueOf(result.size());
		
	}

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

	public static List<String> parseLinks(final String html,String searchrl) throws Exception {
		List<String> result = new ArrayList<String>();
		String pattern1 = searchrl;  //"<h3 class=\"r\"><a href=\"/url?q="
		//String pattern2 = "\">";
		Pattern p = Pattern.compile(Pattern.quote(pattern1) );
		Matcher m = p.matcher(html);

		while (m.find()) {
			String domainName = m.group(0);
			result.add(domainName);

		}
		return result;
	}

}
