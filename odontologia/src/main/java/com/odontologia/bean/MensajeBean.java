package com.odontologia.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Mensaje;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.CitaService;
import com.odontologia.service.MensajeService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;

@Controller
public class MensajeBean {

	private Odontologo odontologo;
	private Paciente paciente;
	private Mensaje mensaje;	
	@Autowired
	MensajeService mensajeservice;

	private List<Odontologo> odontologos;
	private List<Mensaje> mensajeReceptorLeido;
	private List<Mensaje> mensajeReceptorNoLeido;
	private boolean mensajePersona;
	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	CitaService citaService;
	
	
	public MensajeBean() {
		odontologo = new Odontologo();
        mensaje = new Mensaje();     
        mensaje.setMensajeUsuarioReceptor(new Usuario());
        paciente = new Paciente();
        odontologos = new ArrayList<>();
        mensajeReceptorLeido = new ArrayList<>();
        mensajeReceptorNoLeido = new ArrayList<>();
        mensajePersona = true;
	}
	
	public Mensaje getMensaje() {
		return mensaje;
	}

	public void setMensaje(Mensaje mensaje) {
		this.mensaje = mensaje;
	}
	
	
	public void insertarMensaje(){		
		Persona persona = new Persona();
		Usuario usuario = new Usuario();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		usuario =(Usuario) persona.getPersonaUsuario();
		Timestamp fecha= new Timestamp(System.currentTimeMillis());
		mensaje.setMensajeUsuarioEmisor(usuario);	
		mensaje.setFecha(fecha);
		mensaje.setLeido(true);
		
		if(mensajeservice.insertarMensaje(mensaje)){
			StaticHelp.correctMessage("Se ha registrado con éxito el mensaje", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		
		odontologo = new Odontologo();
        mensaje = new Mensaje();     
        mensaje.setMensajeUsuarioReceptor(new Usuario());
        paciente = new Paciente();
		
	}
	
	public List<Mensaje> getMensajeReceptorLeido() {
		Persona persona = new Persona();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		mensajeReceptorLeido = mensajeservice.getMensajesReceptorEst(persona.getIdPersona(),false);
		return mensajeReceptorLeido;
	}
	
	public void setMensajeReceptorLeido(List<Mensaje> mensajeReceptorLeido) {
		this.mensajeReceptorLeido= mensajeReceptorLeido;
	}
	
	public List<Mensaje> getMensajeReceptorNoLeido() {
		Persona persona = new Persona();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		mensajeReceptorNoLeido = mensajeservice.getMensajesReceptorEst(persona.getIdPersona(),true);
		return mensajeReceptorNoLeido;
	}

	public void setMensajeReceptorNoLeido(List<Mensaje> mensajeReceptorNoLeido) {
		this.mensajeReceptorNoLeido = mensajeReceptorNoLeido;
	}
		
	public Odontologo getOdontologo() {
		return odontologo;
	}

	public void setOdontologo(Odontologo odontologo) {
		this.odontologo = odontologo;
	}

	public List<Odontologo> getOdontologos() {
		return odontologos;
	}

	public void setOdontologos(List<Odontologo> odontologos) {
		this.odontologos = odontologos;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	
	public boolean getMensajePersona() {
		Persona persona = new Persona();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		mensajePersona= mensajeservice.getMensajesAvisoReceptor(persona.getIdPersona(),true);
		
		return mensajePersona;
	}
		
 public void mensajeVisto(){
	 
	  for(Mensaje mensaje : mensajeReceptorNoLeido){
		mensaje.setLeido(false);
		mensaje.setTitulo(mensaje.getTitulo()+" -'Mensaje Visto'");
		if(mensajeservice.modificarMensaje(mensaje)){
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
	  }
 }
	
}
