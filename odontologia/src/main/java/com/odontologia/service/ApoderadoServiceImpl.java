package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Paciente;


@Service
public class ApoderadoServiceImpl implements ApoderadoService {

	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public boolean registrarApoderado(Apoderado apoderado) {
		boolean resultado=false;
		try{
										
			em.persist(apoderado);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean actualizarApoderado(Apoderado apoderado) {
		boolean resultado=false;
		try{
										
			em.merge(apoderado);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean eliminarApoderado(Apoderado apoderado) {
		boolean resultado=false;
		try{
										
			em.remove(em.merge(apoderado));
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Apoderado> getApoderados() {
		List<Apoderado> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT a FROM Apoderado a");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public Apoderado buscarPorId(int id) {
		return em.find(Apoderado.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> getPacienteporApoderado(Apoderado apoderado) {
		List<Paciente> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT p FROM Paciente p where p.pacienteApoderado=:apoderado");
			q.setParameter("apoderado", apoderado);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

}
