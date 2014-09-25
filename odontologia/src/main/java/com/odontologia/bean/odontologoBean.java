package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Distrito;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Persona;
import com.odontologia.service.DistritoService;
import com.odontologia.service.OdontologoService;
import com.odontologia.util.StaticHelp;

@Controller
public class odontologoBean {
	
	@Autowired
	OdontologoService odontologoService;
	
	@Autowired
	DistritoService distritoService;
	
	private Odontologo odontologo;
	private List<Odontologo> odontologos;
	private Distrito distrito;
	
	private List<Distrito> distritos;
	
	
	public odontologoBean() {		
		odontologo = new Odontologo();
		distrito = new Distrito();		
		odontologo.setOdontologoPersona(new Persona());
		//odontologo.getOdontologoPersona().setPersonaDistrito(new Distrito());
		//odontologo.getOdontologoPersona().setPersonaUsuario(new Usuario());
		odontologos = new ArrayList<>();
	}
	
	public void registrarOdontologo(){
		odontologo.getOdontologoPersona().setPersonaDistrito(distrito);
		if(odontologoService.registrarOdontologo(odontologo)){
			StaticHelp.correctMessage("Se ha registrado con éxito el odontólogo", "");
			RequestContext.getCurrentInstance().update("formNuevoInsumo:growl");
		}
		odontologo.setOdontologoPersona(new Persona());
		odontologo = new Odontologo();
		
	}
	
	public void cancelar(){
		odontologo = new Odontologo();
		odontologo.setOdontologoPersona(new Persona());
		odontologo.getOdontologoPersona().setPersonaDistrito(new Distrito());
	}
	
	
	public Odontologo getOdontologo() {
		return odontologo;
	}
	
	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}
	
	public List<Odontologo> getOdontologos() {
		odontologos = odontologoService.getOdontologos();
		return odontologos;
	}
	
	public void setOdontologos(List<Odontologo> odontologos) {
		this.odontologos = odontologos;
	}
	
	
	

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public List<Distrito> getDistritos() {
		distritos = distritoService.getDistritos();
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

}
