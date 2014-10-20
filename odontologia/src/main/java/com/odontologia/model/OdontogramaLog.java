package com.odontologia.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "odontogramalog")
public class OdontogramaLog {

	@Id @GeneratedValue @Column(name="idodontogramalog", nullable=false)
	private Integer idOdontogramaLog;
	
	@Column(name="eventoregistro", nullable=true, length=50)
	private String eventoRegistro;
	
	@Column(name="fecharegistro", nullable=true)
	private Timestamp fechaRegistro;	
	
	@Column(name="pcregistro", nullable=true, length=50)
	private String pcRegistro;
	
	@Column(name="ipregistro", nullable=true, length=50)
	private String ipRegistro;
	
	@OneToOne(mappedBy="personaUsuario")
	private Persona usuarioPersona;	
	
	@ManyToOne @JoinColumn(name="idusuario", nullable=false)
	private Usuario odontogramaLogUsuario;
	
	@ManyToOne @JoinColumn(name="idodontograma", nullable=false)
	private Odontograma odontogramaLogOdontograma;

	public Integer getIdOdontogramaLog() {
		return idOdontogramaLog;
	}

	public void setIdOdontogramaLog(Integer idOdontogramaLog) {
		this.idOdontogramaLog = idOdontogramaLog;
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

	public Persona getUsuarioPersona() {
		return usuarioPersona;
	}

	public void setUsuarioPersona(Persona usuarioPersona) {
		this.usuarioPersona = usuarioPersona;
	}

	public Usuario getOdontogramaLogUsuario() {
		return odontogramaLogUsuario;
	}

	public void setOdontogramaLogUsuario(Usuario odontogramaLogUsuario) {
		this.odontogramaLogUsuario = odontogramaLogUsuario;
	}

	public Odontograma getOdontogramaLogOdontograma() {
		return odontogramaLogOdontograma;
	}

	public void setOdontogramaLogOdontograma(Odontograma odontogramaLogOdontograma) {
		this.odontogramaLogOdontograma = odontogramaLogOdontograma;
	}		
}
