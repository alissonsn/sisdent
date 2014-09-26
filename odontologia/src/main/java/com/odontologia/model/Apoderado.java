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
public class Apoderado {
	
	@Id @GeneratedValue @Column(name="idapoderado", nullable=false)
	private Integer idApoderado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idpersona", nullable=true)
	private Persona apoderadoPersona;
	
	@OneToMany(mappedBy="pacienteApoderado")
	private Collection<Paciente> apoderadoPacientes;	
	

	public Integer getIdApoderado() {
		return idApoderado;
	}

	public void setIdApoderado(Integer idApoderado) {
		this.idApoderado = idApoderado;
	}

	public Persona getApoderadoPersona() {
		return apoderadoPersona;
	}

	public void setApoderadoPersona(Persona apoderadoPersona) {
		this.apoderadoPersona = apoderadoPersona;
	}

	public Collection<Paciente> getApoderadoPacientes() {
		return apoderadoPacientes;
	}

	public void setApoderadoPacientes(Collection<Paciente> apoderadoPacientes) {
		this.apoderadoPacientes = apoderadoPacientes;
	}
	
	

}
