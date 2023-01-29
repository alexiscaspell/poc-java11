package io.blacktoast.utils.redis.entity;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.blacktoast.utils.bean.IntrospectionUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class RedisKeyValue<T> {

	private String key;
	private String value;
	private Boolean setOnlyIfNotExists;
	private Long ttlSeconds;

	public RedisKeyValue() {
		super();
		this.setOnlyIfNotExists = false;
		this.ttlSeconds = 60l;
	}
	
	public void setObjectValue(Object value) throws JsonProcessingException {
		this.setValue(IntrospectionUtil.objectToJsonWithJackson(value));
	} 

}
