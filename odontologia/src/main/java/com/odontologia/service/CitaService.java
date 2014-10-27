package com.odontologia.service;

import java.util.List;
import com.odontologia.model.Cita;
import com.odontologia.model.EstadoCita;

public interface CitaService {

	public List<Cita> getCitas();
	
	public Cita citaFromId(Integer id);
	
	public boolean insertarCita(Cita cita);
	
	public boolean modificarCita(Cita cita);
	
	public boolean eliminarCita(Cita cita);
	
	public List<Cita> getCitasPorPacientePorEstado(Integer idPaciente, Integer idEstadoCita);
	
	public List<Cita> getCitasPorOdontologoPorEstado(Integer idOdontologo, Integer idEstadoCita);
	
	public List<Cita> getCitasListaOdontologoSinRepetir(Integer idPaciente,String nombreEstadoCita); 
	
	public List<Cita> getCitasListaPacienteSinRepetir(Integer idOdontologo,String nombreEstadoCita); 

	public EstadoCita estadoCitaFromId(Integer idEstadoCita);
	
	public List<Cita> getCitasPorPaciente(Integer idPaciente);
	
	public List<Cita> getCitasPorOdontologo (Integer idOdontologo);
	
	public EstadoCita estadoCitaFromNombre(String nombre);
	
	public List<Cita> getCitasList();
	
	public List<String> notificarCitasPacientes(String[] ids);
	
}
