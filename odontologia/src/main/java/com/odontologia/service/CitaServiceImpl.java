package com.odontologia.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Cita;
import com.odontologia.model.EstadoCita;
import com.odontologia.model.Paciente;

@Service
public class CitaServiceImpl implements CitaService{

	@PersistenceContext
	EntityManager em;	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitas() {
		List<Cita> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT c FROM Cita c");
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public Cita citaFromId(Integer id) {		
		return em.find(Cita.class, id);
	}

	@Transactional
	public boolean insertarCita(Cita cita) {
		boolean resultado=false;
		try{
			em.persist(cita);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;		
	}

	@Transactional
	public boolean modificarCita(Cita cita) {
		boolean resultado=false;
		try{
			em.merge(cita);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

	@Transactional
	public boolean eliminarCita(Cita cita) {
		boolean resultado=false;
		try{
			Cita toRemove = em.getReference(Cita.class, cita.getIdCita());
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
	public List<Cita> getCitasPorPacientePorEstado(Integer idPaciente,
			Integer idEstadoCita) {
		List<Cita> result = new ArrayList<>();
		try{
			//Query q = em.createQuery("SELECT c FROM Cita c JOIN c.citaPaciente cp AND c.citaEstadoCita ce WHERE cp.idPaciente=:idPaciente and ce.idEstadoCita:idEstadoCita");
			//Arreglar método (SIN DISTINCT)
			Query q = em.createQuery("SELECT DISTINCT c FROM Cita c,Paciente p, EstadoCita e WHERE c.citaPaciente.idPaciente=:idPaciente AND c.citaEstadoCita.idEstadoCita=:idEstadoCita");
			q.setParameter("idPaciente", idPaciente);
			q.setParameter("idEstadoCita", idEstadoCita);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}


}
