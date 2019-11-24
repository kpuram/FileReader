/**
 * 
 */
package main.java.com.mylearn.services;

import java.util.List;

import main.java.com.mylearn.core.ValidExtensions;



/**
 * @author Chitra
 *
 */
public class UrlParserService {

	private String filePath;
	
	public UrlParserService(String filePath) {
		this.filePath=filePath;
	}
	
	public List<String> processDataFile(){
		
		
		return null;
	}
	
	public String processExtUrl(String url,String[] validExts) {
		String finalString =null;
		boolean isFound= false;
		 for (String ext : ValidExtensions.getvalidExt()) {
			 if (url != null && !isFound && url.contains(ext)) {
				 isFound= true;
				int length = url.length();
				int startIndex = url.indexOf(ext);
				int end = startIndex + ext.length();
				if (length > end) {
					//System.out.printf("valid case : %s is the not last in the URL, Need to trim URL ", ext);
					finalString=url.substring(0,end);
				} else if (length == end) {
					//System.out.printf("valid case : %s is the last in the URL ", ext);
					finalString=url;
				} else {
					System.out.printf("Not a valid case: %s should be not be less than URL length", ext);
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
