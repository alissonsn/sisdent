package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.FichaOdontologica;

@Service
public class FichaOdontologiaServiceImpl implements FichaOdontologicaService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean registrarFichaOdontologica(FichaOdontologica fichaO) {
		boolean resultado=false;
		try{
										
			em.merge(fichaO);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean actualizarFichaOdontologica(FichaOdontologica fichaO) {
		boolean resultado=false;
		try{
										
			em.merge(fichaO);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public FichaOdontologica getById(Integer id) {
		return em.find(FichaOdontologica.class, id);
	}

}
