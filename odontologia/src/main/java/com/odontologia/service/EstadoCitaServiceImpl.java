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
import com.odontologia.model.EstadoCita;

@Service
public class EstadoCitaServiceImpl implements EstadoCitaService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public EstadoCita buscarPorId(Integer idEstadoCita) {
		return em.find(EstadoCita.class, idEstadoCita);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<EstadoCita> getEstadoCitas() {
		List<EstadoCita> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT e FROM EstadoCita e");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
