package ar.org.blacktoast.poc.java11.test.persona;

import static io.blacktoast.utils.bean.OptionalValue.val;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import ar.org.blacktoast.poc.java11.entity.Persona;
import ar.org.blacktoast.poc.java11.exceptions.AppException;
import ar.org.blacktoast.poc.java11.service.impl.PersonaServiceSQLImpl;
import io.blacktoast.test.ApplicationTestSql;
import io.blacktoast.test.TestSuite;

@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes = {ApplicationTestSql.class,PersonaServiceSQLImpl.class})
public class MysqlPersonaTestSuite extends TestSuite implements PersonaTestSuite{
	
	@Autowired
	private PersonaServiceSQLImpl taberna;
	
	@After
	public void clean() {
		//NUNCA HACER EN ENTORNO PRODUCTIVO!!
		taberna.deleteAll();
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
	public void homeroEstaEnLaTaberna() throws AppException, IOException  {
		Persona homero = crearUnHomero(val(null));
		
		taberna.save(homero);
		var personaEncontrada = taberna.getById(homero.getId());
		
		Assert.assertTrue(personaEncontrada.isPresent());
		Assert.assertEquals(personaEncontrada.get(), homero);
	}
	
}
