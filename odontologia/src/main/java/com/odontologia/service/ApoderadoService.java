package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Apoderado;
import com.odontologia.model.Paciente;


public interface ApoderadoService {
	
	public boolean registrarApoderado(Apoderado apoderado);
	public boolean actualizarApoderado(Apoderado apoderado);
	public boolean eliminarApoderado(Apoderado apoderado);
	public List<Apoderado> getApoderados();
	public List<Paciente> getPacienteporApoderado(Apoderado apoderado);
	public Apoderado buscarPorId(int id);

}
