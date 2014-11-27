package com.odontologia.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dienteodontograma")
public class DienteOdontograma {

	@Id @GeneratedValue @Column(name="iddienteodontograma", nullable=false)
	private Integer idDienteOdontograma;
	
	@Column(name="detalle", nullable=true)
	private String detalle;
	
	@Column(name="urlimagen", nullable=true, length=200)
	private String urlImagen;
	
	@Column(name="esmodificado", nullable=true)
	private Boolean esModificado;
	
	@Column(name="fechamodificacion", nullable=true)
	private Timestamp fechaModificacion;
	
	@ManyToOne @JoinColumn(name="idodontograma", nullable=false)
	private Odontograma dienteOdontogramaOdontograma;
	
	@ManyToOne @JoinColumn(name="iddiente", nullable=false)
	private Diente dienteOdontogramaDiente;
	
	@ManyToOne @JoinColumn(name="idsituaciondental", nullable=false)
	private SituacionDental dienteOdontogramaSituacionDental;
	
	@ManyToOne @JoinColumn(name="idsuperficiedental", nullable=false)
	private SuperficieDental dienteOdontogramaSuperficieDental;
	
	@OneToMany(mappedBy="tratamientoDienteOdontograma")
	private Collection<Tratamiento> dienteOdontogramaTratamientos;	
	
	public Integer getIdDienteOdontograma() {
		return idDienteOdontograma;
	}

	public void setIdDienteOdontograma(Integer idDienteOdontograma) {
		this.idDienteOdontograma = idDienteOdontograma;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Odontograma getDienteOdontogramaOdontograma() {
		return dienteOdontogramaOdontograma;
	}

	public void setDienteOdontogramaOdontograma(
			Odontograma dienteOdontogramaOdontograma) {
		this.dienteOdontogramaOdontograma = dienteOdontogramaOdontograma;
	}

	public Diente getDienteOdontogramaDiente() {
		return dienteOdontogramaDiente;
	}

	public void setDienteOdontogramaDiente(Diente dienteOdontogramaDiente) {
		this.dienteOdontogramaDiente = dienteOdontogramaDiente;
	}

	public SituacionDental getDienteOdontogramaSituacionDental() {
		return dienteOdontogramaSituacionDental;
	}

	public void setDienteOdontogramaSituacionDental(
			SituacionDental dienteOdontogramaSituacionDental) {
		this.dienteOdontogramaSituacionDental = dienteOdontogramaSituacionDental;
	}

	public SuperficieDental getDienteOdontogramaSuperficieDental() {
		return dienteOdontogramaSuperficieDental;
	}

	public void setDienteOdontogramaSuperficieDental(
			SuperficieDental dienteOdontogramaSuperficieDental) {
		this.dienteOdontogramaSuperficieDental = dienteOdontogramaSuperficieDental;
	}

	public Collection<Tratamiento> getDienteOdontogramaTratamientos() {
		return dienteOdontogramaTratamientos;
	}

	public void setDienteOdontogramaTratamientos(
			Collection<Tratamiento> dienteOdontogramaTratamientos) {
		this.dienteOdontogramaTratamientos = dienteOdontogramaTratamientos;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}

	public Boolean getEsModificado() {
		return esModificado;
	}

	public void setEsModificado(Boolean esModificado) {
		this.esModificado = esModificado;
	}

	public Timestamp getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Timestamp fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}			
			
}
