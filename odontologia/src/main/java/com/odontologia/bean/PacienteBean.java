package com.odontologia.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Distrito;
import com.odontologia.model.FichaOdontologica;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.DistritoService;
import com.odontologia.service.FichaOdontologicaService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;

@Controller
public class PacienteBean {

	@Autowired
	PacienteService pacienteService;

	@Autowired
	DistritoService distritoService;
	
	@Autowired
	FichaOdontologicaService fichaService;

	private Paciente paciente;
	private Persona persona;
	private Usuario usuario;
	private Distrito distrito;
	private Apoderado apoderado;
	private Persona personaApoderado;
	private FichaOdontologica fichaOdontologica;

	private List<Paciente> pacientes;
	private List<Distrito> distritos;

	//Atributos dinámicos para las vistas
	private Date fechaHoy;
	

	public PacienteBean() {
		System.out.println("GRRRRRRRRRRRGRGRGR");
		fichaOdontologica = new FichaOdontologica();
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		apoderado = new Apoderado();
		personaApoderado = new Persona();		
		pacientes = new ArrayList<>();
	}
	
	public void prepararInsertar(ActionEvent actionEvent){
		cancelar(actionEvent);
	}

	public void registrarPaciente(ActionEvent actionEvent) {
		//Registrar un paciente sin apoderado
		if (apoderado.getTelefonoTrabajo()==null) {
			persona.setPersonaDistrito(distrito);
			persona.setPersonaUsuario(usuario);
			paciente.setPacientePersona(persona);
			if (pacienteService.registrarPaciente(paciente)) {
				StaticHelp.correctMessage(
						"Se ha registrado con éxito el paciente", "");
				RequestContext.getCurrentInstance().update("frmNuevoo:growl");
			}
		} else { //Registrar un paciente con apoderado
			persona.setPersonaDistrito(distrito);			
			paciente.setPacientePersona(persona);
			
			personaApoderado.setPersonaDistrito(distrito);
			personaApoderado.setPersonaUsuario(usuario);
			apoderado.setApoderadoPersona(personaApoderado);
			paciente.setPacienteApoderado(apoderado);
			if (pacienteService.registrarPacienteApoderado(paciente, apoderado)) {
				StaticHelp.correctMessage(
						"Se ha registrado con éxito el paciente y su apoderado", "");
				RequestContext.getCurrentInstance().update("frmNuevoo:growl");
			}
		}

		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		personaApoderado = new Persona();
		apoderado = new Apoderado();

	}

	public void prepararAccion(int id) {
		paciente = pacienteService.buscarPorId(id);
		persona = paciente.getPacientePersona();
		usuario = paciente.getPacientePersona().getPersonaUsuario();
		distrito = paciente.getPacientePersona().getPersonaDistrito();
		fichaOdontologica = paciente.getPacienteFichaOdontologica();

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
		personaApoderado = new Persona();
		apoderado = new Apoderado();
	}
	
	/*public void actualizarPacienteMovil(){
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente.setPacientePersona(persona);
		if (pacienteService.actualizarPaciente(paciente)) {
			StaticHelp.correctMessage(
					"Se ha actualizado con éxito el paciente", "");
			
		}
		paciente = new Paciente();
		persona = new Persona();
		
	}*/

	public void eliminarPaciente(ActionEvent actionEvent) {
		paciente.setPacientePersona(persona);
		if (pacienteService.eliminarPaciente(paciente)) {
			StaticHelp.correctMessage("Se ha eliminado con éxito el paciente",
					"");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		personaApoderado = new Persona();
		apoderado = new Apoderado();
	}
	
	public void registrarDatosFicha(ActionEvent actionEvent){		
		
		System.out.println("GRGRGRGRG");
		fichaOdontologica.setFichaOdontologicaPaciente(paciente);
		if(fichaService.registrarFichaOdontologica(fichaOdontologica)){
			StaticHelp.correctMessage("Se ha insertado con éxito los detalles del paciente",
					"");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
			
		}
		cancelar(actionEvent);		
		
	}

	public void cancelar(ActionEvent actionEvent) {
		paciente = new Paciente();
		persona = new Persona();
		usuario = new Usuario();
		distrito = new Distrito();
		personaApoderado = new Persona();
		apoderado = new Apoderado();
		fichaOdontologica = new FichaOdontologica();
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

	public Persona getPersonaApoderado() {
		return personaApoderado;
	}

	public void setPersonaApoderado(Persona personaApoderado) {
		this.personaApoderado = personaApoderado;
	}

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public void setDistritos(List<Distrito> distritos) {
		this.distritos = distritos;
	}
	

	public FichaOdontologica getFichaOdontologica() {
		return fichaOdontologica;
	}

	public void setFichaOdontologica(FichaOdontologica fichaOdontologica) {
		this.fichaOdontologica = fichaOdontologica;
	}
	
	
	
	//Métodos para atributos dinámicos

	public Date getFechaHoy() {
		fechaHoy = new Date();
		return fechaHoy;
	}

	public void setFechaHoy(Date fechaHoy) {
		this.fechaHoy = fechaHoy;
	}



}
