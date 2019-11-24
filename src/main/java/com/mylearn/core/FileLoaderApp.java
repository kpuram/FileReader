/**
 * 
 */
package main.java.com.mylearn.core;

import java.io.IOException;
import java.util.List;

import main.java.com.mylearn.model.DataFeedRow;
import main.java.com.mylearn.services.ResourceLoaderService;
import main.java.com.mylearn.services.UrlParserService;
import main.java.com.mylearn.services.WriteCsvService;

/**
 * @author Chitra
 *
 */
public class FileLoaderApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ResourceLoaderService resourceService = new ResourceLoaderService("New_Data_Feed.csv");
		UrlParserService parserService = new UrlParserService();
		WriteCsvService writeCsvService = new WriteCsvService();
		try {
			List<DataFeedRow> dataFeedRows= resourceService.loadFileWithReader();
			if(dataFeedRows != null  && dataFeedRows.size()>0) {
				DataFeedRow headerRow = dataFeedRows.get(0);
				dataFeedRows.remove(0);
				//System.out.println(dataFeedRows);
				List<String> parsedDataRows= parserService.processDataFeedRows(dataFeedRows);
				System.out.println(parsedDataRows);
				//C://Users//Chitra//Desktop//URL_Output
				writeCsvService.writetoCsvFile("src/main/resources/URL_Output", parsedDataRows);
				System.out.println("############### DATA LOADED END ############");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
