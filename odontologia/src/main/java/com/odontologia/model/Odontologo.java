package com.odontologia.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Odontologo {

	@Id @GeneratedValue @Column(name="idodontologo", nullable=false)
	private Integer idOdontologo; 
	
	@Column(name="especialidad", nullable=true)
	private String especialidad;
	
	@Column(name="cop", length=15, nullable=true)
	private String cop;
	
	@Column(name="experienciaLaboral", nullable=true)
	private String experienciaLaboral;
	
	@ManyToOne @JoinColumn(name="idarchivo", nullable=true)
	private Archivo odontologoArchivo;
	
	@OneToMany(mappedBy="citaOdontologo")
	private Collection<Cita> odontologoCitas;
	
	@OneToMany(mappedBy="odontogramaOdontologo")
	private Collection<Odontograma> odontologoOdontogramas;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpersona", nullable = false)
	private Persona odontologoPersona;

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

	public String getCop() {
		return cop;
	}

	public void setCop(String cop) {
		this.cop = cop;
	}

	public String getExperienciaLaboral() {
		return experienciaLaboral;
	}

	public void setExperienciaLaboral(String experienciaLaboral) {
		this.experienciaLaboral = experienciaLaboral;
	}

	public Archivo getOdontologoArchivo() {
		return odontologoArchivo;
	}

	public void setOdontologoArchivo(Archivo odontologoArchivo) {
		this.odontologoArchivo = odontologoArchivo;
	}

	public Collection<Odontograma> getOdontologoOdontogramas() {
		return odontologoOdontogramas;
	}

	public void setOdontologoOdontogramas(
			Collection<Odontograma> odontologoOdontogramas) {
		this.odontologoOdontogramas = odontologoOdontogramas;
	}	
	
	
}
