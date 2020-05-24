package org.example.bankApp.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author rohitsharma
 */

@Component
public class Utils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static final Logger log = LogManager.getLogger(Utils.class);

	public static String toJson(Object object) throws JsonProcessingException {

		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception ex) {
			log.error("Error occurred while serializing object: " + object, ex);
			throw ex;
		}
	}

	public static Object toObject(String json, Class clazz) throws IOException {

		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception ex) {
			log.error("Error occurred while de-serializing object: " + json + " to object " + clazz, ex);
			throw ex;
		}
	}

	public static HttpHeaders getResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return responseHeaders;
	}
}
