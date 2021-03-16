package jp.gmo.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The Class UrlApiProperties.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "gmo.url")
@Data
public class UrlApiProperties {

	private String getAccountInfo;
}
