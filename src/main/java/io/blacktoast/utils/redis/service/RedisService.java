package io.blacktoast.utils.redis.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * @version 2.0.0
 */
/**
 * @author blacktoast
 *
 */
public interface RedisService {

	/**
	 * @param key
	 * @return
	 */
	String getByKey(String key);

	/**
	 * @param key
	 * @param value
	 * @param setOnlyIfNotExists
	 * @param ttlSeconds
	 */
	void setByKey(String key, String value, Long ttlSeconds);

	/**
	 * @param pattern
	 * @return
	 */
	Map<String, String> getByKeyPattern(String pattern);

	/**
	 * @param pattern
	 * @return
	 */
	List<String> getKeysByPattern(String pattern);

	/**
	 * @param key
	 * @return
	 */
	Long getTtl(String key);

	/**
	 * @param key
	 * @param increment
	 */
	void incrementByKey(String key, Long increment);

	/**
	 * @param quequeName
	 * @param value
	 * @return
	 */
	void pushToQueue(String queueName, String value);

	/**
	 * @param quequeName
	 * @return
	 */
	String retrieveFromQueue(String queueName);

	/**
	 * @param key
	 */
	void deleteByKey(String key);

	<T> T getObjectByKey(String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException;

	<T> void setObjectByKey(String key, T value, Class<T> clazz) throws JsonProcessingException;

	void flushAll();

}
