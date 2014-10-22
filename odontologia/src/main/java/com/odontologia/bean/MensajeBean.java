package com.odontologia.bean;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Cita;
import com.odontologia.model.Mensaje;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.CitaService;
import com.odontologia.service.MensajeService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;
import com.odontologia.util.citaData;

@Controller
public class MensajeBean {

	private Odontologo odontologo;
	private Paciente paciente;
	private Mensaje mensaje;	
    
	@Autowired
	MensajeService mensajeservice;

	private List<Odontologo> odontologos;
	private List<Mensaje> mensajeReceptor;
	
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
        mensajeReceptor = new ArrayList<>();
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
		
		if(mensajeservice.insertarMensaje(mensaje)){
			StaticHelp.correctMessage("Se ha registrado con éxito el mensaje", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		
	}
	
	public List<Mensaje> getMensajeReceptor() {
		Persona persona = new Persona();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		mensajeReceptor = mensajeservice.getMensajesEmisorReceptor(persona.getIdPersona());
		return mensajeReceptor;
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
	
	public void setMensajeReceptor(List<Mensaje> mensajeReceptor) {
		this.mensajeReceptor = mensajeReceptor;
	}
}
