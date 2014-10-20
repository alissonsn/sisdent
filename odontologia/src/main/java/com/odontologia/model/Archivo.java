package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Archivo {

	@Id @GeneratedValue @Column(name="idarchivo", nullable=false)
	private Integer idArchivo;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@Column(name="urlarchivo", nullable=true, length=50)
	private String urlArchivo;
	
	@OneToMany(mappedBy="odontologoArchivo")
	private Collection<Odontologo> archivoOdontologos;

	public Integer getIdArchivo() {
		return idArchivo;
	}

	public void setIdArchivo(Integer idArchivo) {
		this.idArchivo = idArchivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUrlArchivo() {
		return urlArchivo;
	}

	public void setUrlArchivo(String urlArchivo) {
		this.urlArchivo = urlArchivo;
	}

	public Collection<Odontologo> getArchivoOdontologos() {
		return archivoOdontologos;
	}

	public void setArchivoOdontologos(Collection<Odontologo> archivoOdontologos) {
		this.archivoOdontologos = archivoOdontologos;
	}
	
	
}
