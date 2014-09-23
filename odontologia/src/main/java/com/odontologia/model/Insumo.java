package com.odontologia.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Insumo {

	@Id @GeneratedValue @Column(name="idinsumo", nullable=false)
	private Integer idInsumo;
	
	@Column(name="nombre", nullable=true, length=50)
	private String nombre;
	
	@Column(name="preciounitario", nullable=true)
	private Double precioUnitario;

	@OneToMany(mappedBy="insumoTratamientoInsumo")
	private Collection<InsumoTratamiento> insumoTratamientoInsumos;
	
	
	public Collection<InsumoTratamiento> getInsumoTratamientoInsumos() {
		return insumoTratamientoInsumos;
	}

	public void setInsumoTratamientoInsumos(
			Collection<InsumoTratamiento> insumoTratamientoInsumos) {
		this.insumoTratamientoInsumos = insumoTratamientoInsumos;
	}

	public Integer getIdInsumo() {
		return idInsumo;
	}

	public void setIdInsumo(Integer idInsumo) {
		this.idInsumo = idInsumo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
	
	
	
	
}
