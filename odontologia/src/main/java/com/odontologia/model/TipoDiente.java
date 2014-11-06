package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "tipodiente")
public class TipoDiente {

	@Id @GeneratedValue @Column(name="idtipodiente")
	private Integer idTipoDiente;
	
	@Column(name="nombre", length=50, nullable=true)
	private String nombre;
	
	@OneToMany(mappedBy="dienteTipoDiente")
	private Collection<Diente> tipoDienteDientes;

	public Integer getIdTipoDiente() {
		return idTipoDiente;
	}

	public void setIdTipoDiente(Integer idTipoDiente) {
		this.idTipoDiente = idTipoDiente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Collection<Diente> getTipoDienteDientes() {
		return tipoDienteDientes;
	}

	public void setTipoDienteDientes(Collection<Diente> tipoDienteDientes) {
		this.tipoDienteDientes = tipoDienteDientes;
	}

}
