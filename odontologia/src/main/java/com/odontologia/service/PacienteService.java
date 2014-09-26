package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Paciente;

public interface PacienteService {
	
	
	public boolean registrarPaciente(Paciente paciente);
	public boolean actualizarPaciente(Paciente paciente);
	public boolean eliminarPaciente(Paciente paciente);
	public List<Paciente> getPacientes();
	public Paciente buscarPorId(int id);
	public boolean registrarPacienteApoderado(Paciente paciente, Apoderado apoderado);

}
