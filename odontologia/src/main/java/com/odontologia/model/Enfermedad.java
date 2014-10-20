package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Enfermedad {

	@Id @GeneratedValue @Column(name="idenfermedad", nullable=false)
	private Integer idEnfermedad;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@OneToMany(mappedBy="enfermedadPadecidaEnfermedad")
	private Collection<EnfermedadPadecida> enfermedadEnfermedadPadecidas;
	
	@ManyToOne @JoinColumn(name="idtipoenfermedad", nullable=true)
	private TipoEnfermedad enfermedadTipoEnfermedad;

	public Integer getIdEnfermedad() {
		return idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<EnfermedadPadecida> getEnfermedadEnfermedadPadecidas() {
		return enfermedadEnfermedadPadecidas;
	}

	public void setEnfermedadEnfermedadPadecidas(
			Collection<EnfermedadPadecida> enfermedadEnfermedadPadecidas) {
		this.enfermedadEnfermedadPadecidas = enfermedadEnfermedadPadecidas;
	}

	public TipoEnfermedad getEnfermedadTipoEnfermedad() {
		return enfermedadTipoEnfermedad;
	}

	public void setEnfermedadTipoEnfermedad(TipoEnfermedad enfermedadTipoEnfermedad) {
		this.enfermedadTipoEnfermedad = enfermedadTipoEnfermedad;
	}		
	
}
