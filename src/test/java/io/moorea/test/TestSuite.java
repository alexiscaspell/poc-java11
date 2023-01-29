package io.blacktoast.test;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

//@ContextConfiguration(classes= {ConfigurationTest.class})
//@ContextConfiguration(locations = {"/applicationContextTest.xml"})
@RunWith(SpringRunner.class)
//ESTO ES PARA QUE LEVANTE BIEN EL CONTEXTO (GUARDA, BEAN UTIL TE LEVANTA EL CONTEXTO PERO NO CON LOS MOCKS)
//@SpringBootTest(classes = { Application.class, BeanUtil.class })
//ESTO ES PARA QUE LIMPIE EL CONTEXTO LUEGO DE CADA METODO
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public abstract class TestSuite {

}