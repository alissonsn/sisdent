package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.EstadoCita;

@Service
public class EstadoCitaServiceImpl implements EstadoCitaService {

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public EstadoCita buscarPorId(Integer idEstadoCita) {
		return em.find(EstadoCita.class, idEstadoCita);
	}

}
