package ar.org.blacktoast.poc.java11.service.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.service.PersonaService;
import io.blacktoast.utils.redis.service.RedisService;

//@Service
public class PersonaServiceRedisImpl implements PersonaService {

	private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceRedisImpl.class);
	private static final String REDIS_PREFIX = "poc_java11_personas_";
	
	@Autowired
	private RedisService redisService;

	@Override
	public Optional<Persona> getById(Integer id) throws AppException, JsonParseException, JsonMappingException, IOException {
		return Optional.ofNullable(redisService.getObjectByKey(REDIS_PREFIX+id,Persona.class));
	}

	@Override
	public void save(Persona persona) throws AppException, JsonProcessingException {
		redisService.setObjectByKey(REDIS_PREFIX+persona.getId(),persona,Persona.class);
		
	}

	@Override
	public Stream<Persona> getAll(Persona persona) throws AppException {
		return null;
	}


}
