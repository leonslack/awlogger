package com.awto.logger.commons;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class ConfigProperties {

	private Map<String, String> items;

	public Map<String, String> getItems() {
		;
		return items;
	}

	public void setItems(Map<String, String> items) {
		this.items = items;
	}

}
