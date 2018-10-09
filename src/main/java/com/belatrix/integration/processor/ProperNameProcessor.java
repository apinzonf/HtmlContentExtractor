package com.belatrix.integration.processor;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class ProperNameProcessor extends RegexMatchProcessor {
	static final Pattern patternProperName = Pattern.compile("([A-Z][a-z]{1,2}\\.\\s+(?:[A-Z][a-z]+\\s*)*|(?<!\\. )(?<!;)(?:[A-Z][a-z]+\\s*)+)");

	@Override
	public String getName() {
		return "Proper names";
	}

	@Override
	protected Pattern getPattern() {
		return patternProperName;
	}

}
