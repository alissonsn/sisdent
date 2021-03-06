package com.odontologia.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
import com.odontologia.model.EstadoCita;
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
	private ScheduleModel eventModelO;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private List<Cita> citas;
	private List<Cita> citasDeOdontologo;

	private citaData dataSeleccionado;
	private Cita citaSeleccionada;
	private citaData dataInsert;
	private EstadoCita estadoCita;

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
		estadoCita = new EstadoCita();
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
			citaService.enviarEmailModifCitaOdontologo(odontologoSeleccionado, citaSeleccionada);
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
		if(citaService.validarHorarioCita(cita)==false){//Validamos si el horario de ese odont�logo est� ocupado
			event = new DefaultScheduleEvent(titulo, inicio, fin, dataInsert);
			eventModel.addEvent(event);
			citaService.insertarCita(cita);
			citaService.enviarEmailRegistCitaPaciente(cita.getIdCita());
			recargaHorario();
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ESE HORARIO YA EST� OCUPADO, PRUEBE OTRO POR FAVOR",""));
		}		
		cita = new Cita();
		event = new DefaultScheduleEvent();
		dataInsert = new citaData();
	}
	
	@SuppressWarnings("deprecation")
	public void insertarCitaMovil(){		
		Persona persona = new Persona();//Inicializamos persona
		Paciente paciente = new Paciente();//Inicializamos paciente
		cita.setCitaOdontologo(odontologoSeleccionado); //Agregamos al odont�logo a la cita
		HttpSession session = StaticHelp.getSession(); //Invocamos a la sesion
		persona = (Persona) session.getAttribute("personaSesion"); //Agarramos persona del atributo Sesion
		paciente = pacienteservice.buscarPorPersona(persona); //Buscamos al paciente por persona
		cita.setCitaPaciente(paciente); //Agregamos al paciente a la cita
		cita.setCitaEstadoCita(citaService.estadoCitaFromNombre("EN ESPERA"));//Agregamos estado de cita a la cita
		
		dataInsert.setMinInicio(0); //Damos valor 0 a los minutos
		
		Timestamp inicio = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(),
				dataInsert.getHoraInicio(), dataInsert.getMinInicio(), 0, 0);//Configuramos la hora inicio
		
		Timestamp fin = new Timestamp(2014 - 1900, dataInsert.getInicio()
				.getMonth(), dataInsert.getInicio().getDate(), dataInsert
				.getHoraInicio()+1, dataInsert.getMinInicio(), 0, 0);//Configuramos la hora fin
		
		cita.setHoraInicio(inicio);//Agregamos la hora inicio a la cita
		cita.setHoraFin(fin);//Agregamos la hora fin a la cita
		if(citaService.validarHorarioCita(cita)==false){//Validamos si el horario de ese odont�logo est� ocupado
			citaService.insertarCita(cita);//Registramos la cita
			citaService.enviarEmailRegistCitaPaciente(cita.getIdCita());//Enviamos el email al paciente
			recargaHorario();//Refrescamos el horario
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "ESE HORARIO YA EST� OCUPADO, PRUEBE OTRO POR FAVOR",""));
		}
		cita = new Cita();//Inicializamos cita
		dataInsert = new citaData();//Inicializamos dataInsert
	}

	public void cancelar() {
		event = new DefaultScheduleEvent();
		pacienteSeleccionado = new Paciente();
		odontologoSeleccionado = new Odontologo();
		estadoCita = new EstadoCita();
	}

	public void eliminar() {
		citaService.eliminarCita(citaSeleccionada);
		event = new DefaultScheduleEvent();
		pacienteSeleccionado = new Paciente();
		odontologoSeleccionado = new Odontologo();
		estadoCita = new EstadoCita();
		recargaHorario();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
		dataSeleccionado = (citaData) event.getData();
		citaSeleccionada = citaService.citaFromId(dataSeleccionado.getIdCita());
		pacienteSeleccionado = citaSeleccionada.getCitaPaciente();
		odontologoSeleccionado = citaSeleccionada.getCitaOdontologo();
		estadoCita = citaSeleccionada.getCitaEstadoCita();
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
	
	//HORARIO PARA EL ODONT�LOGO
	@SuppressWarnings("deprecation")
	public void recargaHorarioOdontologo(){
		eventModelO = new DefaultScheduleModel();
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

			eventModelO.addEvent(m);

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

	public EstadoCita getEstadoCita() {
		return estadoCita;
	}

	public void setEstadoCita(EstadoCita estadoCita) {
		this.estadoCita = estadoCita;
	}

	public ScheduleModel getEventModelO() {
		recargaHorarioOdontologo();
		return eventModelO;
	}

	public void setEventModelO(ScheduleModel eventModelO) {
		this.eventModelO = eventModelO;
	}
}
