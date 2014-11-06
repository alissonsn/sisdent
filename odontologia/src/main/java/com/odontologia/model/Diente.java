package com.odontologia.model;

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
@Table(name = "diente")
public class Diente {

	@Id @GeneratedValue @Column(name="iddiente")
	private Integer idDiente;
	
	@Column(name="numeracionfdi", nullable = true)
	private Integer numeracionFdi;
	
	@ManyToOne @JoinColumn(name="idtipodiente", nullable=false)
	private TipoDiente dienteTipoDiente;

	@OneToMany(mappedBy="dienteOdontogramaDiente")
	private Collection<DienteOdontograma> dienteDienteOdontogramas;
	
	@OneToMany(mappedBy="imagenOdontogramaDiente")
	private Collection<ImagenOdontograma> DienteImagenOdontogramas;
	
	public Integer getIdDiente() {
		return idDiente;
	}

	public void setIdDiente(Integer idDiente) {
		this.idDiente = idDiente;
	}

	public TipoDiente getDienteTipoDiente() {
		return dienteTipoDiente;
	}

	public void setDienteTipoDiente(TipoDiente dienteTipoDiente) {
		this.dienteTipoDiente = dienteTipoDiente;
	}

	public Collection<DienteOdontograma> getDienteDienteOdontogramas() {
		return dienteDienteOdontogramas;
	}

	public void setDienteDienteOdontogramas(
			Collection<DienteOdontograma> dienteDienteOdontogramas) {
		this.dienteDienteOdontogramas = dienteDienteOdontogramas;
	}

	public Collection<ImagenOdontograma> getDienteImagenOdontogramas() {
		return DienteImagenOdontogramas;
	}

	public void setDienteImagenOdontogramas(
			Collection<ImagenOdontograma> dienteImagenOdontogramas) {
		DienteImagenOdontogramas = dienteImagenOdontogramas;
	}

	public Integer getNumeracionFdi() {
		return numeracionFdi;
	}

	public void setNumeracionFdi(Integer numeracionFdi) {
		this.numeracionFdi = numeracionFdi;
	}
	
	
	
}
