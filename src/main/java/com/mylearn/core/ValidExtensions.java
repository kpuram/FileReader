/*
 * 
 */
package com.mylearn.core;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chitra
 *
 */
public enum ValidExtensions {
	SHTML(".shtml"),
	HTML(".html"),
	HTM(".htm");
	
	private String extension;
	
	private ValidExtensions(String ext) {
		this.extension=ext;
	}
	
	public String getExtension() {
		return extension;
	}



	public static List<String> getvalidExt(){
		List<String> validExtns= new ArrayList<>();
		 for(ValidExtensions ext : ValidExtensions.values()) {
			 validExtns.add(ext.getExtension());
		 }	
		return validExtns;
	}

}
