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
import com.odontologia.model.Persona;

@Service
public class PacienteServiceImpl implements PacienteService {
	
	@PersistenceContext
	EntityManager em;
	

	@Transactional
	public boolean registrarPaciente(Paciente paciente) {
		boolean resultado=false;
		try{
										
			em.persist(paciente);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean actualizarPaciente(Paciente paciente) {
		boolean resultado=false;
		try{										
			em.merge(paciente);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean eliminarPaciente(Paciente paciente) {
		boolean resultado=false;
		try{										
			em.remove(em.merge(paciente));
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
	public List<Paciente> getPacientes() {
		List<Paciente> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT p FROM Paciente p");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public Paciente buscarPorId(int id) {
		return em.find(Paciente.class, id);
	}

	@Transactional
	public boolean registrarPacienteApoderado(Paciente paciente,
			Apoderado apoderado) {
		boolean resultado=false;
		try{
			em.persist(apoderado);										
			em.persist(paciente);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public Paciente buscarPorPersona(Persona persona) {
		Paciente paciente = new Paciente();
		try{			
			Query q = em.createQuery("SELECT p FROM Paciente p JOIN p.pacientePersona pp WHERE pp=:persona");
			q.setParameter("persona", persona);
			paciente = (Paciente) q.getSingleResult();		
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			paciente = null;
		}
		return paciente;
	}

}
