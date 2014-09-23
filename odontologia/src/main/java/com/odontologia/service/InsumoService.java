package com.odontologia.service;

import java.util.List;

import com.odontologia.model.Insumo;

public interface InsumoService {

	public List<Insumo> getInsumos();

	public boolean insertarInsumo(Insumo insumo);

	public boolean modificarInsumo(Insumo insumo);

	public boolean eliminarInsumo(Insumo insumo);

}
