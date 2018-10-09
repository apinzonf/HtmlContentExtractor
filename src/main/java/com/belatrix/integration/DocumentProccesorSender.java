package com.belatrix.integration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocumentProccesorSender {
	final static Logger LOGGER = LoggerFactory.getLogger(DocumentProccesorSender.class);
	
	@Autowired
	List<DocumentProcessor> processors;
	
	
	public Map<String, List<String>> sendDocumentToProcess(Document doc) {
		Map<String, List<String>> matchesByProcessor = new HashMap<>();
		processors.forEach(processor -> {
			matchesByProcessor.put(processor.getName(), processor.processDocument(doc));
		});
		return matchesByProcessor;		
	}

}
