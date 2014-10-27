package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Mensaje;

public interface MensajeService {

	public boolean insertarMensaje(Mensaje mensaje);

	public boolean eliminarMensaje(Mensaje mensaje);
	
	public List<Mensaje> getMensajesEmisorReceptor(Integer idPersona);
	
	public void propiedades();
	
	public String mensajeEmail();
	
	public String cabeceraEmail();
	
	public void preparaEnviar(String para, String cabecera, String cuerpo);
	
	public boolean EnviarMensaje(String to);
	
	public boolean EnviarMensaje(String to, String cabecera, String cuerpo);
	
	public boolean NotificarCitasUsuario(Integer idCita, Integer idUsuario);
}
