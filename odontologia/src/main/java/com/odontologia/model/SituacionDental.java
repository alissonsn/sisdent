package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "situaciondental")
public class SituacionDental {

	@Id @GeneratedValue @Column(name="idsituaciondental", nullable=false)
	private Integer idSituacionDental;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@OneToMany(mappedBy="dienteOdontogramaSituacionDental")
	private Collection<DienteOdontograma> situacionDentalDienteOdontogramas;
	
	@OneToMany(mappedBy="imagenOdontogramaSituacionDental")
	private Collection<ImagenOdontograma> situacionDentalImagenOdontogramas;

	public Integer getIdSituacionDental() {
		return idSituacionDental;
	}

	public void setIdSituacionDental(Integer idSituacionDental) {
		this.idSituacionDental = idSituacionDental;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<DienteOdontograma> getSituacionDentalDienteOdontogramas() {
		return situacionDentalDienteOdontogramas;
	}

	public void setSituacionDentalDienteOdontogramas(
			Collection<DienteOdontograma> situacionDentalDienteOdontogramas) {
		this.situacionDentalDienteOdontogramas = situacionDentalDienteOdontogramas;
	}

	public Collection<ImagenOdontograma> getSituacionDentalImagenOdontogramas() {
		return situacionDentalImagenOdontogramas;
	}

	public void setSituacionDentalImagenOdontogramas(
			Collection<ImagenOdontograma> situacionDentalImagenOdontogramas) {
		this.situacionDentalImagenOdontogramas = situacionDentalImagenOdontogramas;
	}	
	
}
