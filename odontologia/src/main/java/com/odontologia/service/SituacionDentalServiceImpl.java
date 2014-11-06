package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.SituacionDental;

@Service
public class SituacionDentalServiceImpl implements SituacionDentalService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public SituacionDental buscarPorId(Integer id) {
		return em.find(SituacionDental.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SituacionDental> getAll() {
		List<SituacionDental> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT s FROM SituacionDental s");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
