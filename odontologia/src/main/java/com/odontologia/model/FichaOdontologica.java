package com.odontologia.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fichaodontologica")
public class FichaOdontologica {

	@Id @GeneratedValue @Column(name="idfichaodontologica", nullable=false)
	private Integer idFichaOdontologica; 
	
	@Column(name="tiposangre",length=500, nullable=true)
	private String tipoSangre;
	
	@Column(name="esalergico",length=500, nullable=true)
	private String esAlergico;
	
	@Column(name="tomamedicacion",length=500, nullable=true)
	private String tomaMedicacion;
	
	@Column(name="bajoatencion",length=500, nullable=true)
	private String bajoAtencion;
	
	@Column(name="padeceenfermedad",length=500, nullable=true)
	private String padeceEnfermedad;		
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idpaciente", nullable=false)
	private Paciente fichaOdontologicaPaciente;
	
	@OneToMany(mappedBy="odontogramaFichaOdontologica")
	private Collection<Odontograma> fichaOdontologicaOdontogramas;
	
	@OneToMany(mappedBy="enfermedadPadecidaFichaOdontologica")
	private Collection<EnfermedadPadecida> fichaOdontologicaEnfermedadPadecidas;

	public Integer getIdFichaOdontologica() {
		return idFichaOdontologica;
	}

	public void setIdFichaOdontologica(Integer idFichaOdontologica) {
		this.idFichaOdontologica = idFichaOdontologica;
	}


	public String getTipoSangre() {
		return tipoSangre;
	}

	public void setTipoSangre(String tipoSangre) {
		this.tipoSangre = tipoSangre;
	}

	public String getEsAlergico() {
		return esAlergico;
	}

	public void setEsAlergico(String esAlergico) {
		this.esAlergico = esAlergico;
	}

	public String getTomaMedicacion() {
		return tomaMedicacion;
	}

	public void setTomaMedicacion(String tomaMedicacion) {
		this.tomaMedicacion = tomaMedicacion;
	}

	public String getBajoAtencion() {
		return bajoAtencion;
	}

	public void setBajoAtencion(String bajoAtencion) {
		this.bajoAtencion = bajoAtencion;
	}

	public String getPadeceEnfermedad() {
		return padeceEnfermedad;
	}

	public void setPadeceEnfermedad(String padeceEnfermedad) {
		this.padeceEnfermedad = padeceEnfermedad;
	}

	public Paciente getFichaOdontologicaPaciente() {
		return fichaOdontologicaPaciente;
	}

	public void setFichaOdontologicaPaciente(Paciente fichaOdontologicaPaciente) {
		this.fichaOdontologicaPaciente = fichaOdontologicaPaciente;
	}

	public Collection<Odontograma> getFichaOdontologicaOdontogramas() {
		return fichaOdontologicaOdontogramas;
	}

	public void setFichaOdontologicaOdontogramas(
			Collection<Odontograma> fichaOdontologicaOdontogramas) {
		this.fichaOdontologicaOdontogramas = fichaOdontologicaOdontogramas;
	}

	public Collection<EnfermedadPadecida> getFichaOdontologicaEnfermedadPadecidas() {
		return fichaOdontologicaEnfermedadPadecidas;
	}

	public void setFichaOdontologicaEnfermedadPadecidas(
			Collection<EnfermedadPadecida> fichaOdontologicaEnfermedadPadecidas) {
		this.fichaOdontologicaEnfermedadPadecidas = fichaOdontologicaEnfermedadPadecidas;
	}
		
}
