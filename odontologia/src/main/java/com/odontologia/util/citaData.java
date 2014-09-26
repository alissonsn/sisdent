package com.odontologia.util;

import java.util.Date;

import com.odontologia.model.EstadoCita;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;

public class citaData {

	private Odontologo odontologo;
	private Paciente paciente;
	private EstadoCita estadocita;
	private Integer idCita;
	private Date inicio;
	private Date fin;
	private Integer horaInicio;
	private Integer minInicio;
	private Integer horaFin;
	private Integer minFin;
	private String titulo;
	
	public citaData(Odontologo odontologo, Paciente paciente,
			EstadoCita estadocita, Integer idCita) {		
		this.odontologo = odontologo;
		this.paciente = paciente;
		this.estadocita = estadocita;
		this.idCita = idCita;
	}

	public Odontologo getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public EstadoCita getEstadocita() {
		return estadocita;
	}

	public void setEstadocita(EstadoCita estadocita) {
		this.estadocita = estadocita;
	}

	public Integer getIdCita() {
		return idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Integer getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Integer horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Integer getMinInicio() {
		return minInicio;
	}

	public void setMinInicio(Integer minInicio) {
		this.minInicio = minInicio;
	}

	public Integer getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(Integer horaFin) {
		this.horaFin = horaFin;
	}

	public Integer getMinFin() {
		return minFin;
	}

	public void setMinFin(Integer minFin) {
		this.minFin = minFin;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}	
	
	
	
}
