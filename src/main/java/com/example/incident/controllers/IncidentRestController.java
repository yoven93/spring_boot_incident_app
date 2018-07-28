package com.example.incident.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.incident.entities.Incident;
import com.example.incident.entities.dtos.IncidentDto;
import com.example.incident.entities.dtos.IncidentDtoWithId;
import com.example.incident.exceptions.IncidentNotFoundException;
import com.example.incident.services.IncidentService;

@RestController
@RequestMapping("/api")
public class IncidentRestController {
	
	@Autowired
	private IncidentService incidentService;
	
	@PostMapping("/")
	public ResponseEntity<?> create(@Valid @RequestBody IncidentDto incidentDto, Errors errors) {
		
		if (errors.hasErrors()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incident Name Not Provided");
		}
		
		// Create Incident object from IncidentDTO
		Incident incident = new Incident(incidentDto.getIncidentName());
		
		// Insert to database
		Incident newIncident = incidentService.insertIncident(incident);
		
		if (newIncident != null) {
			return ResponseEntity.ok(newIncident);
		}
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Error incident already exists");
	}
	
	
	@GetMapping("/{incidentId}")
	public ResponseEntity<?> get(@PathVariable("incidentId") Integer incidentId) {
		
		Incident incident = incidentService.getIncidentByIncidentId(incidentId);
		
		if (incident != null) {
			return ResponseEntity.ok(incident);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Incident Not Found");
	}
	
	
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		
		List<Incident> incidents = incidentService.getAllIncidents();
		
		if (incidents.size() > 0) {
			
			return ResponseEntity.ok(incidents);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No incidents found");
	}
	
	
	@DeleteMapping("/{incidentId}")
	public ResponseEntity<?> delete(@PathVariable("incidentId") Integer incidentId) {
		
		Incident incident;
		
		try {
			incident = incidentService.deleteIncidentByIncidentId(incidentId);
		} catch (IncidentNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		return ResponseEntity.ok(incident);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> update(@RequestBody IncidentDtoWithId incidentDtoWithId) {
		
		try {
			Incident incident = new Incident();
			incident.setIncidentId(incidentDtoWithId.getIncidentId());
			incident.setIncidentName(incidentDtoWithId.getIncidentName());
			incident.setIncidentDate(new Date());
			Incident updatedIncident = incidentService.updateIncidentName(incident);
			return ResponseEntity.ok(updatedIncident);
		} catch (IncidentNotFoundException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
}
