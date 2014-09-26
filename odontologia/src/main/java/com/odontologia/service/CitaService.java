package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Cita;

public interface CitaService {

	public List<Cita> getCitas();
	
	public Cita citaFromId(Integer id);
	
	public boolean insertarCita(Cita cita);
	
	public boolean modificarCita(Cita cita);
	
	public boolean eliminarCita(Cita cita);
	
	
	
}
