package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Distrito;

import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.DistritoService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;

@Controller
public class PacienteBean {

	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	DistritoService distritoService; 

	private Paciente paciente;
	private Persona persona;
	private Usuario usuario;
	private Distrito distrito;
	
	private List<Paciente> pacientes;
	private List<Distrito> distritos;

	public PacienteBean() {
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		pacientes = new ArrayList<>();
	}

	public void registrarPaciente(ActionEvent actionEvent) {
		persona.setPersonaDistrito(distrito);
		persona.setPersonaUsuario(usuario);
		paciente.setPacientePersona(persona);
		if (pacienteService.registrarPaciente(paciente)) {
			StaticHelp.correctMessage(
					"Se ha registrado con éxito el paciente", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();

	}

	public void prepararAccion(int id) {
		paciente = pacienteService.buscarPorId(id);
		persona = paciente.getPacientePersona();
		usuario = paciente.getPacientePersona().getPersonaUsuario();
		distrito = paciente.getPacientePersona().getPersonaDistrito();
		
	}

	public void actualizarPaciente(ActionEvent actionEvent) {
		persona.setPersonaDistrito(distrito);
		persona.setPersonaUsuario(usuario);
		paciente.setPacientePersona(persona);
		if (pacienteService.actualizarPaciente(paciente)) {
			StaticHelp.correctMessage(
					"Se ha actualizado con éxito el paciente", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}

	public void eliminarPaciente(ActionEvent actionEvent) {		
		paciente.setPacientePersona(persona);
		if (pacienteService.eliminarPaciente(paciente)) {
			StaticHelp.correctMessage(
					"Se ha eliminado con éxito el paciente", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}

	public void cancelar(ActionEvent actionEvent) {
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Paciente> getPacientes() {
		pacientes = pacienteService.getPacientes();
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
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

	public List<Distrito> getDistritos() {
		distritos = distritoService.getDistritos();
		return distritos;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}

}
