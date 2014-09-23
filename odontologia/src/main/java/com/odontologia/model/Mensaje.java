package com.odontologia.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Mensaje {

	@Id @GeneratedValue @Column(name="idmensaje", nullable=false)
	private Integer idMensaje;
	
	@Column(name="titulo", length=50, nullable=true)
	private String titulo;	
	
	@Column(name="texto", length=1000, nullable=true)
	private String texto;
	
	@Column(name="fecha", nullable=true)
	private Timestamp fecha;	
	
	@ManyToOne @JoinColumn(name="idemisor", nullable=false)
	private Usuario mensajeUsuarioEmisor;
	
	@ManyToOne @JoinColumn(name="idreceptor", nullable=false)
	private Usuario mensajeUsuarioReceptor;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public Usuario getMensajeUsuarioEmisor() {
		return mensajeUsuarioEmisor;
	}

	public void setMensajeUsuarioEmisor(Usuario mensajeUsuarioEmisor) {
		this.mensajeUsuarioEmisor = mensajeUsuarioEmisor;
	}

	public Usuario getMensajeUsuarioReceptor() {
		return mensajeUsuarioReceptor;
	}

	public void setMensajeUsuarioReceptor(Usuario mensajeUsuarioReceptor) {
		this.mensajeUsuarioReceptor = mensajeUsuarioReceptor;
	}

	public Integer getIdMensaje() {
		return idMensaje;
	}

	public void setIdMensaje(Integer idMensaje) {
		this.idMensaje = idMensaje;
	}
	
	
	
}
