package com.odontologia.service;

import com.odontologia.model.Mensaje;

public interface MensajeService {

	public boolean insertarMensaje(Mensaje mensaje);

	public boolean eliminarMensaje(Mensaje mensaje);
}
