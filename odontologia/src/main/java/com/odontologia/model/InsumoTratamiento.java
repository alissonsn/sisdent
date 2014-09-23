package com.odontologia.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="insumotratamiento")
public class InsumoTratamiento {

	@Id @GeneratedValue @Column(name="idinsumotratamiento", nullable=false)
	private Integer idInsumoTratamiento;
	
	@Column(name="costoinsumo", nullable=true)
	private Double costoInsumo;
	
	@Column(name="cantidad", nullable=true)
	private Double cantidad;
	
	@ManyToOne @JoinColumn(name="idtratamiento", nullable=false)
	private Tratamiento insumoTratamientoTratamiento;
	
	@ManyToOne @JoinColumn(name="idinsumo", nullable=false)
	private Insumo insumoTratamientoInsumo;

	public Integer getIdInsumoTratamiento() {
		return idInsumoTratamiento;
	}

	public void setIdInsumoTratamiento(Integer idInsumoTratamiento) {
		this.idInsumoTratamiento = idInsumoTratamiento;
	}

	public Double getCostoInsumo() {
		return costoInsumo;
	}

	public void setCostoInsumo(Double costoInsumo) {
		this.costoInsumo = costoInsumo;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	public Tratamiento getInsumoTratamientoTratamiento() {
		return insumoTratamientoTratamiento;
	}

	public void setInsumoTratamientoTratamiento(
			Tratamiento insumoTratamientoTratamiento) {
		this.insumoTratamientoTratamiento = insumoTratamientoTratamiento;
	}

	public Insumo getInsumoTratamientoInsumo() {
		return insumoTratamientoInsumo;
	}

	public void setInsumoTratamientoInsumo(Insumo insumoTratamientoInsumo) {
		this.insumoTratamientoInsumo = insumoTratamientoInsumo;
	}
	
	
	
}
