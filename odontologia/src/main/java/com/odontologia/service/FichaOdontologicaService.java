package com.odontologia.service;

import com.odontologia.model.FichaOdontologica;
import com.odontologia.model.Paciente;

public interface  FichaOdontologicaService {
	
	public boolean registrarFichaOdontologica(FichaOdontologica fichaO);
	public boolean actualizarFichaOdontologica(FichaOdontologica fichaO);
	public FichaOdontologica getById(Integer id);
	public FichaOdontologica fichaPorPaciente(Paciente paciente);

}
