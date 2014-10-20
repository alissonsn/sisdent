 package com.odontologia.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "citalog")
public class CitaLog {

	@Id @GeneratedValue @Column(name="idcitalog", nullable=false)
	private Integer idCitaLog;
	
	@Column(name="eventoregistro", nullable=true, length=50)
	private String eventoRegistro;
	
	@Column(name="fecharegistro", nullable=true)
	private Timestamp fechaRegistro;	
	
	@Column(name="pcregistro", nullable=true, length=50)
	private String pcRegistro;
	
	@Column(name="ipregistro", nullable=true, length=50)
	private String ipRegistro;
	
	@ManyToOne @JoinColumn(name="idusuario", nullable=false)
	private Usuario citaLogUsuario;	
	
	@ManyToOne @JoinColumn(name="idcita", nullable=false)
	private Cita citaLogCita;

	public Integer getIdCitaLog() {
		return idCitaLog;
	}

	public void setIdCitaLog(Integer idCitaLog) {
		this.idCitaLog = idCitaLog;
	}

	public String getEventoRegistro() {
		return eventoRegistro;
	}

	public void setEventoRegistro(String eventoRegistro) {
		this.eventoRegistro = eventoRegistro;
	}

	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getPcRegistro() {
		return pcRegistro;
	}

	public void setPcRegistro(String pcRegistro) {
		this.pcRegistro = pcRegistro;
	}

	public String getIpRegistro() {
		return ipRegistro;
	}

	public void setIpRegistro(String ipRegistro) {
		this.ipRegistro = ipRegistro;
	}

	public Usuario getCitaLogUsuario() {
		return citaLogUsuario;
	}

	public void setCitaLogUsuario(Usuario citaLogUsuario) {
		this.citaLogUsuario = citaLogUsuario;
	}

	public Cita getCitaLogCita() {
		return citaLogCita;
	}

	public void setCitaLogCita(Cita citaLogCita) {
		this.citaLogCita = citaLogCita;
	}	
	
	
}
