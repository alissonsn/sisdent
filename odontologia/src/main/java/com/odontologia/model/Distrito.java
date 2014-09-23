package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="distrito")
public class Distrito {

	@Id @GeneratedValue @Column(name="iddistrito", nullable=false)
	private Integer idDistrito;
	
	@Column(name="nombre", length = 50, nullable = true)
	private String nombre;
	
	@OneToMany(mappedBy="personaDistrito")
	private Collection<Persona> distritoPersonas;

	public Integer getIdDistrito() {
		return idDistrito;
	}

	public void setIdDistrito(Integer idDistrito) {
		this.idDistrito = idDistrito;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Persona> getDistritoPersonas() {
		return distritoPersonas;
	}

	public void setDistritoPersonas(Collection<Persona> distritoPersonas) {
		this.distritoPersonas = distritoPersonas;
	}
	
	
}
