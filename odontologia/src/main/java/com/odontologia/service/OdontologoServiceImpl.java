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
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;

@Service
public class OdontologoServiceImpl implements OdontologoService {

	
	@PersistenceContext
	EntityManager em;
	
	
	@Transactional
	public boolean registrarOdontologo(Odontologo odontologo) {
		boolean resultado=false;
		try{
			
			Usuario usu = new Usuario();
			usu.setUsuario(odontologo.getOdontologoPersona().getPersonaUsuario().getUsuario());
			usu.setPassword(odontologo.getOdontologoPersona().getPersonaUsuario().getPassword());
			em.persist(usu);
			
			Persona per = new Persona();
			per.setNombre(odontologo.getOdontologoPersona().getNombre());
			per.setApellidoPaterno(odontologo.getOdontologoPersona().getApellidoPaterno());
			per.setApellidoMaterno(odontologo.getOdontologoPersona().getApellidoMaterno());
			per.setDireccion(odontologo.getOdontologoPersona().getDireccion());
			per.setCorreoElectronico(odontologo.getOdontologoPersona().getCorreoElectronico());
			per.setCelular(odontologo.getOdontologoPersona().getCelular());
			per.setTelefono(odontologo.getOdontologoPersona().getTelefono());
			per.setPersonaDistrito(odontologo.getOdontologoPersona().getPersonaDistrito());
			per.setPersonaUsuario(usu);
			em.persist(per);
								
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

}
