package ar.org.blacktoast.poc.java11.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.org.blacktoast.poc.java11.configuration.ConfigurationVars;

@RestController
public class ApplicationController {

	@GetMapping("/alive")
	public ResponseEntity<Object> status() {
		return new ResponseEntity<Object>("ALIVE!!!", HttpStatus.OK);
	}

	@GetMapping("/properties")
	public ResponseEntity<Object> getProperties() {
		try {
			Map<String, String> properties = ConfigurationVars.getPropertiesMap();
			return new ResponseEntity<>(properties, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
