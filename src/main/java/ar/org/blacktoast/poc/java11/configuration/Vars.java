package ar.org.blacktoast.poc.java11.configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Vars {

	public static final String FS_ROUTE = "fs.route";

	public static final String REDIS_URL = "redis.url";
	public static final String REDIS_PORT = "redis.port";
	public static final String REDIS_MAX_POOL = "redis.maxpool";
	public static final String REDIS_POP_TIME_OUT = "redis.pop.timeout";

	public static List<String> getConfigurationKeys() {

		List<String> keys = new ArrayList<String>();

		Field[] fields = Vars.class.getFields();
		for (Field field : fields) {

			try {
				keys.add(field.get(String.class).toString());

			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return keys;
	}
}
