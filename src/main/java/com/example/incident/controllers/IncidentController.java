package com.example.incident.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.incident.entities.Incident;
import com.example.incident.entities.dtos.IncidentDto;
import com.example.incident.services.IncidentService;

@Controller
@RequestMapping("/incident")
public class IncidentController {
	
	@Autowired
	private IncidentService incidentService;
	
	
	/**
	 * Show home page
	 */
	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	/**
	 * Get all incidents
	 */
	@GetMapping("/getAll")
	public String getAllIncidents(Model model){
		List<Incident> incidents = incidentService.getAllIncidents();
		
		if (incidents.size() == 0) {
			model.addAttribute("isListEmpty", true);
		}
		else {
			model.addAttribute("isListEmpty", false);
			model.addAttribute("incidents", incidents);
		}
		
		return "showAll";
	}
	
	
	/**
	 * Show create new incident page
	 */
	@Secured("ROLE_ADMIN")
	@GetMapping("/createIncident")
	public String showCreateIncidentPage(Model model) {
		model.addAttribute("incidentDto", new IncidentDto());
		return "createIncident";
	}
	
	
	/**
	 * Process create user
	 */
	@PostMapping("/processCreateIncident")
	public String processCreateIncident(Model model, @Valid @ModelAttribute IncidentDto incidentDto, BindingResult br) {
		
		if (br.hasErrors()) {
			return "createIncident";
		}
		
		Incident incident = incidentService.insertIncident(new Incident(incidentDto.getIncidentName()));
		List<Incident> incidents = incidentService.getAllIncidents();
		
		model.addAttribute("incidents", incidents);
		model.addAttribute("isListEmpty", false);
		return "showAll";
	}
	
	
	/**
	 * show search by name page
	 */
	@GetMapping("/searchByIncidentName")
	public String showSearchByIncidentNamePage() {
		return "searchByIncidentName";
	}
	
	/**
	 * Process Search By name
	 */
	@PostMapping("/processSearchIncidentName")
	public String processSearchIncidentName(@RequestParam("incidentName") String incidentName, Model model) {
		List<Incident> incidents =incidentService.getIncidentsByIncidentName(incidentName);
		
		if (incidents.size() == 0) {
			model.addAttribute("isListEmpty", true);
			return "showAll";
		}
		
		model.addAttribute("incidents", incidents);
		model.addAttribute("isListEmpty", false);
		return "showAll";
	}
}

