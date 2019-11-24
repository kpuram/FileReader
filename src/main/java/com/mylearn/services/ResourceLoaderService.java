/**
 * 
 */
package main.java.com.mylearn.services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import main.java.com.mylearn.model.DataFeedRow;

/**
 * @author Chitra
 *
 */
public class ResourceLoaderService {
	
	private static final String COMMA_DELIMITTER=",";
	
	private String filePath = "src/main/resources/";
	private String fileName;
	
	public ResourceLoaderService(String filePath,String fileName) {
		if(filePath != null) {
			this.filePath=filePath;
		}
		this.fileName=fileName;
	}
	
	public ResourceLoaderService(String fileName) {
			this.fileName = fileName;
	}
	
    public List<DataFeedRow> loadFileWithReader() throws IOException {
    	List<DataFeedRow> dataFeedRows= null;
    	String finalFilePath=this.filePath+this.fileName;
    	if(!this.filePath.endsWith("/")) {
    		finalFilePath=this.filePath+"/"+this.fileName;
    	}
    		
        try (FileReader fileReader = new FileReader(finalFilePath);
             BufferedReader reader = new BufferedReader(fileReader)) {
           /* String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(contents);*/
            
            List<String> rawRowData = reader.lines().collect(Collectors.toList());
            dataFeedRows=  rawRowData.stream().map(row -> parseLinetoDataFeedRow(row)).collect(Collectors.toList());
           
        }
        
        return dataFeedRows;
    }

    public void loadFileAsResource(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/"+fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String contents = reader.lines()
                .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(contents);
        }
    }
    
    public DataFeedRow parseLinetoDataFeedRow(String rowData) {
    	DataFeedRow dataFeedRow = null;
    	if(Objects.nonNull(rowData)) {
    		String [] columnsData=rowData.split(COMMA_DELIMITTER);
    		if (columnsData != null && columnsData.length>=3) {
    			dataFeedRow = new DataFeedRow();
    			dataFeedRow.setSerialNum(columnsData[0]);
    			dataFeedRow.setUrlStr(columnsData[1]);
    			dataFeedRow.setNoOfHits(columnsData[2]);
    		}
    	}
    	return dataFeedRow;
    }

}
