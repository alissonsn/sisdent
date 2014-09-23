package com.odontologia.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cita {

	@Id @GeneratedValue @Column(name="cita", nullable=false)
	private Integer idCita;
	
	@Column(name="horainicio", nullable=true)
	private Timestamp horaInicio;
	
	@Column(name="horafin", nullable=true)
	private Timestamp horaFin;	
	
	@Column(name="fecha", nullable=true)
	private Timestamp fecha;
	
	@ManyToOne @JoinColumn(name="idodontologo", nullable=false)
	private Odontologo citaOdontologo;
	
	@ManyToOne @JoinColumn(name="idpaciente", nullable=false)
	private Paciente citaPaciente;
	
	@ManyToOne @JoinColumn(name="idestadocita", nullable=false)
	private EstadoCita citaEstadoCita;

	public Integer getIdCita() {
		return idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public Timestamp getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Timestamp horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Timestamp getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Timestamp horaFin) {
		this.horaFin = horaFin;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Odontologo getCitaOdontologo() {
		return citaOdontologo;
	}

	public void setCitaOdontologo(Odontologo citaOdontologo) {
		this.citaOdontologo = citaOdontologo;
	}

	public Paciente getCitaPaciente() {
		return citaPaciente;
	}

	public void setCitaPaciente(Paciente citaPaciente) {
		this.citaPaciente = citaPaciente;
	}

	public EstadoCita getCitaEstadoCita() {
		return citaEstadoCita;
	}

	public void setCitaEstadoCita(EstadoCita citaEstadoCita) {
		this.citaEstadoCita = citaEstadoCita;
	}
	
	
	
}
