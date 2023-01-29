package ar.org.blacktoast.poc.java11.test.persona;

import static io.blacktoast.utils.bean.OptionalValue.val;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.service.impl.PersonaServiceRedisImpl;
import io.blacktoast.test.ApplicationTestRedis;
import io.blacktoast.test.TestSuite;
import io.blacktoast.utils.redis.service.RedisService;
import io.blacktoast.utils.redis.service.RedisServiceImpl;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes = {ApplicationTestRedis.class,RedisServiceImpl.class,PersonaServiceRedisImpl.class})
public class RedisPersonaTestSuite extends TestSuite implements PersonaTestSuite{
	
	@Autowired
	private PersonaServiceRedisImpl taberna;
	
	@Autowired
	private RedisService redis;
	
	@After
	public void clean() {
		//NUNCA HACER EN ENTORNO PRODUCTIVO!!
		redis.flushAll();
	}
	

	@Test
	public void tabernaVaciaLuegoDeTest() throws Exception {
		Persona homero = crearUnHomero(val(null));
		
		taberna.save(homero);
		
		this.clean();
		
		var personaEncontrada = taberna.getById(homero.getId());
		
		Boolean tabernaEstaVacia = personaEncontrada.isEmpty();
		
		Assert.assertTrue(tabernaEstaVacia);
	}
	
	@Test
	public void homeroEstaEnLaTaberna() throws JsonParseException, JsonMappingException, AppException, IOException {
		Persona homero = crearUnHomero(val(1));
		
		taberna.save(homero);
		var personaEncontrada = taberna.getById(homero.getId());
		
		Assert.assertTrue(personaEncontrada.isPresent());
		Assert.assertEquals(personaEncontrada.get(), homero);
	}
	
}
