package com.odontologia.bean;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Persona;
import com.odontologia.service.PersonaService;
import com.odontologia.util.StaticHelp;

@Controller
public class PersonaBean {
	
	@Autowired
	PersonaService personaService;
	
	private Persona persona;

	public PersonaBean() {
		persona = new Persona();
		
		
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
	
	
	

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}
