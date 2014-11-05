package com.odontologia.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.odontologia.model.Cita;
import com.odontologia.model.Odontologo;
import com.odontologia.model.Paciente;
import com.odontologia.model.Persona;
import com.odontologia.service.CitaService;
import com.odontologia.service.OdontologoService;
import com.odontologia.service.PacienteService;
import com.odontologia.util.StaticHelp;
import com.odontologia.util.citaData;
import com.odontologia.util.horarioAdapter;

@Controller
public class horarioBean {

	@Autowired
	CitaService citaService;

	@Autowired
	OdontologoService odontologoService;

	@Autowired
	PacienteService pacienteservice;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private List<Cita> citas;
	private List<Cita> citasDeOdontologo;

	private citaData dataSeleccionado;
	private Cita citaSeleccionada;
	private citaData dataInsert;

	private List<Paciente> pacientes;
	private List<Odontologo> odontologos;

	private Paciente pacienteSeleccionado;
	private Odontologo odontologoSeleccionado;
	
	private Cita cita;

	public horarioBean() {
		citas = new ArrayList<>();
		pacienteSeleccionado = new Paciente();
		dataSeleccionado = new citaData();
		dataInsert = new citaData();
		odontologoSeleccionado = new Odontologo();
		cita = new Cita();
		citasDeOdontologo = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		recargaHorario();
		
		
	}

	public List<Cita> getCitas() {
		citas = citaService.getCitas();
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

	public void modificarEvento(ActionEvent actionEvent) {
		if (event.getId() != null) {
			actualizaEventoCita();
			eventModel.updateEvent(event);
			citaService.modificarCita(citaSeleccionada);
			citaService.enviarEmailModifCitaPaciente(citaSeleccionada);
			citaService.enviarEmailModifCitaOdontologo(citaSeleccionada);
		}
		event = new DefaultScheduleEvent();
		pacienteSeleccionado = new Paciente();
		odontologoSeleccionado = new Odontologo();
	}

	public citaData getDataInsert() {
		return dataInsert;
	}

	public void setDataInsert(citaData dataInsert) {
		this.dataInsert = dataInsert;
	}

	@SuppressWarnings("deprecation")
	public void insertarEvento() {
		Cita cita = new Cita();
		String titulo = event.getTitle();
		cita.setCitaOdontologo(odontologoSeleccionado);
		cita.setCitaPaciente(pacienteSeleccionado);
		cita.setCitaEstadoCita(citaService.estadoCitaFromNombre("EN ESPERA"));

		Timestamp inicio = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(),
				dataInsert.getHoraInicio(), dataInsert.getMinInicio(), 0, 0);
		
		Timestamp fin = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(), dataInsert
				.getHoraFin(), dataInsert.getMinFin(), 0, 0);
		
		cita.setHoraInicio(inicio);
		cita.setHoraFin(fin);
		cita.setTitulo(titulo);

		event = new DefaultScheduleEvent(titulo, inicio, fin, dataInsert);
		eventModel.addEvent(event);
		citaService.insertarCita(cita);
		recargaHorario();
		cita = new Cita();
		event = new DefaultScheduleEvent();
		dataInsert = new citaData();
	}
	
	@SuppressWarnings("deprecation")
	public void insertarCitaMóvil(){		
		Persona persona = new Persona();
		Paciente paciente = new Paciente();
		cita.setCitaOdontologo(odontologoSeleccionado);
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		paciente = pacienteservice.buscarPorPersona(persona);
		cita.setCitaPaciente(paciente);
		cita.setCitaEstadoCita(citaService.estadoCitaFromNombre("EN ESPERA"));
		
		Timestamp inicio = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(),
				dataInsert.getHoraInicio(), dataInsert.getMinInicio(), 0, 0);
		
		Timestamp fin = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(), dataInsert
				.getHoraFin(), dataInsert.getMinFin(), 0, 0);
		
		cita.setHoraInicio(inicio);
		cita.setHoraFin(fin);
		
		citaService.insertarCita(cita);
		cita = new Cita();
		dataInsert = new citaData();
	}

	public void cancelar() {
		event = new DefaultScheduleEvent();
		pacienteSeleccionado = new Paciente();
		odontologoSeleccionado = new Odontologo();
	}

	public void eliminar() {
		citaService.eliminarCita(citaSeleccionada);
		event = new DefaultScheduleEvent();
		pacienteSeleccionado = new Paciente();
		odontologoSeleccionado = new Odontologo();
		recargaHorario();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
		dataSeleccionado = (citaData) event.getData();
		citaSeleccionada = citaService.citaFromId(dataSeleccionado.getIdCita());
		pacienteSeleccionado = citaSeleccionada.getCitaPaciente();
		odontologoSeleccionado = citaSeleccionada.getCitaOdontologo();
	}

	public Cita getCitaSeleccionada() {
		return citaSeleccionada;
	}

	public void setCitaSeleccionada(Cita citaSeleccionada) {
		this.citaSeleccionada = citaSeleccionada;
	}

	@SuppressWarnings("deprecation")
	public void actualizaEventoCita() {
		citaSeleccionada.getHoraInicio().setHours(dataSeleccionado.getHoraInicio());
		citaSeleccionada.getHoraInicio().setMinutes(dataSeleccionado.getMinInicio());
		citaSeleccionada.getHoraFin().setHours(dataSeleccionado.getHoraFin());
		citaSeleccionada.getHoraFin().setMinutes(dataSeleccionado.getMinFin());
		citaSeleccionada.getHoraInicio().setDate(dataSeleccionado.getInicio().getDate());
		citaSeleccionada.getHoraInicio().setMonth(dataSeleccionado.getInicio().getMonth());
		citaSeleccionada.getHoraFin().setDate(dataSeleccionado.getInicio().getDate());
		citaSeleccionada.getHoraFin().setMonth(dataSeleccionado.getInicio().getMonth());
		citaSeleccionada.setTitulo(event.getTitle());
		event.getStartDate().setDate(citaSeleccionada.getHoraInicio().getDate());
		event.getStartDate().setHours(citaSeleccionada.getHoraInicio().getHours());
		event.getStartDate().setMonth(citaSeleccionada.getHoraInicio().getMonth());
		event.getStartDate().setMinutes(citaSeleccionada.getHoraInicio().getMinutes());
		event.getEndDate().setDate(citaSeleccionada.getHoraInicio().getDate());
		event.getEndDate().setMonth(citaSeleccionada.getHoraInicio().getMonth());
		event.getEndDate().setHours(citaSeleccionada.getHoraFin().getHours());		
		event.getEndDate().setMinutes(citaSeleccionada.getHoraFin().getMinutes());
		citaSeleccionada.setCitaPaciente(pacienteSeleccionado);
		citaSeleccionada.setCitaOdontologo(odontologoSeleccionado);
	}

	public citaData getDataSeleccionado() {
		return dataSeleccionado;
	}

	public void setDataSeleccionado(citaData dataSeleccionado) {
		this.dataSeleccionado = dataSeleccionado;
	}

	public List<Paciente> getPacientes() {
		pacientes = pacienteservice.getPacientes();
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}

	public List<Odontologo> getOdontologos() {
		odontologos = odontologoService.getOdontologos();
		return odontologos;
	}

	public void setOdontologos(List<Odontologo> odontologos) {
		this.odontologos = odontologos;
	}

	public Paciente getPacienteSeleccionado() {
		return pacienteSeleccionado;
	}

	public void setPacienteSeleccionado(Paciente pacienteSeleccionado) {
		this.pacienteSeleccionado = pacienteSeleccionado;
	}

	public Odontologo getOdontologoSeleccionado() {
		return odontologoSeleccionado;
	}

	public void setOdontologoSeleccionado(Odontologo odontologoSeleccionado) {
		this.odontologoSeleccionado = odontologoSeleccionado;
	}

	//HORARIO PARA EL RECEPCIONISTA
	@SuppressWarnings("deprecation")
	public void recargaHorario() {
		eventModel = new DefaultScheduleModel();
		for (Cita c : getCitas()) {
			citaData data = new citaData(c.getCitaOdontologo(),
					c.getCitaPaciente(), c.getCitaEstadoCita(), c.getIdCita());
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
	
	//HORARIO PARA EL ODONTÓLOGO
	@SuppressWarnings("deprecation")
	public void recargaHorarioOdontologo(){
		eventModel = new DefaultScheduleModel();
		for (Cita c : getCitasDeOdontologo()) {
			citaData data = new citaData(c.getCitaOdontologo(),
					c.getCitaPaciente(), c.getCitaEstadoCita(), c.getIdCita());
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

	public Cita getCita() {
		return cita;
	}

	public void setCita(Cita cita) {
		this.cita = cita;
	}

	public List<Cita> getCitasDeOdontologo() {
		Persona persona = new Persona();
		Odontologo odontologo = new Odontologo();
		HttpSession session = StaticHelp.getSession();
		persona = (Persona) session.getAttribute("personaSesion");
		odontologo = odontologoService.buscarPorPersona(persona);
		citasDeOdontologo = citaService.getCitasPorOdontologo(odontologo
				.getIdOdontologo());
		return citasDeOdontologo;		
	}

	public void setCitasDeOdontologo(List<Cita> citasDeOdontologo) {
		this.citasDeOdontologo = citasDeOdontologo;
	}
}
