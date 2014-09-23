package com.odontologia.model;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="historiaclinica")
public class HistoriaClinica {

	@Id @GeneratedValue @Column(name="idhistoriaclinica", nullable=false)
	private Integer idHistoriaClinica;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="idpaciente", nullable=false)
	private Paciente historiaClinicaPaciente;

	@OneToMany(mappedBy="tratamientoHistoriaClinica")
	private Collection<Tratamiento> historiaClinicaTratamientos;	
	
	public Collection<Tratamiento> getHistoriaClinicaTratamientos() {
		return historiaClinicaTratamientos;
	}

	public void setHistoriaClinicaTratamientos(
			Collection<Tratamiento> historiaClinicaTratamientos) {
		this.historiaClinicaTratamientos = historiaClinicaTratamientos;
	}

	public Integer getIdHistoriaClinica() {
		return idHistoriaClinica;
	}

	public void setIdHistoriaClinica(Integer idHistoriaClinica) {
		this.idHistoriaClinica = idHistoriaClinica;
	}

	public Paciente getHistoriaClinicaPaciente() {
		return historiaClinicaPaciente;
	}

	public void setHistoriaClinicaPaciente(Paciente historiaClinicaPaciente) {
		this.historiaClinicaPaciente = historiaClinicaPaciente;
	}
	
	
	
	
}
