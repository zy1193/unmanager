package com.mix.unmanage.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class GlobalConfig {
	private Map<String, String> configMap;

	@Resource
	private Properties configProperties;

	@SuppressWarnings("unused")
	@PostConstruct
	private void init() {
		configMap = new HashMap<String, String>();
		Set<Object> keys = configProperties.keySet();
		for (Object key : keys) {
			configMap.put(key.toString(),
					configProperties.getProperty(key.toString()));
		}
	}

	public String get(String key) {
		return configMap.get(key);
	}
}
