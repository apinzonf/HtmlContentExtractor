package com.belatrix.integration;

import java.util.List;

import org.jsoup.nodes.Document;

public interface DocumentProcessor {
	String getName();
	List<String> processDocument(Document doc); 
}
