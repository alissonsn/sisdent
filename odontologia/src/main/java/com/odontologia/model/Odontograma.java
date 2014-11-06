package com.odontologia.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Odontograma {

	@Id @GeneratedValue @Column(name="idodontograma", nullable=false)
	private Integer idOdontograma; 	
	
	@Column(name="fechaactualizacion", nullable=true)
	private Timestamp fechaActualizacion;
	
	@ManyToOne @JoinColumn(name="idodontologo", nullable=true)
	private Odontologo odontogramaOdontologo;
	
	@OneToMany(mappedBy="odontogramaLogOdontograma")
	private Collection<OdontogramaLog> odontogramaOdontogramaLogs;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idfichaodontologica", nullable=true)
	private FichaOdontologica odontogramaFichaOdontologica;
	
	@OneToMany(mappedBy="dienteOdontogramaOdontograma")
	private Collection<DienteOdontograma> odontogramaDienteOdontogramas;

	public Integer getIdOdontograma() {
		return idOdontograma;
	}

	public void setIdOdontograma(Integer idOdontograma) {
		this.idOdontograma = idOdontograma;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Odontologo getOdontogramaOdontologo() {
		return odontogramaOdontologo;
	}

	public void setOdontogramaOdontologo(Odontologo odontogramaOdontologo) {
		this.odontogramaOdontologo = odontogramaOdontologo;
	}

	public Collection<OdontogramaLog> getOdontogramaOdontogramaLogs() {
		return odontogramaOdontogramaLogs;
	}

	public void setOdontogramaOdontogramaLogs(
			Collection<OdontogramaLog> odontogramaOdontogramaLogs) {
		this.odontogramaOdontogramaLogs = odontogramaOdontogramaLogs;
	}

	public FichaOdontologica getOdontogramaFichaOdontologica() {
		return odontogramaFichaOdontologica;
	}

	public void setOdontogramaFichaOdontologica(
			FichaOdontologica odontogramaFichaOdontologica) {
		this.odontogramaFichaOdontologica = odontogramaFichaOdontologica;
	}

	public Collection<DienteOdontograma> getOdontogramaDienteOdontogramas() {
		return odontogramaDienteOdontogramas;
	}

	public void setOdontogramaDienteOdontogramas(
			Collection<DienteOdontograma> odontogramaDienteOdontogramas) {
		this.odontogramaDienteOdontogramas = odontogramaDienteOdontogramas;
	}		
	
}
