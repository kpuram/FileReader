/**
 * 
 */
package main.java.com.mylearn.model;

/**
 * @author Chitra
 *
 */
public class DataFeedRow {

	private String serialNum;
	private String urlStr;
	private String noOfHits;
	
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getNoOfHits() {
		return noOfHits;
	}
	public void setNoOfHits(String noOfHits) {
		this.noOfHits = noOfHits;
	}
	
	@Override
	public String toString() {
		return "DataFeedRow [serialNum=" + serialNum + ", urlStr=" + urlStr + ", noOfHits=" + noOfHits + "]";
	}
	
	
	
}
