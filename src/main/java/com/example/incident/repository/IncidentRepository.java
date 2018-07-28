package com.example.incident.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.incident.entities.Incident;

public interface IncidentRepository extends JpaRepository<Incident, Integer> {
	
	/**
	 * Find incidents by incident date
	 */
	public List<Incident> findByIncidentDate(Date incidentDate);
	
	/**
	 * Find incident by incident name
	 */
	public List<Incident> findByIncidentName(String incidentName);
	
	/**
	 * Find incident by incident id
	 */
	public Incident findByIncidentId(Integer incidentId);
}
