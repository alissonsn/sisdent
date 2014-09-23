package com.odontologia.bean;

import java.util.List;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Distrito;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.DistritoService;
import com.odontologia.service.PersonaService;
import com.odontologia.util.StaticHelp;

@Controller
public class registroBean {
	
	@Autowired
	DistritoService distritoservice;	
	
	@Autowired
	PersonaService personaservice;
	
	private List<Distrito> distritos;
	private Usuario usuario;
	private Persona persona;	
	private Distrito distrito;
	
	public registroBean(){
		usuario = new Usuario();
		persona = new Persona();
		distrito = new Distrito();
	}
	
	public void registrar(){				
		persona.setPersonaUsuario(usuario);
		persona.setPersonaDistrito(distrito);
		if(personaservice.registrarPersona(persona)){
			StaticHelp.correctMessage("Se ha registrado con éxito el usuario "+usuario.getUsuario(), "");
			RequestContext.getCurrentInstance().update("formRegistro:growl");
		}		
		distrito = new Distrito();
		usuario = new Usuario();
		persona = new Persona();
	}
	
	public void limpiarFormulario(){
		usuario = new Usuario();
		persona = new Persona();
		distrito = new Distrito();
	}

	public List<Distrito> getDistritos() {
		distritos = distritoservice.getDistritos();
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
	
	
	
}
