package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public boolean modificarMensaje(Mensaje mensaje) {
		boolean resultado=false;
		try{
			em.merge(mensaje);
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
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Mensaje> getMensajesReceptorEst(Integer idPersona,boolean leido) {
		List<Mensaje> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT m FROM Mensaje m JOIN m.mensajeUsuarioReceptor mu JOIN mu.usuarioPersona up WHERE up.idPersona=:idPersona and m.leido=:leido");
			q.setParameter("idPersona", idPersona);	
			q.setParameter("leido", leido);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public boolean getMensajesAvisoReceptor(Integer idPersona,boolean leido) {
		List<Mensaje> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT m FROM Mensaje m JOIN m.mensajeUsuarioReceptor mu JOIN mu.usuarioPersona up WHERE up.idPersona=:idPersona and m.leido=:leido");
			q.setParameter("idPersona", idPersona);		
			q.setParameter("leido", leido);
			result = q.getResultList();
			
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		if(result!=null && result.size()>0){
			return true;
		}
		else{return false;}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Mensaje> getMensajesReceptorList() {
		List<Mensaje> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT m FROM Mensaje m JOIN m.mensajeUsuarioReceptor mu group by mu.idUsuario");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public Mensaje getById(Integer id) {
		return em.find(Mensaje.class, id);
	}
	
	
}
