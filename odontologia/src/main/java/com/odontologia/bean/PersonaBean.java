package com.odontologia.bean;

import java.util.List;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Distrito;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.PersonaService;
import com.odontologia.util.StaticHelp;

@Controller
public class PersonaBean {
	
	@Autowired
	PersonaService personaService;
	
	private Persona persona;
	private Distrito distrito;
	private Usuario usuario;
	
	public PersonaBean() {
		persona = new Persona();
		distrito = new Distrito();
		usuario = new Usuario();
	}
	
	public void actualizarPersonaMovil(){
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");		
		if (personaService.actualizarPersona(persona)) {
			StaticHelp.correctMessage(
					"Se ha actualizado con éxito el paciente", "");
			
		}		
		persona = new Persona();
		
	}
	
	public void actualizarPersonaWeb(ActionEvent actionEvent){
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");	
		if (personaService.actualizarPersona(persona)) {
			StaticHelp.correctMessage(
					"Se ha actualizado con éxito el paciente", "");
			RequestContext.getCurrentInstance().update("frmDatos:growl");
		}		
	}
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
