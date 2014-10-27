package com.odontologia.service;

import com.odontologia.model.FichaOdontologica;

public interface  FichaOdontologicaService {
	
	public boolean registrarFichaOdontologica(FichaOdontologica fichaO);
	public boolean actualizarFichaOdontologica(FichaOdontologica fichaO);
	public FichaOdontologica getById(Integer id);

}
