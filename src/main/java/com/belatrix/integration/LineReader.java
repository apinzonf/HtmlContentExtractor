package com.belatrix.integration;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LineReader {
	final static Logger LOGGER = LoggerFactory.getLogger(LineReader.class);
	
	LineIterator lineIterator;
	Boolean finishJob;
	
	@Value("${file.name}")
	String filename;
	
	@PostConstruct
	public void config() {
		LOGGER.info("Filename: " + filename);
		finishJob = false;
		File file = new File(filename);
		try {
			lineIterator = FileUtils.lineIterator(file, "UTF-8");
		} catch (IOException e) {
			LOGGER.error("ERROR reading file: " + filename);
		}
		
	}
	
	public String nextLine() {
		if (lineIterator.hasNext()) {
			return lineIterator.next().trim();
		} else if(!finishJob){
			finishJob = true;
			LOGGER.info("Finished, no more URLs in the file.");
		}
		return null;
	}

}
