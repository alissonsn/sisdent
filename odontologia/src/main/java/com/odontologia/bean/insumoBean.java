package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Insumo;
import com.odontologia.service.InsumoService;
import com.odontologia.util.StaticHelp;

@Controller
public class insumoBean {

	@Autowired
	InsumoService insumoservice;
	
	private Insumo insumo;
	private List<Insumo> insumos;
	
	public insumoBean(){
		insumo = new Insumo();
		insumos = new ArrayList<>();
	}
	
	public void ingresarInsumo(){
		if(insumoservice.insertarInsumo(insumo)){
			StaticHelp.correctMessage("Se ha registrado con éxito el insumo", "");
			RequestContext.getCurrentInstance().update("formNuevoInsumo:growl");
		}	
		insumo = new Insumo();
	}
	
	public void cancelar(){
		
	}

	public Insumo getInsumo() {
		return insumo;
	}

	public void setInsumo(Insumo insumo) {
		this.insumo = insumo;
	}

	public List<Insumo> getInsumos() {
		insumos = insumoservice.getInsumos();
		return insumos;
	}

	public void setInsumos(List<Insumo> insumos) {
		this.insumos = insumos;
	}
	
	
	
}
