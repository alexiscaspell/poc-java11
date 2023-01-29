package ar.org.blacktoast.poc.java11.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.exceptions.codes.PocJavaErrors;
import ar.org.blacktoast.poc.java11.service.PersonaService;

//@Service
public class PersonaServiceMockImpl implements PersonaService {

	private static final Logger LOG = LoggerFactory.getLogger(PersonaServiceMockImpl.class);
	
	private Persona unicaPersonaInTheWorld = Persona.builder().nombre("Homero").apellido("Simpson").dni("soyUnDni").id(1).build();
	private List<Persona> db = List.of(unicaPersonaInTheWorld);

	@Override
	public Optional<Persona> getById(Integer id) throws AppException {

		//EJEMPLO DE ERROR CUSTOM
		if (id.equals(100)) {
			throw AppException.builder().code(PocJavaErrors.ANYONE_ERROR).message("BOOM..!!!").build();
		}
		
		var personaEncontrada = db.stream().filter(p->p.getId().equals(id)).findFirst();
		
		LOG.info("Persona ->" + personaEncontrada.toString());

		return personaEncontrada;
	}

	@Override
	public void save(Persona persona) throws AppException {
		db.add(persona);
	}

	@Override
	public Stream<Persona> getAll(Persona persona) throws AppException {
		return db.stream();
	}

}
