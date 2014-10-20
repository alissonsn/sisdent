package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "superficiedental")
public class SuperficieDental {

	@Id @GeneratedValue @Column(name="idsuperficiedental", nullable=false)
	private Integer idSuperficieDental;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@OneToMany(mappedBy="dienteOdontogramaSuperficieDental")
	private Collection<DienteOdontograma> superficieDentalDienteOdontogramas;
	
	@OneToMany(mappedBy="imagenOdontogramaSuperficieDental")
	private Collection<ImagenOdontograma> superficieDentalImagenOdontogramas;

	public Integer getIdSuperficieDental() {
		return idSuperficieDental;
	}

	public void setIdSuperficieDental(Integer idSuperficieDental) {
		this.idSuperficieDental = idSuperficieDental;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<DienteOdontograma> getSuperficieDentalDienteOdontogramas() {
		return superficieDentalDienteOdontogramas;
	}

	public void setSuperficieDentalDienteOdontogramas(
			Collection<DienteOdontograma> superficieDentalDienteOdontogramas) {
		this.superficieDentalDienteOdontogramas = superficieDentalDienteOdontogramas;
	}

	public Collection<ImagenOdontograma> getSuperficieDentalImagenOdontogramas() {
		return superficieDentalImagenOdontogramas;
	}

	public void setSuperficieDentalImagenOdontogramas(
			Collection<ImagenOdontograma> superficieDentalImagenOdontogramas) {
		this.superficieDentalImagenOdontogramas = superficieDentalImagenOdontogramas;
	}		
	
}
