package com.example.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.EventEntity;
import com.example.repository.EventRepository;
import com.example.util.ReadFile;

/**
 * Java class implements EventService interface
 * Injected EventRepository interface for saving event to the DB
 * Injected ReadFile class for reading file and convert it to the event
 * 
 * @author Abduyev Elvin
 * 
 */
@Service
public class EventServiceImpl implements EventService {

	private static final Logger logger = LoggerFactory.getLogger(EventServiceImpl.class);

	@Autowired
	private EventRepository repository;

	@Autowired
	private ReadFile readFile;

	/**
	 * The method for saving event to the DB
	 * Getting event by using ReadFile class's readFile method
	 * If saved correctly logging info about created event by id
	 *  
	 */
	@Override
	public void createEvent() {

		List<EventEntity> events = readFile.sendEvent();

		for(EventEntity event : events) {
			repository.save(event);
			
			if (repository.findById(event.getId()) != null) {
				logger.info("Event is created: " + event.getId());
			}
		}
	}
}
