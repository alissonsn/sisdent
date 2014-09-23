package com.odontologia.model;

import java.sql.Timestamp;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Usuario {

	@Id @GeneratedValue @Column(name = "idusuario", nullable = false)
	private Integer idUsuario;
	
	@Column(name="usuario", nullable=false, length=50)
	private String usuario;
	
	@Column(name="password", nullable=false, length=50)
	private String password;
	
	@Column(name="fechacreacion", nullable=true)
	private Timestamp fechaCreacion;
	
	@Column(name="urlfoto", nullable=true, length=200)
	private String urlFoto;
	
	@Column(name="esactivo", nullable=true)
	private Boolean esActivo;
	
	@OneToOne(mappedBy="personaUsuario")
	private Persona usuarioPersona;
	
	@OneToMany(mappedBy="mensajeUsuarioEmisor")
	private Collection<Mensaje> usuarioMensajeEmisores;
	
	@OneToMany(mappedBy="mensajeUsuarioReceptor")
	private Collection<Mensaje> usuarioMensajeReceptores;
	
	public Collection<Mensaje> getUsuarioMensajeEmisores() {
		return usuarioMensajeEmisores;
	}

	public void setUsuarioMensajeEmisores(Collection<Mensaje> usuarioMensajeEmisores) {
		this.usuarioMensajeEmisores = usuarioMensajeEmisores;
	}

	public Collection<Mensaje> getUsuarioMensajeReceptores() {
		return usuarioMensajeReceptores;
	}

	public void setUsuarioMensajeReceptores(
			Collection<Mensaje> usuarioMensajeReceptores) {
		this.usuarioMensajeReceptores = usuarioMensajeReceptores;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public Persona getUsuarioPersona() {
		return usuarioPersona;
	}

	public void setUsuarioPersona(Persona usuarioPersona) {
		this.usuarioPersona = usuarioPersona;
	}
	
	
	
}
