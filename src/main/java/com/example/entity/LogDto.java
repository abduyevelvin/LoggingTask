package com.example.entity;

/**
 * Java class convertible for logging text file data
 * Generated setter and getter methods of class fields
 * 
 * @author Abduyev Elvin
 * 
 */
public class LogDto {

	/** Represents the id of event log.
	*/
	private String id;
	/** Represents the state of event log.
	*/
	private String state;
	/** Represents the timestamp of event log.
	*/
	private Long timestamp;
	/** Represents the type of event log.
	*/
	private String type;
	/** Represents the host of event log.
	*/
	private String host;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
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
	
	@Override
	public String toString() {
		return "Log [id=" + id + ", state=" + state + ", timestamp=" + timestamp + ", type=" + type + ", host=" + host
				+ "]";
	}
}
