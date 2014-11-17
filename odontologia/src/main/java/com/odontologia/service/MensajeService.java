package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Mensaje;

public interface MensajeService {

	public boolean insertarMensaje(Mensaje mensaje);

	public boolean eliminarMensaje(Mensaje mensaje);
	
	public boolean modificarMensaje(Mensaje mensaje);
	
	public List<Mensaje> getMensajesReceptorEst(Integer idPersona,boolean leido);
		
	public boolean getMensajesAvisoReceptor(Integer idPersona,boolean leido);
	
	public List<Mensaje> getMensajesReceptorList();
}
