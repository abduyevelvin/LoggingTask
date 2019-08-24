package com.example;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.entity.EventEntity;
import com.example.repository.EventRepository;

/**
 * Java test class for testing EventService
 * Logger used for logging the results
 * EventRepository injected for saving an event to the DB
 * 
 * @author Abduyev Elvin
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogingTaskApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(LogingTaskApplicationTests.class);
	
	@Autowired
    private EventRepository repos;
	
	/**
	 * The method saving an event to the DB
	 * Checking id's equality of new created event and saved event in DB
	 * 
	 */
	@Test
	public void saveEvent() {
	    repos.save(new EventEntity("123", 3L, "test", "12345", true));
	    EventEntity event = repos.findById("123").orElseGet(() 
	      -> new EventEntity("112", 4L, "prod", "12344", false));
	    
	    assertThat(event.getId()).isEqualTo("123");
	}
	
	/**
	 * The method gets event from DB
	 * Checking not null of fetched event list from DB
	 * 
	 */
	@Test
	public void getEvents() {
		List<EventEntity> events = (List<EventEntity>) repos.findAll();
		
		logger.info(events.toString());
		
		assertNotNull(events);
	}

}
