package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Java class creating entity(table) in DB
 * Generated setter and getter methods of class fields
 * The class is convertible to LogDto class which event logs
 * 
 * @author Abduyev Elvin
 * 
 */
@Entity
/**
 * Represents created table name
 *
 */
@Table(name = "events")
public class EventEntity {

	/** Represents the id column in the table.
	*/
	@Id
	@Column(name = "eventId")
	private String id;
	
	/** Represents the duration column in the table.
	*/
	@Column(name = "eventDuration")
	private Long duration;
	
	/** Represents the type column in the table.
	*/
	@Column(name = "eventType")
	private String type;
	
	/** Represents the host column in the table.
	*/
	@Column(name = "host")
	private String host;
	
	/** Represents the alert column in the table.
	*/
	@Column(name = "alert")
	private Boolean alert;

	public EventEntity() {}
	
	public EventEntity(String id, Long duration, String type, String host, Boolean alert) {
		this.id = id;
		this.duration = duration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Boolean getAlert() {
		return alert;
	}

	public void setAlert(Boolean alert) {
		this.alert = alert;
	}

	@Override
	public String toString() {
		return "EventEntity [id=" + id + ", duration=" + duration + ", type=" + type + ", host=" + host + ", alert="
				+ alert + "]";
	}
}
