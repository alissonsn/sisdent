package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.TipoDiente;

@Service
public class TipoDienteServiceImpl implements TipoDienteService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean registrarTipoDiente(TipoDiente arg) {
		boolean esCorrecto = false;	
		try{
			arg.setUrlImagen("");
			em.persist(arg);			
			System.out.println("Se inserto correctamente un TipoDiente");
		}
		catch(IllegalArgumentException e){
			System.out.println("ERROR: "+e.getMessage());
			esCorrecto = false;
		}
		
		return esCorrecto;
	}

}
