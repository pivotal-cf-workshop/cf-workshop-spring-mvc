package com.gopivotal.cf.workshop.web;

import java.io.IOException;
import java.util.Map;

import org.cloudfoundry.org.codehaus.jackson.JsonParseException;
import org.cloudfoundry.org.codehaus.jackson.map.JsonMappingException;
import org.cloudfoundry.org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkshopRestController {

	private static final Logger logger = LoggerFactory
			.getLogger(WorkshopRestController.class);

	
	@RequestMapping("/version")
	public String versionString()throws JsonMappingException, JsonParseException, IOException{
		
		String instanceIndex = System.getenv("CF_INSTANCE_INDEX");
		
		if(instanceIndex == null){
			logger.info("No CF_INSTANCE_INDEX, going to VCAP_APPLICATION");
			
			String vcapApplication = System.getenv("VCAP_APPLICATION");
			ObjectMapper mapper = new ObjectMapper();
			if (vcapApplication != null) {
				Map vcapMap = mapper.readValue(vcapApplication, Map.class);
				instanceIndex = Integer.toString((Integer)vcapMap.get("instance_index"));
			}
			
		}
		
		return "Version 1" + (instanceIndex != null? " - index " + instanceIndex:"");
		
	}
	
}
