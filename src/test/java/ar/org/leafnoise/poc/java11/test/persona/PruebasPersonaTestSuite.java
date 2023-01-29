package ar.org.blacktoast.poc.java11.test.persona;

import static io.blacktoast.utils.bean.OptionalValue.val;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.org.blacktoast.poc.java11.entity.Homero;
import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.entity.Rosca;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import io.blacktoast.test.TestSuite;

public class PruebasPersonaTestSuite extends TestSuite implements PersonaTestSuite {

	private List<Persona> crearGrupoLosBorbotones() {
		var homero = crearUnHomero(val(null));
		var apu = crearUnApu();
		var skinner = crearUnSkinner();
		var gorgory = crearUnGorgory();

		return List.of(homero, apu, skinner, gorgory);
	}

	@Test
	public void losBorbotonesCambianAGorgoryPorBarney() {

		var losBorbotones = crearGrupoLosBorbotones();

		Persona barney = crearUnBarney();
		Persona gorgory = crearUnGorgory();

		// STREAM NO TIENE COPY Y List.of DEVUELVE UNA LISTA INMUTABLE!!
		var losNuevosBorbotones = List.copyOf(losBorbotones).stream().collect(toList());

		losNuevosBorbotones.replaceAll(borboton -> borboton.equals(gorgory) ? barney : borboton);

		Boolean losBorbotonesTenianAGorgory = losBorbotones.contains(gorgory);
		Boolean losBorbotonesTienenABarney = losNuevosBorbotones.contains(barney);
		Boolean losBorbotonesNoTienenAGorgory = !losNuevosBorbotones.contains(gorgory);

		Assert.assertEquals(losNuevosBorbotones.size(), losBorbotones.size());
		Assert.assertTrue(losBorbotonesTenianAGorgory);
		Assert.assertTrue(losBorbotonesTienenABarney && losBorbotonesNoTienenAGorgory);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void noSeAdmitenHomerosPorqueYaHayUno()
			throws JsonParseException, JsonMappingException, AppException, IOException {

		var otroHomero = crearPersona("Homero", "Fulano", null);

		// ESTO ES EQUIVALENTE A HACER List.of(otroHomero)
		List<Persona> casitaDelArbol = Stream.of(otroHomero).collect(toUnmodifiableList());

		var homero = crearUnHomero(val(null));

		casitaDelArbol.add(homero);
	}

	@Test
	public void homeroSeComeTodasLasRoscasDelMundo() {

		Homero homero = crearUnHomero(val(null));

		Map<String, Object> mapaSaboresYKcal = Map.of("frutilla", 200, "chocolate", 300, "cereza", 270, "comun", "180");
		

		List<Rosca> todasLasRoscasDelMundo = new ArrayList<>();
		
		mapaSaboresYKcal.forEach((s,k)->
								 todasLasRoscasDelMundo.add(Rosca.builder()
										 		       .sabor(s)
										 		       .kcal((Integer.valueOf(String.valueOf(k))))
										 		       .build()));
		
		var capacidadDeHomero = Long.valueOf(todasLasRoscasDelMundo.size() * 2);

		homero.setCapacidadEstomacalDeRoscas(capacidadDeHomero);

		var roscasRestantes = todasLasRoscasDelMundo.stream().dropWhile(r -> !homero.estaLleno(r));

		var cantidadRoscasRestantes = roscasRestantes.count();

		Assert.assertEquals(0, cantidadRoscasRestantes);
	}

	@Test
	public void homeroNoPuedeComerLaUltimaRosca() {

		Homero homero = crearUnHomero(val(null));

		Map<String, Object> mapaSaboresYKcal = Map.of("frutilla", 200, "chocolate", 300, "cereza", 270, "comun", "180");

		List<Rosca> cajaDeRoscas = new ArrayList<>();
		
		mapaSaboresYKcal.forEach((s,k)->
								 cajaDeRoscas.add(Rosca.builder()
										 		       .sabor(s)
										 		       .kcal((Integer.valueOf(String.valueOf(k))))
										 		       .build()));

		var capacidadDeHomero = Long.valueOf(cajaDeRoscas.size() - 1);

		homero.setCapacidadEstomacalDeRoscas(capacidadDeHomero);

		var roscasRestantes = cajaDeRoscas.stream().dropWhile(r -> !homero.estaLleno(r));

		var cantidadRoscasRestantes = roscasRestantes.count();

		Assert.assertEquals(1, cantidadRoscasRestantes);
	}

}
