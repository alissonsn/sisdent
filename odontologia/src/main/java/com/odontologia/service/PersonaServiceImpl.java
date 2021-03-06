package com.odontologia.service;

import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.context.RequestContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Distrito;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.util.StaticHelp;

@Service
public class PersonaServiceImpl implements PersonaService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean registrarPersona(Persona persona) {
		boolean resultado=false;
		try{
			em.persist(persona);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean actualizarPersona(Persona persona) {
		boolean resultado=false;
		try{										
			em.merge(persona);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}
	
	@Transactional
	public Persona buscarPorUsuario(String usuario) {		
		Persona persona = new Persona();
		try{			
			Query q = em.createQuery("SELECT p FROM Persona p JOIN p.personaUsuario pu WHERE pu.usuario=:usuario");
			q.setParameter("usuario", usuario);
			persona = (Persona) q.getSingleResult();		
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			persona = null;
		}
		return persona;
		
	}

}
