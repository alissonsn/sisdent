package com.odontologia.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Tratamiento {

	@Id @GeneratedValue @Column(name="idtratamiento", nullable=false)
	private Integer idTratamiento;
	
	@Column(name="detalle", nullable=true, length=1000)
	private String detalle;
	
	@Column(name="fecha", nullable=true)
	private Timestamp fecha;
	
	@Column(name="costotratamiento", nullable=true)
	private Double costoTratamiento;
	
	@Column(name="costototal", nullable=true)
	private Double costoTotal;
	
	@ManyToOne @JoinColumn(name="iddienteodontograma", nullable=true)
	private DienteOdontograma tratamientoDienteOdontograma;

	public Integer getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Double getCostoTratamiento() {
		return costoTratamiento;
	}

	public void setCostoTratamiento(Double costoTratamiento) {
		this.costoTratamiento = costoTratamiento;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public DienteOdontograma getTratamientoDientoOdontograma() {
		return tratamientoDienteOdontograma;
	}

	public void setTratamientoDientoOdontograma(
			DienteOdontograma tratamientoDientoOdontograma) {
		this.tratamientoDienteOdontograma = tratamientoDientoOdontograma;
	}
	
}
