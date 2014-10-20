package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tipoenfermedad")
public class TipoEnfermedad {

	@Id @GeneratedValue @Column(name="idtipoenfermedad", nullable=false)
	private Integer idTipoEnfermedad;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@OneToMany(mappedBy="enfermedadTipoEnfermedad")
	private Collection<Enfermedad> tipoEnfermedadEnfermedades;

	public Integer getIdTipoEnfermedad() {
		return idTipoEnfermedad;
	}

	public void setIdTipoEnfermedad(Integer idTipoEnfermedad) {
		this.idTipoEnfermedad = idTipoEnfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Enfermedad> getTipoEnfermedadEnfermedades() {
		return tipoEnfermedadEnfermedades;
	}

	public void setTipoEnfermedadEnfermedades(
			Collection<Enfermedad> tipoEnfermedadEnfermedades) {
		this.tipoEnfermedadEnfermedades = tipoEnfermedadEnfermedades;
	}	
	
}
