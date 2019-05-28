package com.example.systemdesign.tinyurl.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

@Service
public class TinyKeyGenerationServiceImpl implements TinyKeyGenerationService {

	public TinyKeyGenerationServiceImpl() {
		System.out.println("--------TinyKeyGenerationServiceImpl--------");
	}

	// base62
	private static final String CHAR_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private final int base = CHAR_STR.length();
	private Map<String, String> urlStrore = new HashMap<>();
	AtomicInteger counter = new AtomicInteger(10);

	public String generateUrl(String url) {
		if (url == null) {
			throw new NullPointerException();
		}
		final long nextNumber = getNextNumber();
		String tinyUrl = convertAndGetBase62Code(nextNumber);
		urlStrore.put(tinyUrl, url);
		return tinyUrl;
	}

	private String convertAndGetBase62Code(long number) {
		StringBuilder sb = new StringBuilder("");
		while (number > 0) {
			int reminder = (int) (number % base);
			sb.append(CHAR_STR.charAt(reminder));
			number = number / base;
		}
		return sb.toString();
	}

	private long getNextNumber() {
		int counterValue = counter.incrementAndGet();
		return Long.valueOf(counterValue + System.currentTimeMillis());
	}

	public String reverseURL(String encodedTinyURL) {
		// converttoBase10... long tinyURLId =
		return urlStrore.get(encodedTinyURL);
	}
}
