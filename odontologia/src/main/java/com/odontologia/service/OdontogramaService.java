package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Odontograma;
import com.odontologia.model.Paciente;

public interface OdontogramaService {
	
	public boolean insertarOdontograma(Odontograma odontograma);
	
	public Odontograma lastInsert();
	
	public List<Odontograma> getOdontogramasPorPaciente(Paciente paciente);
	
	public Odontograma buscarPorId(int id);
	
	public Odontograma getLastOdontograma(Paciente paciente);

}
