package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Distrito;

@Service
public class DistritoServiceImpl implements DistritoService{

	@PersistenceContext
	EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Distrito> getDistritos() {
		List<Distrito> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT d FROM Distrito d");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
