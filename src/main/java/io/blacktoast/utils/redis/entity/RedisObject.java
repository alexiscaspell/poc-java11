package io.blacktoast.utils.redis.entity;

import ar.org.blacktoast.poc.java11.entity.Persona;

public interface RedisObject<T> {
	
	default String getKey() {
		return null;
		
	}

}
