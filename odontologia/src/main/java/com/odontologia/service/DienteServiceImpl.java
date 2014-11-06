package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Diente;

@Service
public class DienteServiceImpl implements DienteService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Diente dientePorId(Integer id) {
		return em.find(Diente.class, id);
	}

}
