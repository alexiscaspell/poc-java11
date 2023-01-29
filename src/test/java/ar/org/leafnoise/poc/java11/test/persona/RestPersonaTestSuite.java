package ar.org.blacktoast.poc.java11.test.persona;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ar.org.blacktoast.poc.java11.controller.PersonaController;
import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.service.PersonaService;
import io.blacktoast.test.RestTestSuite;
import static io.blacktoast.utils.bean.OptionalValue.val;


public class RestPersonaTestSuite extends RestTestSuite implements PersonaTestSuite {

	private static final String URL_EXAMPLE = PersonaController.BASE_URL+"/personas/1";

	@Mock
	private PersonaService personaService;

	@InjectMocks
	private PersonaController personaController;

	@Before
	public void setUp() throws Exception {
		super.setUp(personaController);
	}

	@Test
	public void testObtenerResponseYValidarAManoConJunit() throws Exception {

		Optional<Persona> respuestaEsperada = Optional.ofNullable(crearUnHomero(val(1)));

		Mockito.when(personaService.getById(1)).thenReturn(respuestaEsperada);

		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get(URL_EXAMPLE));

		Persona response = getResponseObject(result, Persona.class);

		Assert.assertNotNull(response);
		Assert.assertEquals(respuestaEsperada.get(), response);
	}

	@Test
	public void testSoloMeImportaQueElStatusSeaOk() throws Exception {
		Optional<Persona> respuestaEsperada = Optional.ofNullable(crearUnHomero(val(1)));

		Mockito.when(personaService.getById(1)).thenReturn(respuestaEsperada);
		mockMvc.perform(MockMvcRequestBuilders.get(URL_EXAMPLE)).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testValidarPropertyDelResponseSpringosamente() throws Exception {

		Optional<Persona> respuestaEsperada = Optional.ofNullable(crearUnHomero(val(1)));

		Mockito.when(personaService.getById(1)).thenReturn(respuestaEsperada);

		mockMvc.perform(MockMvcRequestBuilders.get(URL_EXAMPLE))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(respuestaEsperada.get().getId()));
	}
}