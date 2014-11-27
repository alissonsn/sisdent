package com.odontologia.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Distrito;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.model.Usuario;
import com.odontologia.service.ApoderadoService;

@Controller
public class ApoderadoBean {

	@Autowired
	ApoderadoService apoderadoService;

	private Apoderado apoderado;
	private Persona persona;
	private Distrito distrito;
	private Usuario usuario;

	private List<Apoderado> apoderados;
	
	private List<Paciente> pacienteApoderado;
	
	public ApoderadoBean() {
		apoderado = new Apoderado();
		persona = new Persona();
		distrito = new Distrito();
		usuario = new Usuario();
		apoderados = new ArrayList<>();
		pacienteApoderado = new ArrayList<>();

	} 

	public Apoderado getApoderado() {
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}

	public Persona getPersona() {
		return persona;
	}	

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Apoderado> getApoderados() {
		apoderados = apoderadoService.getApoderados();
		return apoderados;
	}

	public void setApoderados(List<Apoderado> apoderados) {
		this.apoderados = apoderados;
	}
	
	public void cargarPaciente(int id){		
		Apoderado apoderado= apoderadoService.buscarPorId(id);
		setPacienteApoderado(apoderadoService.getPacienteporApoderado(apoderado));
	}

	public List<Paciente> getPacienteporApoderado(int id) {
		Apoderado apoderado= apoderadoService.buscarPorId(id);
		return setPacienteApoderado(apoderadoService.getPacienteporApoderado(apoderado));
	} 
	
	
	public void setPacienteporApoderado(List<Paciente> pacienteApoderado) {
		this.setPacienteApoderado(pacienteApoderado);
	}

	public List<Paciente> getPacienteApoderado() {		
		return pacienteApoderado;
	}

	public List<Paciente> setPacienteApoderado(List<Paciente> pacienteApoderado) {
		this.pacienteApoderado = pacienteApoderado;
		return pacienteApoderado;
	}

}
