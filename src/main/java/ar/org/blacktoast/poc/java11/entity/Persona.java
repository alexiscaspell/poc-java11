package ar.org.blacktoast.poc.java11.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Persona {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1340464717821721260L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	@EqualsAndHashCode.Include
	private String dni;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

}
