package com.gopivotal.cf.workshop.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gopivotal.cf.workshop.entity.Attendee;
import com.gopivotal.cf.workshop.entity.Session;
import com.gopivotal.cf.workshop.repository.AttendeeRepository;
import com.gopivotal.cf.workshop.repository.SessionRepository;

/**
 * Controller for the Cloud Foundry workshop - Spring MVC version.
 * 
 */
@Controller
public class CloudFoundryWorkshopController {
	
	private static final Logger logger = LoggerFactory.getLogger(CloudFoundryWorkshopController.class);

	@Autowired
	private AttendeeRepository attendeeRepository;
	
	@Autowired
	private SessionRepository sessionRepository;

	/**
	 * Gets basic environment information.  This is the application's
	 * default action.
	 * @param model The model for this action.
	 * @return The path to the view.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
			
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
		String serverTime = dateFormat.format(date);
		model.addAttribute("serverTime", serverTime);
		
		String port = System.getenv("PORT");
		model.addAttribute("port", port);

		String vcapApplication = System.getenv("VCAP_APPLICATION");
		model.addAttribute("vcapApplication", vcapApplication);
		
		logger.info("Current date and time = [{}], port = [{}].", serverTime, port);

		return "index";
	}
	
	/**
	 * Action to get a list of all attendees.
	 * @param model The model for this action.
	 * @return The path to the view.
	 */
	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public String attendees(Model model) {
		
		Iterable<Attendee> attendees = attendeeRepository.findAll();
	
		model.addAttribute("attendees", attendees);
		return "attendees";
	}
	
	/**
	 * Action to get a list of all of the sessions for the specified attendee.
	 * @param attendeeId The ID of the attendee to get the sessions for.
	 * @param model The model for this action.
	 * @return The path to the view.
	 */
	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public String sessions(@RequestParam("attendeeId") Long attendeeId, Model model) {
		
		Attendee attendee = attendeeRepository.findOne(attendeeId);
		List<Session> sessions = sessionRepository.findByAttendee(attendee);
		model.addAttribute("sessions", sessions);
		
		return "sessions";
	}
	
	/**
	 * Action to initiate shutdown of the system.  In CF, the application 
	 * <em>should</em>f restart.  In other environments, the application
	 * runtime will be shut down.
	 */
	@RequestMapping(value = "/kill", method = RequestMethod.GET)
	public void kill() {
		
		logger.warn("*** The system is shutting down. ***");
		System.exit(-1);
		
	}
	
	/**
	 * Seeds data in the database for convenience / demo purposes.
	 * @return The view to display.
	 */
	@RequestMapping(value = "/seedData", method = RequestMethod.GET)
	public String seedData() {
		
		
		Attendee attendee = new Attendee();
		attendee.setFirstName("Brian");
		attendee.setLastName("Jimerson");
		attendee.setAddress("123 Main St.");
		attendee.setCity("Akron");
		attendee.setState("OH");
		attendee.setZipCode("44321");
		attendee.setPhoneNumber("330-123-4567");
		attendee.setEmailAddress("bjimerson@gopivotal.com");
		attendeeRepository.save(attendee);

		Session session = new Session();
		session.setName("Session 1");
		session.setDate(new Date());
		session.setAttendee(attendee);
		sessionRepository.save(session);

		session = new Session();
		session.setName("Session 2");
		session.setDate(new Date());
		session.setCompleted(true);
		session.setAttendee(attendee);
		sessionRepository.save(session);

		return "index";
	}



}
