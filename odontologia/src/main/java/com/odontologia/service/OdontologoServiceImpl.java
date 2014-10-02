package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;

@Service
public class OdontologoServiceImpl implements OdontologoService {

	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public boolean registrarOdontologo(Odontologo odontologo) {
		boolean resultado=false;
		try{
										
			em.persist(odontologo);
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
	public List<Odontologo> getOdontologos() {
		List<Odontologo> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT o FROM Odontologo o");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public boolean actualizarOdontologo(Odontologo odontologo) {
		boolean resultado=false;
		try{										
			em.merge(odontologo);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean eliminarOdontologo(Odontologo odontologo) {
		boolean resultado=false;
		try{										
			em.remove(em.merge(odontologo));
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public Odontologo buscarPorId(int id) {
		return em.find(Odontologo.class, id);
	}

	@Transactional
	public Odontologo buscarPorPersona(Persona persona) {
		Odontologo odontologo = new Odontologo();
		try{			
			Query q = em.createQuery("SELECT o FROM Odontologo o JOIN o.odontologoPersona op WHERE op=:persona");
			q.setParameter("persona", persona);
			odontologo = (Odontologo) q.getSingleResult();		
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			odontologo = null;
		}
		return odontologo;
	}

}
