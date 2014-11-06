package com.odontologia.service;

import java.util.List;

import com.odontologia.model.SituacionDental;

public interface SituacionDentalService {

	public SituacionDental buscarPorId(Integer id);
	
	public List<SituacionDental> getAll();
	
}
