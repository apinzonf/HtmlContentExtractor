package com.belatrix.integration.processor;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class HashTagProcessor extends RegexMatchProcessor {
	static final Pattern patternHashtag = Pattern.compile("#(\\d*[A-Za-z_]+\\d*)");

	@Override
	public String getName() {
		return "Hashtags";
	}

	@Override
	protected Pattern getPattern() {
		return patternHashtag;
	}

}
