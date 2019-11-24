/**
 * 
 */
package com.mylearn.core;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import javax.management.openmbean.OpenDataException;

/**
 * @author Chitra
 *
 */
public class FileReader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
}
