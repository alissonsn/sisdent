package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Odontologo;

public interface OdontologoService {

	public boolean registrarOdontologo(Odontologo odontologo);
	public List<Odontologo> getOdontologos();
}
