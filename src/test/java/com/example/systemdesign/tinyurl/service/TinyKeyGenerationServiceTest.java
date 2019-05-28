/**
 * 
 */
package com.example.systemdesign.tinyurl.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.example.systemdesign.tinyurl.service.TinyKeyGenerationServiceImpl;

/**
 * @author govindaraju.v
 *
 */
@RunWith(MockitoJUnitRunner.class)
//@RunWith(PowerMockRunner.class)
public class TinyKeyGenerationServiceTest {

	@Mock
	HashMap<String, String> urlStrore;

	@InjectMocks
	TinyKeyGenerationServiceImpl tinyUrlGenerator;

	@Test
	public void generateTinyUrl_test() {
		String tinyUrl = tinyUrlGenerator.generateUrl("tset");
		// assertNotNull(tinyUrl);
		assertTrue(tinyUrl.length() == 7);
	}

	@Test(expected=NullPointerException.class)
	public void generateTinyUrlNull_test() {
		tinyUrlGenerator.generateUrl(null);
	}

	@Test
	public void getFullUrl_Test() {
		//sample mock result
		HashMap<String, String> urlStrore = new HashMap<>();
		urlStrore.put("s264kAB", "https://www.educative.io/collection/page/5668639101419520/5649050225344512/5668600916475904/");
		
		System.out.println("------------");
		
		//HashMap<String, String> map = PowerMockito.mock(HashMap.class);
		//PowerMockito.whenNew(HashMap.class).withAnyArguments().thenReturn(map,urlStroreSample1);
		
		//set private variable  using powerMock
		//PowerMockito.when(urlStrore.get("s264kAB")).thenReturn("test");
		
		//set private variable value using reflection
		ReflectionTestUtils.setField(tinyUrlGenerator, "urlStrore", urlStrore);
		
		
		// Mockito.when(tinyUrlGenerator.urlStrore.get("s264kAB")).thenReturn("test");
		String tinyUrl = tinyUrlGenerator.reverseURL("s264kAB");
		assertNotNull(tinyUrl);
	}
}
