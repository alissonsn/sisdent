package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.SuperficieDental;

@Service
public class SuperficieDentalServiceImpl implements SuperficieDentalService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public SuperficieDental buscarPorId(Integer id) {
		return em.find(SuperficieDental.class, id);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<SuperficieDental> getAll() {
		List<SuperficieDental> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT s FROM SuperficieDental s");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
	
}
