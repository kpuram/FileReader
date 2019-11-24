/**
 * 
 */
package main.java.com.mylearn.core;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chitra
 *
 */
public class MyResourceLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyResourceLoader resourceLoader= new MyResourceLoader();
		try {
			resourceLoader.loadFileWithReader();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("File check");
		String [] supportedUrlExts= new String[3];
		supportedUrlExts[0]= ".shtml";
		supportedUrlExts[1]= ".html";
		supportedUrlExts[2]= ".htm";
		
		
		String [] urls= new String [7];
		urls[0]= "http://www.xyz.com/v.html";
		urls[1]= "http://www.xyz.com/v.shtml";
		urls[2]= "http://www.xyz.com/v.htm";
		urls[3]= "http://www.xyz.com/index.html";
		urls[4]= "http://www.xyz.com/abc/123/index.html?23445";
		urls[5]= "http://www.xyz.com/abc/123/";
		urls[6]= "http://www.xyz.com/abc/123/";
		
		List<String> urlList = Arrays.asList(urls);
		
		
		for(String url : urlList) {
			String finalUrl= processExtUrl(url, supportedUrlExts);
			System.out.println("Original Url :::::: "+url+" vs Parsed Url :::::: "+finalUrl);
		}
		
		

		
	}
		
	public static String processExtUrl(String url,String[] validExts) {
		String finalString =null;
		boolean isFound= false;
		 for (int i=0; i< validExts.length ; i++) {
			 if (!isFound && url.contains(validExts[i])) {
				 isFound= true;
				int length = url.length();
				int startIndex = url.indexOf(validExts[i]);
				int end = startIndex + validExts[i].length();
				if (length > end) {
					//System.out.printf("valid case : %s is the not last in the URL, Need to trim URL ", validExts[i]);
					finalString=url.substring(0,end);
				} else if (length == end) {
					//System.out.printf("valid case : %s is the last in the URL ", validExts[i]);
					finalString=url;
				} else {
					System.out.printf("Not a valid case: %s should be not be less than URL length", validExts[i]);
					//System.out.println(url);
				} 
			}
		 }
		 
		 if(!isFound) {
			 if(url.endsWith("/")) {
				 finalString= url+"index.html";
			 }else {
				 finalString= url+"/index.html";
			 }
			 
		 }

		return finalString;
	}
	
    private void loadFileWithReader() throws IOException {

        try (FileReader fileReader = new FileReader("src/main/resources/New_Data_Feed.csv");
             BufferedReader reader = new BufferedReader(fileReader)) {
            String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(contents);
        }

    }

/*    private void loadFileAsResource() throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/New_Data_Feed.csv");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(contents);
        }
    }*/
}
