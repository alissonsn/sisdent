package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Cita;

@Service
public class CitaServiceImpl implements CitaService{

	@PersistenceContext
	EntityManager em;	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitas() {
		List<Cita> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT c FROM Cita c");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
