package com.belatrix.integration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Component
public class JsonFileWritter {
	final static Logger LOGGER = LoggerFactory.getLogger(JsonFileWritter.class);
	ObjectMapper mapper;
	
	@Value("${output.folder.name:./output}")
	String outputFolderName;
	
	@PostConstruct
	public void setup() {
		mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
		File dir = new File(outputFolderName);
		dir.mkdirs();
	}

	public void writeFile(@Header("inputUrl") String inputUrl, @Payload Map<String, List<String>> matchesByProcessorJsonObj) {
		File outputFile = new File(outputFolderName + "/" + convertUrlToFileName(inputUrl));
		try {			
			BufferedWriter outputBuffer = new  BufferedWriter(new FileWriter(outputFile));
			String jsonString = mapper.writeValueAsString(matchesByProcessorJsonObj);
			outputBuffer.write(jsonString);
			outputBuffer.flush();
			outputBuffer.close();
			LOGGER.info("New file " + outputFile);
		} catch (IOException e) {
			LOGGER.warn("Failed to write file " + outputFile, e);
		}
	}
	
	static String convertUrlToFileName(String url) {
		String filename = url.replaceAll("[^A-Za-z0-9]+", "_");
		return filename;
	}
}
