package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Odontologo;
import com.odontologia.model.Persona;

public interface OdontologoService {

	public boolean registrarOdontologo(Odontologo odontologo);
	public List<Odontologo> getOdontologos();
	public boolean actualizarOdontologo(Odontologo odontologo);
	public boolean eliminarOdontologo(Odontologo odontologo);
	public Odontologo buscarPorId(int id);	
	public Odontologo buscarPorPersona(Persona persona);
}
