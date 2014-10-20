package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Archivo;
import com.odontologia.model.Distrito;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.DistritoService;
import com.odontologia.service.OdontologoService;
import com.odontologia.util.StaticHelp;


@Controller
public class OdontologoBean{

	@Autowired
	OdontologoService odontologoService;
	
	
	@Autowired
	DistritoService distritoService; 
	
	private Odontologo odontologo;
	private Persona persona;
	private Usuario usuario;
	private Distrito distrito;	
	
	private List<Odontologo> odontologos;	
	private List<Distrito> distritos;
	
	
	
	public OdontologoBean() {		
		odontologo = new Odontologo();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		odontologos = new ArrayList<>();		
	}
	
	public void registrarOdontologo(ActionEvent actionEvent){
		persona.setPersonaDistrito(distrito);
		persona.setPersonaUsuario(usuario);	
		odontologo.setOdontologoPersona(persona);
		if(odontologoService.registrarOdontologo(odontologo)){
			StaticHelp.correctMessage("Se ha registrado con éxito el odontólogo", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}		
		odontologo = new Odontologo();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		
	}
	
	public void prepararAccion(int id){
		odontologo = odontologoService.buscarPorId(id);
		persona = odontologo.getOdontologoPersona();
		usuario = odontologo.getOdontologoPersona().getPersonaUsuario();
		distrito = odontologo.getOdontologoPersona().getPersonaDistrito();
	}
	
	public void actualizarOdontologo(ActionEvent actionEvent){
		persona.setPersonaDistrito(distrito);
		persona.setPersonaUsuario(usuario);
		odontologo.setOdontologoPersona(persona);
		if(odontologoService.actualizarOdontologo(odontologo)){
			StaticHelp.correctMessage("Se ha actualizado con éxito el odontólogo", "");
			RequestContext.getCurrentInstance().update("formNuevoInsumo:growl");
		}		
		odontologo = new Odontologo();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}
	
	public void eliminarOdontologo(ActionEvent actionEvent){
		if(odontologoService.eliminarOdontologo(odontologo)){
			StaticHelp.correctMessage("Se ha eliminado con éxito el odontólogo", "");
			RequestContext.getCurrentInstance().update("formNuevoInsumo:growl");
		}		
		odontologo = new Odontologo();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}
	
	public void cancelar(ActionEvent actionEvent){
		odontologo = new Odontologo();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}
	
	
	public Odontologo getOdontologo() {
		return odontologo;
	}
	
	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}
	
	public List<Odontologo> getOdontologos() {
		odontologos = odontologoService.getOdontologos();
		return odontologos;
	}
	
	public void setOdontologos(List<Odontologo> odontologos) {
		this.odontologos = odontologos;
	}
	

	public List<Distrito> getDistritos() {
		distritos = distritoService.getDistritos();
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}
	
	
}
	


