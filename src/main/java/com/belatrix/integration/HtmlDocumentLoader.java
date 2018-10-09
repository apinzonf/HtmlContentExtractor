package com.belatrix.integration;

import java.io.IOException;
import java.net.ConnectException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Retryable(value= {ConnectException.class}, maxAttempts = 5, backoff = @Backoff(delay = 1000, maxDelay = 60000, multiplier = 100))
@Component
public class HtmlDocumentLoader {
	final static Logger LOGGER = LoggerFactory.getLogger(HtmlDocumentLoader.class);
	
	public Document loadHTMLDocumentFromURL(String url) throws IOException {
		LOGGER.info("loading " + url + "...");
		Document doc = null;
		doc = Jsoup.connect(url).get();
		return doc;
	}
}
