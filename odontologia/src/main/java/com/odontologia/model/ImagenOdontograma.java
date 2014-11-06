package com.odontologia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "imagenodontograma")
public class ImagenOdontograma {

	@Id @GeneratedValue @Column(name="idimagenodontograma", nullable=false)
	private Integer idImagenOdontograma;
	
	@Column(name="urlimagen", nullable=true, length=200)
	private String urlImagen;

	@ManyToOne @JoinColumn(name="idsituaciondental", nullable=true)
	private SituacionDental imagenOdontogramaSituacionDental;
	
	@ManyToOne @JoinColumn(name="idsuperficiedental", nullable=true)
	private SuperficieDental imagenOdontogramaSuperficieDental;	

	@ManyToOne @JoinColumn(name="iddiente", nullable=true)
	private Diente imagenOdontogramaDiente;

	public Integer getIdImagenOdontograma() {
		return idImagenOdontograma;
	}

	public void setIdImagenOdontograma(Integer idImagenOdontograma) {
		this.idImagenOdontograma = idImagenOdontograma;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public SituacionDental getImagenOdontogramaSituacionDental() {
		return imagenOdontogramaSituacionDental;
	}

	public void setImagenOdontogramaSituacionDental(
			SituacionDental imagenOdontogramaSituacionDental) {
		this.imagenOdontogramaSituacionDental = imagenOdontogramaSituacionDental;
	}

	public SuperficieDental getImagenOdontogramaSuperficieDental() {
		return imagenOdontogramaSuperficieDental;
	}

	public void setImagenOdontogramaSuperficieDental(
			SuperficieDental imagenOdontogramaSuperficieDental) {
		this.imagenOdontogramaSuperficieDental = imagenOdontogramaSuperficieDental;
	}

	public Diente getImagenOdontogramaDiente() {
		return imagenOdontogramaDiente;
	}

	public void setImagenOdontogramaDiente(Diente imagenOdontogramaDiente) {
		this.imagenOdontogramaDiente = imagenOdontogramaDiente;
	}
	
}
