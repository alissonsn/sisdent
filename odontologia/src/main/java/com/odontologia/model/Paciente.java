package com.odontologia.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente {

	@Id @GeneratedValue @Column(name="idpaciente", nullable=false)
	private Integer idPaciente; 
	
	@Column(name="esasegurado", nullable=true)
	private Boolean esAsegurado;	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "idpersona", nullable = false)
	private Persona pacientePersona;
	
	@OneToMany(mappedBy="citaPaciente")
	private Collection<Cita> pacienteCitas;
	
	@OneToOne(mappedBy="fichaOdontologicaPaciente")
	private FichaOdontologica pacienteFichaOdontologica;
	
	@ManyToOne @JoinColumn(name="idapoderado", nullable=true)
	private Apoderado pacienteApoderado;

	public Collection<Cita> getPacienteCitas() {
		return pacienteCitas;
	}

	public void setPacienteCitas(Collection<Cita> pacienteCitas) {
		this.pacienteCitas = pacienteCitas;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
	}

	public Persona getPacientePersona() {
		return pacientePersona;
	}

	public void setPacientePersona(Persona pacientePersona) {
		this.pacientePersona = pacientePersona;
	}

	public Apoderado getPacienteApoderado() {
		return pacienteApoderado;
	}

	public void setPacienteApoderado(Apoderado pacienteApoderado) {
		this.pacienteApoderado = pacienteApoderado;
	}

	public boolean isEsAsegurado() {
		return esAsegurado;
	}

	public void setEsAsegurado(boolean esAsegurado) {
		this.esAsegurado = esAsegurado;
	}

	public FichaOdontologica getPacienteFichaOdontologica() {
		return pacienteFichaOdontologica;
	}

	public void setPacienteFichaOdontologica(
			FichaOdontologica pacienteFichaOdontologica) {
		this.pacienteFichaOdontologica = pacienteFichaOdontologica;
	}	
	
	
	
	
	
	
}
