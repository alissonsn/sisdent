package com.odontologia.service;

import java.util.List;

import com.odontologia.model.SuperficieDental;

public interface SuperficieDentalService {
	
	public SuperficieDental buscarPorId(Integer id);
	
	public List<SuperficieDental> getAll();

}
