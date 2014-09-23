package com.odontologia.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.TipoDiente;
import com.odontologia.service.TipoDienteService;


@Controller
public class TipoDienteBean{

	@Autowired
	TipoDienteService tipoDienteService;
	
	private TipoDiente tipodiente;
	
	public TipoDienteBean(){
		tipodiente = new TipoDiente();
	}

	public TipoDiente getTipodiente() {
		return tipodiente;
	}

	public void setTipodiente(TipoDiente tipodiente) {
		this.tipodiente = tipodiente;
	}
	
	public void insertar(){
		tipoDienteService.registrarTipoDiente(tipodiente);
	}
	
	
	
}
