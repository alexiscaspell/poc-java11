package ar.org.blacktoast.poc.java11.entity;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class Homero extends Persona{
	
	//SUPERA LOS 9000!
	private Long capacidadEstomacalDeRoscas = 9001l;
	
	@Builder(builderMethodName="homerBuilder")
	public Homero(Integer id, String nombre, String apellido, String dni, Date fechaNacimiento,Long capacidad) {
		super(id, nombre, apellido, dni, fechaNacimiento);
		this.capacidadEstomacalDeRoscas = capacidad;
	}
	
	public void comerRosca(Rosca unaRosca) {
		capacidadEstomacalDeRoscas--;
	}
	
	public Boolean estaLleno(Rosca rosca) {
		
		this.comerRosca(rosca);
		return capacidadEstomacalDeRoscas<0;
	}
	
}
