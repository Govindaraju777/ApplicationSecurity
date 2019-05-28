package com.example.systemdesign.tinyurl.service;

/**
 * @author govindaraju.v
 *
 */
public interface TinyKeyGenerationService {
	public String generateUrl(String url);

	public String reverseURL(String encodedTinyURL);
}
