package ar.org.blacktoast.poc.java11.test.persona;

import java.sql.Date;
import java.util.Optional;

import ar.org.blacktoast.poc.java11.entity.Homero;
import ar.org.blacktoast.poc.java11.entity.Persona;


public interface PersonaTestSuite {
	
	default public Persona crearPersona(String nombre,String apellido,Date fechaNacimiento) {
		
		return Persona.builder().nombre(nombre).apellido(apellido).dni("dniDe"+nombre+apellido).fechaNacimiento(fechaNacimiento).build();
	}
	
	default public Homero crearUnHomero(Optional<Integer> id) {
		
		return Homero.homerBuilder().nombre("Homero").apellido("Simpson").id(id.orElse(null)).build();
	}
	
	default public Persona crearUnGorgory() {
		
		return crearPersona("Clancy", "Gorgory", null);
	}
	default public Persona crearUnBarney() {
		
		return crearPersona("Barney", "Gomez", null);
	}
	default public Persona crearUnApu() {
		
		return crearPersona("Apu", "Nahasamanoseque", null);
	}
	default public Persona crearUnSkinner() {
		
		return crearPersona("Seymour", "Skinner", null);
	}


}
