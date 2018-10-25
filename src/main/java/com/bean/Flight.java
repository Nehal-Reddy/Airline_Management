package com.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="FLIGHTS")
@XmlRootElement
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FLIGHT_ID")
	private int flightid;
	@Column(name="TOTAL_SEATS")
	private int totalseats;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="ORIGIN")
	private String origin;
	@Column(name="DESTINATION")
	private String destination;
	
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}


	public Flight(int totalseats, String description, String origin, String destination) {
		super();
		this.totalseats = totalseats;
		this.description = description;
		this.origin = origin;
		this.destination = destination;
	}


	public int getFlightid() {
		return flightid;
	}


	public void setFlightid(int flightid) {
		this.flightid = flightid;
	}


	public int getTotalseats() {
		return totalseats;
	}


	public void setTotalseats(int totalseats) {
		this.totalseats = totalseats;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getDestination() {
		return destination;
	}


	public void setDestination(String destination) {
		this.destination = destination;
	}


	@Override
	public String toString() {
		return "Flight [flightid=" + flightid + ", totalseats=" + totalseats + ", description=" + description
				+ ", origin=" + origin + ", destination=" + destination + "]";
	}
	
	
	
	
	
	
	

}
