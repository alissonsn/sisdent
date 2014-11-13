package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Diente;
import com.odontologia.model.DienteOdontograma;
import com.odontologia.model.Odontograma;
import com.odontologia.model.Odontologo;

@Service
public class DienteOdontogramaServiceImpl implements DienteOdontogramaService{

	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public boolean registrarDienteOdontograma(DienteOdontograma dienteOdontograma) {
		boolean resultado=false;
		try{										
			em.persist(dienteOdontograma);
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
	public List<DienteOdontograma> getByOdontograma(Odontograma odontograma) {
		List<DienteOdontograma> result = new ArrayList<>();
		try{			
			Query q = em.createQuery("SELECT d FROM DienteOdontograma d WHERE d.dienteOdontogramaOdontograma=:odontograma");
			q.setParameter("odontograma", odontograma);
			result = q.getResultList();		
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			result = null;
		}
		return result;
	}

	@Transactional
	public DienteOdontograma buscarPorId(int id) {
		return em.find(DienteOdontograma.class, id);
	}

	@Transactional
	public boolean merge(DienteOdontograma dienteOdontograma) {
		boolean resultado=false;
		try{										
			em.merge(dienteOdontograma);
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
	public List<DienteOdontograma> getModified(Odontograma odontograma) {
		List<DienteOdontograma> result = new ArrayList<>();
		try{			
			Query q = em.createQuery("SELECT d FROM DienteOdontograma d WHERE d.dienteOdontogramaOdontograma=:odontograma and d.esModificado=true");
			q.setParameter("odontograma", odontograma);
			result = q.getResultList();		
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			result = null;
		}
		return result;
	}

}
