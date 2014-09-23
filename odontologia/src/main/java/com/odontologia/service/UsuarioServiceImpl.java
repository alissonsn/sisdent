package com.odontologia.service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.odontologia.model.Usuario;
import com.odontologia.util.StaticHelp;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	@PersistenceContext
	EntityManager em;

	@Transactional
	public Usuario getUsuario(String usuario) {
		Usuario resultado = null;
		try{			
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.usuario=:usuReceived");
		query.setParameter("usuReceived", usuario);
		resultado = (Usuario) query.getSingleResult();
		}
		catch(NoResultException e){
			System.out.println("ERROR: "+e.getMessage());
		}
		return resultado;
	}

	@Override
	public boolean login(String usuario, String password) {
		boolean esLogueado=false;
		if(usuario==null || usuario.length()==0){
			StaticHelp.errorMessage("ERROR", "Debe ingresar un usuario");
			esLogueado = false;	
			return esLogueado;
		}
		if(password==null || password.length()==0){
			StaticHelp.errorMessage("ERROR", "Debe ingresar un password");
			esLogueado = false;
			return esLogueado;
		}		
		Usuario user = this.getUsuario(usuario);
		if(user==null){
			StaticHelp.errorMessage("ERROR", "No existe el usuario ingresado");
			esLogueado = false;
			return esLogueado;
		}
		if(user.getUsuario().equals(usuario)==true && user.getPassword().equals(password)==false){
			StaticHelp.errorMessage("ERROR", "El password es inválido");
			esLogueado = false;
			return esLogueado;
		}
		if(user.getUsuario().equals(usuario) && user.getPassword().equals(password)){
			StaticHelp.correctMessage("Bienvenido al sistema", user.getUsuarioPersona().getNombre());
			esLogueado = true;
			return esLogueado;			
		}
		return esLogueado;
	}

	@Transactional
	public boolean registrarUsuario(Usuario usuario) {
		boolean resultado=false;
		try{
			em.persist(usuario);
			resultado = true;
		}
		catch(Exception e){
			System.out.println("ERROR: "+e.getMessage());
			resultado = false;
		}		
		return resultado;
	}

}
