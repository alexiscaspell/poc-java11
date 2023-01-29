package ar.org.blacktoast.poc.java11.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.service.PersonaService;

@RestController
public class PersonaController {

	public static final String BASE_URL = "/v1";

	@Autowired
	private PersonaService personaService;

	@GetMapping(BASE_URL + "/personas/{number}")
	public ResponseEntity<Object> getPersona(@PathVariable Integer number) {
		try {
			Optional<Persona> response = personaService.getById(number);

			if (response.isEmpty()) {
				return new ResponseEntity<>("No se encontraron registros", HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(response.get(), HttpStatus.OK);

		} catch (AppException e) {
			return e.buildRestResponse();

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(BASE_URL + "/personas")
	public ResponseEntity<Object> setPersona(@RequestBody Persona persona) {
		try {
			personaService.save(persona);

			return new ResponseEntity<>(HttpStatus.OK);
		} catch (AppException e) {
			return e.buildRestResponse();

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
