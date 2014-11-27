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
import com.odontologia.model.Odontograma;
import com.odontologia.model.Paciente;

@Service
public class OdontogramaServiceImpl implements OdontogramaService {

	@PersistenceContext
	EntityManager em;

	@Transactional
	public boolean insertarOdontograma(Odontograma odontograma) {
		boolean resultado = false;
		try {
			em.merge(odontograma);
			resultado = true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			resultado = false;
		}
		return resultado;
	}

	@Override
	public Odontograma lastInsert() {
		Odontograma result = new Odontograma();
		try {
			Query q = em
					.createQuery("SELECT MAX(o.idOdontograma) FROM Odontograma o");
			Integer lastId = (Integer) q.getSingleResult();
			result = em.find(Odontograma.class, lastId);
		} catch (NoResultException e) {
			System.out.println("ERROR: " + e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Odontograma> getOdontogramasPorPaciente(Paciente paciente) {
		List<Odontograma> result = null;
		try{
			Query q = em.createQuery("SELECT o FROM Odontograma o where o.odontogramaFichaOdontologica.fichaOdontologicaPaciente=:paciente");
			q.setParameter("paciente", paciente);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
	
	@Transactional
	public Odontograma buscarPorId(int id) {
		return em.find(Odontograma.class, id);
	}

	@Transactional
	public Odontograma getLastOdontograma(Paciente paciente) {
		Odontograma result = null;
		try{
			Query q = em.createQuery("SELECT o FROM Odontograma o WHERE o.odontogramaFichaOdontologica.fichaOdontologicaPaciente=:paciente" +
					                 " ORDER BY o.fechaActualizacion DESC ");
			q.setMaxResults(1);
			q.setParameter("paciente", paciente);
			result = (Odontograma) q.getSingleResult();
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
}
