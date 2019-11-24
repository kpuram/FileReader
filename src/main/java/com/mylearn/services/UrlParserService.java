/**
 * 
 */
package main.java.com.mylearn.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import main.java.com.mylearn.core.ValidExtensions;
import main.java.com.mylearn.model.DataFeedRow;



/**
 * @author Chitra
 *
 */
public class UrlParserService {


	
	public UrlParserService() {
	}
	
	public List<String> processDataFeedRows(List<DataFeedRow> dataFeeds){
		List<String> parsedDataFeeds=  new ArrayList<>();
		WriteCsvService csvService = new WriteCsvService();
		if(dataFeeds != null && dataFeeds.size()>0) {
			for(DataFeedRow feedRow : dataFeeds) {
				String[] parsedDataRow = new String [3];
				parsedDataRow[0]=feedRow.getSerialNum();
				parsedDataRow[1]=processExtUrl(feedRow.getUrlStr());
				parsedDataRow[2]=feedRow.getNoOfHits();
				parsedDataFeeds.add(csvService.convertToCSV(parsedDataRow));
			}
		}
		
		return parsedDataFeeds;
	}
	
	public String processExtUrl(String url) {
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
			 int lastOccuranceOfSlash = url.lastIndexOf("/");
			 finalString=url.substring(0,lastOccuranceOfSlash)+"/index.html";
/*			 if(url.endsWith("/")) {
				 finalString= url+"index.html";
			 }else {
				 finalString= url+"/index.html";
			 }*/
			 
		 }

		return finalString;
	}
}
