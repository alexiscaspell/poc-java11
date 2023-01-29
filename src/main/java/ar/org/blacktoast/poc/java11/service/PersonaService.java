package ar.org.blacktoast.poc.java11.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;

/**
 * @author blacktoast
 *
 */
public interface PersonaService {

	Optional<Persona> getById(Integer id) throws AppException, JsonParseException, JsonMappingException, IOException;
	
	void save(Persona persona) throws AppException, JsonProcessingException;
	
	Stream<Persona> getAll(Persona persona) throws AppException;

}
