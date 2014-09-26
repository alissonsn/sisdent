package com.odontologia.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Cita;
import com.odontologia.model.EstadoCita;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.service.CitaService;
import com.odontologia.util.citaData;
import com.odontologia.util.horarioAdapter;

@Controller
public class horarioBean {

	@Autowired
	CitaService citaservice;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private List<Cita> citas;
	
	private citaData dataSeleccionado;
	
	private Cita citaSeleccionada;
	
	public horarioBean() {
		citas = new ArrayList<>();
	}

	@SuppressWarnings("deprecation")
	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		for (Cita c : getCitas()) {
			citaData data = new citaData(c.getCitaOdontologo(), c.getCitaPaciente(), c.getCitaEstadoCita(), c.getIdCita());
			data.setTitulo(c.getTitulo());
			data.setHoraInicio(c.getHoraInicio().getHours());
			data.setMinInicio(c.getHoraInicio().getMinutes());
			data.setHoraFin(c.getHoraFin().getHours());
			data.setMinFin(c.getHoraFin().getMinutes());
			data.setInicio(horarioAdapter.fromSqlToDate(c.getHoraInicio()));
			data.setFin(horarioAdapter.fromSqlToDate(c.getHoraFin()));			
			ScheduleEvent m = new DefaultScheduleEvent(c.getTitulo(),
			horarioAdapter.fromSqlToDate(c.getHoraInicio()),
			horarioAdapter.fromSqlToDate(c.getHoraFin()), data);					
			
			eventModel.addEvent(m);
				
		}
	}

	public List<Cita> getCitas() {
		citas = citaservice.getCitas();
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public ScheduleModel getEventModel() {
		return eventModel;
	}

	public void setEventModel(ScheduleModel eventModel) {
		this.eventModel = eventModel;
	}

	public ScheduleEvent getEvent() {
		return event;
	}

	public void setEvent(ScheduleEvent event) {
		this.event = event;
	}

	public void addEvent(ActionEvent actionEvent) {
		if (event.getId() == null)
			eventModel.addEvent(event);
		else			
			actualizaEventoCita();
			eventModel.updateEvent(event);
			citaservice.modificarCita(citaSeleccionada);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();		
		dataSeleccionado = (citaData)event.getData();
		Cita c = citaservice.citaFromId(dataSeleccionado.getIdCita());		
		this.setCitaSeleccionada(c);
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

	public Cita getCitaSeleccionada() {
		return citaSeleccionada;
	}

	public void setCitaSeleccionada(Cita citaSeleccionada) {
		this.citaSeleccionada = citaSeleccionada;
	}

	public citaData getData() {
		return dataSeleccionado;
	}
	
	@SuppressWarnings("deprecation")
	public void actualizaEventoCita(){
		citaSeleccionada.getHoraInicio().setHours(dataSeleccionado.getHoraInicio());
		citaSeleccionada.getHoraInicio().setMinutes(dataSeleccionado.getMinInicio());
		citaSeleccionada.getHoraFin().setHours(dataSeleccionado.getHoraFin());
		citaSeleccionada.getHoraFin().setMinutes(dataSeleccionado.getMinFin());
		citaSeleccionada.getHoraInicio().setDate(dataSeleccionado.getInicio().getDate());
		citaSeleccionada.getHoraInicio().setMonth(dataSeleccionado.getInicio().getMonth());
		citaSeleccionada.getHoraFin().setDate(dataSeleccionado.getFin().getDate());
		citaSeleccionada.getHoraFin().setMonth(dataSeleccionado.getFin().getMonth());
		citaSeleccionada.setTitulo(dataSeleccionado.getTitulo());
		event.getStartDate().setDate(citaSeleccionada.getHoraInicio().getDate());		
		event.getStartDate().setHours(citaSeleccionada.getHoraInicio().getHours());
		event.getStartDate().setMinutes(citaSeleccionada.getHoraInicio().getMinutes());
		event.getEndDate().setDate(citaSeleccionada.getHoraFin().getDay());
		event.getEndDate().setHours(citaSeleccionada.getHoraFin().getHours());
		event.getEndDate().setMinutes(citaSeleccionada.getHoraFin().getMinutes());		
	}

	public void setData(citaData data) {
		this.dataSeleccionado = data;
	}

}
