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
@Table(name = "fichaodontologica")
public class FichaOdontologica {

	@Id @GeneratedValue @Column(name="idfichaodontologica", nullable=false)
	private Integer idFichaOdontologica; 
	
	@Column(name="esalergico", nullable=true)
	private boolean esAlergico;	
	
	@Column(name="tomamedicacion", nullable=true)
	private boolean tomaMedicacion;	
	
	@Column(name="bajoatencion", nullable=true)
	private boolean bajoAtencion;	

	@Column(name="padeceenfermedad", nullable=true)
	private boolean padeceEnfermedad;	
	
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

	public boolean isEsAlergico() {
		return esAlergico;
	}

	public void setEsAlergico(boolean esAlergico) {
		this.esAlergico = esAlergico;
	}

	public boolean isTomaMedicacion() {
		return tomaMedicacion;
	}

	public void setTomaMedicacion(boolean tomaMedicacion) {
		this.tomaMedicacion = tomaMedicacion;
	}

	public boolean isBajoAtencion() {
		return bajoAtencion;
	}

	public void setBajoAtencion(boolean bajoAtencion) {
		this.bajoAtencion = bajoAtencion;
	}

	public boolean isPadeceEnfermedad() {
		return padeceEnfermedad;
	}

	public void setPadeceEnfermedad(boolean padeceEnfermedad) {
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
