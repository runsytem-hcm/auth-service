package jp.gmo.auth.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {

    public static <T> T convertJsonStringToObject(String jsonData, Class<T> typeClass) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonData, typeClass);
    }
    
    public static String convertObjectToString(Object obj) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
    
	public static Long getDateTimeCurrent() {
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
		return zdt.toInstant().toEpochMilli();
	}
	
	public static String getMessage(String messageCode) {

		try {
			InputStream utf8in = ResponseUtils.class.getClassLoader().getResourceAsStream("i18n/messages.properties");
			Reader reader = new InputStreamReader(utf8in, StandardCharsets.UTF_8);
			Properties props = new Properties();
			props.load(reader);

			return props.getProperty(messageCode);
		} catch (Exception e) {
			return "";
		}
	}
}
