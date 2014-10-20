package com.odontologia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "enfermedadpadecida")
public class EnfermedadPadecida {

	@Id @GeneratedValue @Column(name="idenfermedadpadecida", nullable=false)
	private Integer idEnfermedadPadecida;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@ManyToOne @JoinColumn(name="idfichaodontologica", nullable=true)
	private FichaOdontologica enfermedadPadecidaFichaOdontologica;
	
	@ManyToOne @JoinColumn(name="idenfermedad", nullable=true)
	private Enfermedad enfermedadPadecidaEnfermedad;

	public Integer getIdEnfermedad() {
		return idEnfermedadPadecida;
	}

	public void setIdEnfermedad(Integer idEnfermedadPadecida) {
		this.idEnfermedadPadecida = idEnfermedadPadecida;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public FichaOdontologica getEnfermedadPadecidaFichaOdontologica() {
		return enfermedadPadecidaFichaOdontologica;
	}

	public void setEnfermedadPadecidaFichaOdontologica(
			FichaOdontologica enfermedadPadecidaFichaOdontologica) {
		this.enfermedadPadecidaFichaOdontologica = enfermedadPadecidaFichaOdontologica;
	}

	public Enfermedad getEnfermedadPadecidaEnfermedad() {
		return enfermedadPadecidaEnfermedad;
	}

	public void setEnfermedadPadecidaEnfermedad(
			Enfermedad enfermedadPadecidaEnfermedad) {
		this.enfermedadPadecidaEnfermedad = enfermedadPadecidaEnfermedad;
	}
		
	
}
