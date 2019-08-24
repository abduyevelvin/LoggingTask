package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.entity.EventEntity;
import com.example.entity.LogDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Java class has two methods
 * One for reading file 
 * One for creating events list from the log file
 * Component annotation is used for injecting class by using autowired
 * 
 * @author Abduyev Elvin
 * 
 */
@Component
public class ReadFile {
	
	private static final Logger logger = LoggerFactory.getLogger(ReadFile.class);
	
	/**
	 * The method for reading log file
	 * ObjectMapper used for mapping logs to the java object creating an event 
	 * 
	 * @param path A string containing file path
	 * @return event map
	 */
	public Map<String, List<LogDto>> readFile(String path) {		

		Map<String, List<LogDto>> logMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();

		File file = new File(getClass().getClassLoader().getResource(path).getFile());

		try {
			FileInputStream inputStream = new FileInputStream(file);
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(inputStream, "UTF-8");

			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				LogDto log = mapper.readValue(line, LogDto.class);

				List<LogDto> logs = logMap.get(log.getId());

				if (logs == null) {
					logs = new ArrayList<>();
				}

				logs.add(log);

				logMap.put(log.getId(), logs);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		return logMap;
		
	}
	
	/**
	 * The method converting log object to the event object
	 * Send it to the service and save in the DB
	 * 
	 * @return event object list
	 */
	public List<EventEntity> sendEvent() {

		String path = "logfile.txt";
		
		Map<String, List<LogDto>> logMap = readFile(path);
		
		List<EventEntity> events = new ArrayList<>();
		
		for (String key : logMap.keySet()) {
			EventEntity event = new EventEntity();
			List<LogDto> logs = logMap.get(key);

			LogDto log1 = logs.get(0);
			LogDto log2 = logs.get(1);

			if (log1.getId() != null) {
				event.setId(log1.getId());
			}
			if (log1.getType() != null) {
				event.setType(log1.getType());
			} else {
				event.setType(null);
			}
			if (log1.getHost() != null) {
				event.setHost(log1.getHost());
			} else {
				event.setHost(null);
			}

			Long duration = Math.abs(log1.getTimestamp() - log2.getTimestamp());
			event.setDuration(duration);

			if (duration > 4) {
				event.setAlert(true);
				logger.warn("Alert!!! " + event.getId() + " --- more than 4ms.");
			} else {
				event.setAlert(false);
			}
			
			events.add(event);
			
			logger.info(event.toString());			
		}
		
		return events;
	}
}
