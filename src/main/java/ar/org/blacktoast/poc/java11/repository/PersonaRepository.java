package ar.org.blacktoast.poc.java11.repository;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.org.blacktoast.poc.java11.entity.Persona;

@Repository
@Transactional
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

//	@Modifying
//	@Query(nativeQuery = true, value = "exec dbo.I_persona :#{#p.nombre}, :#{#p.apellido}, :#{#p.dni}, :#{#p.fechaNacimiento}")
//	void savePersona(@Param("p") Persona persona);
//
//	@Query(nativeQuery = true, value = "exec dbo.S_persona :#{#p.id}, :#{#p.nombre}, :#{#p.apellido}, :#{#p.dni}")
//	List<Persona> getpersonas(@Param("p") Persona personaABuscar);

}