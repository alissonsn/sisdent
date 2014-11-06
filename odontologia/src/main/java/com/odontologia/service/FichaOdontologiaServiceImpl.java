package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Apoderado;
import com.odontologia.model.FichaOdontologica;
import com.odontologia.model.Paciente;

@Service
public class FichaOdontologiaServiceImpl implements FichaOdontologicaService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean registrarFichaOdontologica(FichaOdontologica fichaO) {
		boolean resultado = false;
		try {

			em.merge(fichaO);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public boolean actualizarFichaOdontologica(FichaOdontologica fichaO) {
		boolean resultado = false;
		try {

			em.merge(fichaO);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Transactional
	public FichaOdontologica getById(Integer id) {
		return em.find(FichaOdontologica.class, id);
	}

	@Override
	public FichaOdontologica fichaPorPaciente(Paciente paciente) {
		FichaOdontologica resultado = null;
		try {
			Query query = em.createQuery("SELECT fp FROM FichaOdontologica fp WHERE fp.fichaOdontologicaPaciente=:pacReceived");
			query.setParameter("pacReceived", paciente);
			resultado = (FichaOdontologica) query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return resultado;

	}

}
