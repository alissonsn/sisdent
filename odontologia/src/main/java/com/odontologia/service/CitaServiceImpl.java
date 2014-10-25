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
import com.odontologia.model.Odontologo;
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

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitasListaOdontologoSinRepetir(Integer idPaciente,
			 String nombreEstadoCita) {
		List<Cita> result = new ArrayList<>();
		try{
			
			//Arreglar método (SIN DISTINCT)
			Query q = em.createQuery("SELECT DISTINCT c FROM Cita c,Paciente p, EstadoCita e JOIN c.citaOdontologo co WHERE c.citaPaciente.idPaciente=:idPaciente AND c.citaEstadoCita.nombre=:nombreEstadoCita group by co.idOdontologo");
			q.setParameter("idPaciente", idPaciente);
			q.setParameter("nombreEstadoCita", nombreEstadoCita);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitasListaPacienteSinRepetir(Integer idOdontologo,
			 String nombreEstadoCita) {
		List<Cita> result = new ArrayList<>();
		try{
			
			//Arreglar método (SIN DISTINCT)
			Query q = em.createQuery("SELECT c FROM Cita c,Odontologo  o, EstadoCita e JOIN c.citaPaciente cp WHERE c.citaOdontologo.idOdontologo=:idOdontologo AND c.citaEstadoCita.nombre=:nombreEstadoCita group by cp.idPaciente");
			q.setParameter("idOdontologo", idOdontologo);
			q.setParameter("nombreEstadoCita", nombreEstadoCita);
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}
	
	@Transactional
	public EstadoCita estadoCitaFromId(Integer idEstadoCita) {
		return em.find(EstadoCita.class, idEstadoCita);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitasPorPaciente(Integer idPaciente) {
		List<Cita> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT c FROM Cita c JOIN c.citaPaciente cp WHERE cp.idPaciente=:idPaciente");
			q.setParameter("idPaciente", idPaciente);			
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cita> getCitasPorOdontologo(Integer idOdontologo) {
		List<Cita> result = new ArrayList<>();
		try{
			Query q = em.createQuery("SELECT c FROM Cita c JOIN c.citaOdontologo co WHERE co.idOdontologo=:idOdontologo");
			q.setParameter("idOdontologo", idOdontologo);			
			result = q.getResultList();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return result;
	}

	@Transactional
	public EstadoCita estadoCitaFromNombre(String nombre) {
		EstadoCita estadoCita = new EstadoCita();
		try{
			Query q = em.createQuery("SELECT ec FROM EstadoCita ec WHERE ec.nombre=:nombre");
			q.setParameter("nombre", nombre);
			estadoCita = (EstadoCita) q.getSingleResult();
		}
		catch(NoResultException e){
			System.out.println("ERROR:"+e.getMessage());
			
		}
		return estadoCita;


	}
}
