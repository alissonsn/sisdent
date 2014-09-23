package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="estadocita")
public class EstadoCita {

	@Id @GeneratedValue @Column(name="idestadocita", nullable=false)
	private Integer idEstadoCita;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@OneToMany(mappedBy="citaEstadoCita")
	private Collection<Cita> estadoCitaCitas;	

	public Collection<Cita> getEstadoCitaCitas() {
		return estadoCitaCitas;
	}

	public void setEstadoCitaCitas(Collection<Cita> estadoCitaCitas) {
		this.estadoCitaCitas = estadoCitaCitas;
	}

	public Integer getIdEstadoCita() {
		return idEstadoCita;
	}

	public void setIdEstadoCita(Integer idEstadoCita) {
		this.idEstadoCita = idEstadoCita;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
