package com.belatrix.integration.processor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.belatrix.integration.DocumentProcessor;

public abstract class RegexMatchProcessor implements DocumentProcessor{
	final static Logger LOGGER = LoggerFactory.getLogger(RegexMatchProcessor.class);
	
	protected abstract Pattern getPattern();
	
	@Override
	public List<String> processDocument(Document doc) {
		Set<String> results = new HashSet<>();
		try {
			String bodyText = doc.select("body").text();
			Matcher matcher = getPattern().matcher(bodyText);
			while(matcher.find()) {
				results.add(matcher.group());
			}
		}catch(Exception ex) {
			LOGGER.warn("Failed to match " + getName(), ex);
		}
		return new ArrayList<>(results);
	}
}
