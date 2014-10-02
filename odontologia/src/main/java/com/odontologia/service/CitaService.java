package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Cita;
import com.odontologia.model.EstadoCita;
import com.odontologia.model.Paciente;

public interface CitaService {

	public List<Cita> getCitas();
	
	public Cita citaFromId(Integer id);
	
	public boolean insertarCita(Cita cita);
	
	public boolean modificarCita(Cita cita);
	
	public boolean eliminarCita(Cita cita);
	
	public List<Cita> getCitasPorPacientePorEstado(Integer idPaciente, Integer idEstadoCita);
	
	public List<Cita> getCitasDePacienteDeOdontologo(Integer idPaciente,Integer idOdontologo); 

	public EstadoCita estadoCitaFromId(Integer idEstadoCita);
}
