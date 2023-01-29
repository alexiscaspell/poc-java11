package ar.org.blacktoast.poc.java11.service.impl;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.repository.PersonaRepository;
import ar.org.blacktoast.poc.java11.service.PersonaService;
import io.blacktoast.utils.redis.service.RedisService;

@Service
public class PersonaServiceSQLImpl implements PersonaService {

	private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceSQLImpl.class);
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Optional<Persona> getById(Integer id) throws AppException, JsonParseException, JsonMappingException, IOException {
		return personaRepository.findById(id);
	}

	@Override
	public void save(Persona persona) throws AppException, JsonProcessingException {
		personaRepository.save(persona);
		
	}

	@Override
	public Stream<Persona> getAll(Persona persona) throws AppException {
		return StreamSupport.stream(personaRepository.findAll().spliterator(), false);
	}

	public void deleteAll() {
		personaRepository.deleteAll();
		
	}


}
