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
import com.odontologia.service.CitaService;
import com.odontologia.util.horarioAdapter;

@Controller
public class horarioBean {

	@Autowired
	CitaService citaservice;

	private ScheduleModel eventModel;
	private ScheduleEvent event = new DefaultScheduleEvent();
	private List<Cita> citas;

	public horarioBean() {
		citas = new ArrayList<>();
	}

	@PostConstruct
	public void init() {
		eventModel = new DefaultScheduleModel();
		for (Cita c : getCitas()) {
			eventModel.addEvent(new DefaultScheduleEvent("Titulo",
					horarioAdapter.fromSqlToDate(c.getHoraInicio()),
					horarioAdapter.fromSqlToDate(c.getHoraFin())));
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
			eventModel.updateEvent(event);

		event = new DefaultScheduleEvent();
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (ScheduleEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(),
				(Date) selectEvent.getObject());
	}

}
