package com.odontologia.service;

import java.util.List;

import com.odontologia.model.DienteOdontograma;
import com.odontologia.model.Odontograma;

public interface DienteOdontogramaService {
	
	public boolean registrarDienteOdontograma(DienteOdontograma dienteOdontograma);
	
	public List<DienteOdontograma> getByOdontograma(Odontograma odontograma);
	
	public DienteOdontograma buscarPorId(int id);
	
	public boolean merge(DienteOdontograma dienteOdontograma);

}
