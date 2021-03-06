package com.odontologia.service;

import com.odontologia.model.Persona;

public interface PersonaService {

	public boolean registrarPersona(Persona persona);
	public boolean actualizarPersona(Persona persona);
	public Persona buscarPorUsuario(String usuario);
	
}
