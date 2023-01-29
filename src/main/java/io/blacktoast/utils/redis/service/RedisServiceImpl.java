package io.blacktoast.utils.redis.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.blacktoast.utils.bean.IntrospectionUtil;
import io.blacktoast.utils.redis.configuration.RedisConfig;

@Service
public class RedisServiceImpl implements RedisService {

	@Autowired
	private RedisConfig redisConfig;

	private ValueOperations<String, String> getOpsForValue() {
		return redisConfig.getRedisTemplate().opsForValue();
	}

	private ListOperations<String, String> getOpsForList() {
		return redisConfig.getRedisTemplate().opsForList();
	}

	@Override
	public String getByKey(String key) {
		String value = null;
		try {
			value = getOpsForValue().get(key);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public void incrementByKey(String key, Long increment) {
		try {
			getOpsForValue().increment(key, increment);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, String> getByKeyPattern(String pattern) {

		Map<String, String> keysAndValues = new HashMap<String, String>();
		try {
			Set<String> keys = redisConfig.getRedisTemplate().keys(pattern);

			for (String key : keys) {
				String value = getOpsForValue().get(key);
				keysAndValues.put(key, value);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return keysAndValues;
	}

	@Override
	public List<String> getKeysByPattern(String pattern) {

		List<String> keys = new ArrayList<String>();
		try {
			Set<String> keySet = redisConfig.getRedisTemplate().keys(pattern);

			if (keySet != null) {
				for (String key : keySet) {
					keys.add(key);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return keys;
	}

	@Override
	public void setByKey(String key, String value, Long ttlSeconds) {
		try {
			BoundValueOperations<String, String> boundValueOperations = redisConfig.getRedisTemplate()
					.boundValueOps(key);
			boundValueOperations.set(value);
			
			if (ttlSeconds != null) {
				boundValueOperations.expire(ttlSeconds, TimeUnit.SECONDS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Long getTtl(String key) {

		Long ttl = 0l;
		try {
			ttl = redisConfig.getRedisTemplate().getExpire(key, TimeUnit.SECONDS);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttl;
	}

	@Override
	public void pushToQueue(String queueName, String value) {
		getOpsForList().leftPush(queueName, value);
	}

	@Override
	public String retrieveFromQueue(String queueName) {
		return getOpsForList().rightPop(queueName, RedisConfig.POP_TIME_OUT, TimeUnit.MILLISECONDS);
	}

	@Override
	public void deleteByKey(String key) {
		getOpsForValue().getOperations().delete(key);
	}

	@Override
	public <T> T getObjectByKey(String key, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
		return IntrospectionUtil.jsonToObjectWithJackson(this.getByKey(key), clazz);
	}

	@Override
	public <T> void setObjectByKey(String key, T value, Class<T> clazz) throws JsonProcessingException {
		this.setByKey(key, IntrospectionUtil.objectToJsonWithJackson(value), null);
	}
	@Override
	public void flushAll() {
		this.getKeysByPattern("*").forEach(k->this.deleteByKey(k));
	}

}
