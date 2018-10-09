package com.belatrix.integration.processor;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class TwitterAccountProcessor extends RegexMatchProcessor {
	static final Pattern patternHashtag = Pattern.compile("(?<=^|(?<=[^a-zA-Z0-9-_\\.]))@([A-Za-z]+[A-Za-z0-9-_]+)");

	@Override
	public String getName() {
		return "Twitter accounts";
	}

	@Override
	protected Pattern getPattern() {
		return patternHashtag;
	}

}
