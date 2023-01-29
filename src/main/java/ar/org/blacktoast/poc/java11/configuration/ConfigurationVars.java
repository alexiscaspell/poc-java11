package ar.org.blacktoast.poc.java11.configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationVars {

	private static final Logger LOG = LoggerFactory.getLogger(ConfigurationVars.class);

	private static ConfigurationVars INSTANCE = new ConfigurationVars();

	private Map<String, String> propertiesMap;

	private ConfigurationVars() {
		propertiesMap = new HashMap<String, String>();
	}

	private static ConfigurationVars getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfigurationVars();
		}
		return INSTANCE;
	}

	public static Map<String, String> getPropertiesMap() {
		return getInstance().propertiesMap;
	}

	public static void addProperty(String key, String value) {
		getPropertiesMap().put(key, value);
	}

	public static <T> T get(String key, Class<T> typeProperty) {

		String value = getPropertiesMap().get(key);

		try {
			return typeProperty.getConstructor(String.class).newInstance(value);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			LOG.error(e.getLocalizedMessage(), e);
			return null;
		}
	}

	public static String get(String key) {
		return getPropertiesMap().get(key);
	}
}
