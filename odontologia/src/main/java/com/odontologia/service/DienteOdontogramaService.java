package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Diente;
import com.odontologia.model.DienteOdontograma;
import com.odontologia.model.Odontograma;
import com.odontologia.model.Paciente;

public interface DienteOdontogramaService {
	
	public boolean registrarDienteOdontograma(DienteOdontograma dienteOdontograma);
	
	public List<DienteOdontograma> getByOdontograma(Odontograma odontograma);
	
	public DienteOdontograma buscarPorId(int id);
	
	public boolean merge(DienteOdontograma dienteOdontograma);

	public List<DienteOdontograma> getModified(Odontograma odontograma);
	
	public List<DienteOdontograma> getHistorialDiente(Diente diente, Paciente paciente);
	
	public List<Diente> getDientes();
}
