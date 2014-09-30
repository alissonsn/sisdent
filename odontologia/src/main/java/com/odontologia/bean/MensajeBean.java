package com.odontologia.bean;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Cita;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.service.CitaService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;

@Controller
public class MensajeBean {

	private Odontologo odontologo;
	private Paciente paciente;

	private List<Odontologo> odontologos;
	private List<Cita> CitasDePacienteDeOdontologo;

	@Autowired
	PacienteService pacienteService;
	
	@Autowired
	CitaService citaService;
	
	
	public MensajeBean() {
		odontologo = new Odontologo();

	}

	/*public List<Odontologo> getCitasDePacienteDeOdontologo() {
		Persona persona = new Persona();
		Paciente paciente = new Paciente();
		Odontologo odontologo = new Odontologo();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente = pacienteService.buscarPorPersona(persona);
		// ID DE ESTAOD DE CITA = 1 (EN ESPERA)
		CitasDePacienteDeOdontologo = citaService
				.getCitasDePacienteDeOdontologo(paciente.getIdPaciente(),
						odontologo.getIdOdontologo());
		return CitasDePacienteDeOdontologo;
	}
	*/

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

}
