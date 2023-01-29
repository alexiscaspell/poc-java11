package io.blacktoast.utils.redis.configuration;

import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import ar.org.blacktoast.poc.java11.configuration.ConfigurationVars;
import ar.org.blacktoast.poc.java11.configuration.Vars;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class RedisConfig {

	public static final String URL = ConfigurationVars.get(Vars.REDIS_URL);
	public static final Integer PORT = ConfigurationVars.get(Vars.REDIS_PORT, Integer.class);
	public static final Integer MAX_POOL = ConfigurationVars.get(Vars.REDIS_MAX_POOL, Integer.class);
	public static final Integer POP_TIME_OUT = ConfigurationVars.get(Vars.REDIS_POP_TIME_OUT, Integer.class);

	private StringRedisTemplate redisTemplate;

	public RedisConfig() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(MAX_POOL);
		poolConfig.setMaxIdle(MAX_POOL);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);

		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(URL, PORT);

		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration);

		StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(connectionFactory);
		stringRedisTemplate.setEnableTransactionSupport(true);

		this.redisTemplate = stringRedisTemplate;
	}

	public StringRedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

}
