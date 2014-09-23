package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Insumo;

@Service
public class InsumoServiceImpl implements InsumoService{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Insumo> getInsumos() {
		List<Insumo> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT i FROM Insumo i");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean insertarInsumo(Insumo insumo) {
		boolean resultado=false;
		try{
			em.persist(insumo);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean modificarInsumo(Insumo insumo) {
		boolean resultado=false;
		try{
			em.merge(insumo);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean eliminarInsumo(Insumo insumo) {
		boolean resultado=false;
		try{
			Insumo toRemove = em.getReference(Insumo.class, insumo.getIdInsumo());
			em.remove(toRemove);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}


	
	
}
