package com.example.incident.services;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.example.incident.entities.Incident;
import com.example.incident.exceptions.IncidentNotFoundException;
import com.example.incident.repository.IncidentRepository;

@Service
public class IncidentService {
	
	private final IncidentRepository incidentRepository;
	
	public IncidentService(IncidentRepository incidentRepository) {
		this.incidentRepository = incidentRepository;
	}
	
	
	/**
	 * Find all incidents
	 */
	public List<Incident> getAllIncidents(){
		return incidentRepository.findAll();
	}
	
	
	/**
	 * Find incident by incidentId
	 */
	public Incident getIncidentByIncidentId(Integer incidentId) {
		return incidentRepository.findByIncidentId(incidentId);
	}
	
	
	/**
	 * Find incidents by incident name
	 */
	public List<Incident> getIncidentsByIncidentName(String incidentName) {
		return incidentRepository.findByIncidentName(incidentName);
	}
	
	
	/**
	 * Insert new incident
	 */
	public Incident insertIncident(Incident incident) {
		return incidentRepository.save(incident);
	}
	
	
	/**
	 * Update incident's name
	 * @throws IncidentNotFoundException 
	 */
	public Incident updateIncidentName(Incident incident) throws IncidentNotFoundException {
		
		// check if incident exists before updating
		Incident checkIncident = incidentRepository.findByIncidentId(incident.getIncidentId());
		
		if (checkIncident != null) {
			return incidentRepository.save(incident);
		}
		
		throw new IncidentNotFoundException("Incident does not exists");
	}
	
	
	/**
	 * Delete incident by incident id
	 * @throws IncidentNotFoundException 
	 */
	public Incident deleteIncidentByIncidentId(Integer incidentId) throws IncidentNotFoundException {
		
		// Check if incident exists before deleting
		Incident checkIncident = incidentRepository.findByIncidentId(incidentId);
		
		if (checkIncident != null) {
			incidentRepository.delete(checkIncident);
			return checkIncident;
		}
		
		throw new IncidentNotFoundException("Incident does not exists");
	}
	
	
	/**
	 * Populate incident table
	 */
	@PostConstruct
	public void populateIncidentTable() {
		
		Incident incident1 = new Incident("Incident 1");
		Incident incident2 = new Incident("Incident 2");
		insertIncident(incident1);
		insertIncident(incident2);
	}
}
