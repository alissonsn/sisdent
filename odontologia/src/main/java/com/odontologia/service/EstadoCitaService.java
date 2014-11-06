package com.odontologia.service;

import java.util.List;

import com.odontologia.model.EstadoCita;

public interface EstadoCitaService {
	
	public EstadoCita buscarPorId(Integer idEstadoCita);
	
	public List<EstadoCita> getEstadoCitas();

}
