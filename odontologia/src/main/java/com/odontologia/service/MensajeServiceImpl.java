package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.odontologia.model.Mensaje;

@Service
public class MensajeServiceImpl implements MensajeService {
	@PersistenceContext
	EntityManager em;	
	
	@Transactional
	public boolean insertarMensaje(Mensaje mensaje) {
		boolean resultado=false;
		try{
			em.persist(mensaje);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;	
		
	}

	@Transactional
	public boolean eliminarMensaje(Mensaje mensaje) {
		boolean resultado=false;
		try{
			Mensaje toRemove = em.getReference(Mensaje.class, mensaje.getIdMensaje());
			em.remove(toRemove);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}
}
