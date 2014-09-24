package com.odontologia.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Odontologo {

	@Id @GeneratedValue @Column(name="idodontologo", nullable=false)
	private Integer idOdontologo; 
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpersona", nullable = false)
	private Persona odontologoPersona;
	
	@Column(name="especialidad", nullable=false)
	private String especialidad;
	
	@OneToMany(mappedBy="citaOdontologo")
	private Collection<Cita> odontologoCitas;
	
	@OneToMany(mappedBy="tratamientoOdontologo")
	private Collection<Tratamiento> odontologoTratamientos;

	public Collection<Tratamiento> getOdontologoTratamientos() {
		return odontologoTratamientos;
	}

	public void setOdontologoTratamientos(
			Collection<Tratamiento> odontologoTratamientos) {
		this.odontologoTratamientos = odontologoTratamientos;
	}

	public Collection<Cita> getOdontologoCitas() {
		return odontologoCitas;
	}

	public void setOdontologoCitas(Collection<Cita> odontologoCitas) {
		this.odontologoCitas = odontologoCitas;
	}

	public Integer getIdOdontologo() {
		return idOdontologo;
	}

	public void setIdOdontologo(Integer idOdontologo) {
		this.idOdontologo = idOdontologo;
	}

	public Persona getOdontologoPersona() {
		return odontologoPersona;
	}

	public void setOdontologoPersona(Persona odontologoPersona) {
		this.odontologoPersona = odontologoPersona;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}	
	
}
