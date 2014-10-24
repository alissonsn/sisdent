package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Cita;
import com.odontologia.model.EstadoCita;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.service.CitaService;
import com.odontologia.service.EstadoCitaService;
import com.odontologia.service.OdontologoService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;

@Controller
public class CitaBean {

	@Autowired
	CitaService citaService;

	@Autowired
	PacienteService pacienteService;

	@Autowired
	EstadoCitaService estadoCitaService;

	@Autowired
	OdontologoService odontologoService;

	private Cita cita;
	private List<Cita> citas;
	private List<Cita> citasDePacienteEnEspera;
	private List<Cita> citasDePacienteOdontologo;
	private List<Cita> citasDeOdontologoPaciente;
	private List<Cita> citasPorPaciente;
	private List<Cita> citasPorOdontologo;
	private EstadoCita estadoCita;

	public CitaBean() {
		cita = new Cita();
		estadoCita = new EstadoCita();
		citas = new ArrayList<>();
		citasDePacienteEnEspera = new ArrayList<>();
		citasPorPaciente = new ArrayList<>();
		citasPorOdontologo = new ArrayList<>();
		citasDePacienteOdontologo = new ArrayList<>();
		citasDeOdontologoPaciente = new ArrayList<>();
	}

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public List<Cita> getCitas() {
		// Para no estar consultando a la BD cada rato al reordenar con "SORTBY"
		if (citas.size() == 0) {
			citas = citaService.getCitas();
		}
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public void cancelarCita(ActionEvent actionEvent) {
		// ID DE ESTADO DE CITA = 3 (CANCELADO)
		estadoCita = estadoCitaService.buscarPorId(3);
		cita.setCitaEstadoCita(estadoCita);
		if (citaService.modificarCita(cita)) {
			StaticHelp.correctMessage("Se ha cancelado la cita", "");
			RequestContext.getCurrentInstance().update("frmNuevoo:growl");
		}
		cita = new Cita();
		estadoCita = new EstadoCita();

	}

	// Ver las citas(En estado espera) del paciente
	public List<Cita> getCitasDePacienteEnEspera() {
		Persona persona = new Persona();
		Paciente paciente = new Paciente();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente = pacienteService.buscarPorPersona(persona);
		// ID DE ESTAOD DE CITA = 1 (EN ESPERA)
		citasDePacienteEnEspera = citaService.getCitasPorPacientePorEstado(
				paciente.getIdPaciente(), 1);
		return citasDePacienteEnEspera;
	}

	// Ver las citas(En estado espera) del paciente
	public List<Cita> getCitasDePacienteOdontologo() {
		Persona persona = new Persona();
		Paciente paciente = new Paciente();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente = pacienteService.buscarPorPersona(persona);
		// ID DE ESTAOD DE CITA = 1 (EN ESPERA)
		citasDePacienteOdontologo = citaService.getCitasListaOdontologoSinRepetir(paciente.getIdPaciente(), "EN ESPERA");
		return citasDePacienteOdontologo;
	}

	public void setCitasDePacienteEnEspera(List<Cita> citasDePacienteEnEspera) {
		this.citasDePacienteEnEspera = citasDePacienteEnEspera;
	}

	public EstadoCita getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(EstadoCita estadoCita) {
		this.estadoCita = estadoCita;
	}

	public void setCitasDePacienteOdontologo(List<Cita> citasDePacienteOdontologo) {
		this.citasDePacienteOdontologo = citasDePacienteOdontologo;
	}

	public List<Cita> getCitasPorPaciente() {
		Persona persona = new Persona();
		Paciente paciente = new Paciente();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente = pacienteService.buscarPorPersona(persona);
		citasPorPaciente = citaService.getCitasPorPaciente(paciente
				.getIdPaciente());

		return citasPorPaciente;
	}

	public void setCitasPorPaciente(List<Cita> citasPorPaciente) {
		this.citasPorPaciente = citasPorPaciente;
	}

	public List<Cita> getCitasPorOdontologo() {
		Persona persona = new Persona();
		Odontologo odontologo = new Odontologo();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		odontologo = odontologoService.buscarPorPersona(persona);
		citasPorOdontologo = citaService.getCitasPorOdontologo(odontologo
				.getIdOdontologo());
		return citasPorOdontologo;
	}

	public void setCitasPorOdontologo(List<Cita> citasPorOdontologo) {
		this.citasPorOdontologo = citasPorOdontologo;
	}
	
	
    //Agarrar fecha local
	public TimeZone getTimeZone() {
		TimeZone timeZone = TimeZone.getDefault();
		return timeZone;
	}

	public List<Cita> getCitasDeOdontologoPaciente() {
		Persona persona = new Persona();
		Odontologo odontologo = new Odontologo();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		odontologo = odontologoService.buscarPorPersona(persona);
		// ID DE ESTAOD DE CITA = 1 (EN ESPERA)
		citasDePacienteOdontologo = citaService.getCitasListaPacienteSinRepetir(odontologo.getIdOdontologo(), "EN ESPERA");
		return citasDeOdontologoPaciente;
	}

	public void setCitasDeOdontologoPaciente(
			List<Cita> citasDeOdontologoPaciente) {
		this.citasDeOdontologoPaciente = citasDeOdontologoPaciente;
	}

}
