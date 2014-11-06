package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImagenOdontogramaServiceImpl implements ImagenOdontogramaService{

	@PersistenceContext
	EntityManager em;	
		
	@Transactional
	public String getUrlImagen(Integer idDiente, Integer idSuperficie,
			Integer idSituacion) {		
		String resultado = null;
		try{
			Query q = em.createQuery("SELECT i.urlImagen FROM ImagenOdontograma i WHERE" +
									 " i.imagenOdontogramaDiente.idDiente=:idDiente" +
									 " and i.imagenOdontogramaSituacionDental.idSituacionDental=:idSituacion" +
									 " and i.imagenOdontogramaSuperficieDental.idSuperficieDental=:idSuperficie");
			q.setParameter("idDiente", idDiente);
			q.setParameter("idSituacion", idSituacion);
			q.setParameter("idSuperficie", idSuperficie);
			resultado = (String) q.getSingleResult();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}				
		return resultado;
	}

}
