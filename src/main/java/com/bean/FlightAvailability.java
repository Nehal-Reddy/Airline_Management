package com.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "FLIGHTAVAILABILITY")
@XmlRootElement
public class FlightAvailability {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int id;
	@Column(name = "AVAILABLE_SEATS")
	private int availableseats;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "PRICE")
	private int price;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "FLIGHT_ID")
	private Flight flight;

	public FlightAvailability() {
		// TODO Auto-generated constructor stub
	}

	public FlightAvailability(int availableseats, Date date, int price, Flight flight) {
		super();
		this.availableseats = availableseats;
		this.date = date;
		this.price = price;
		this.flight = flight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAvailableseats() {
		return availableseats;
	}

	public void setAvailableseats(int availableseats) {
		this.availableseats = availableseats;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	@Override
	public String toString() {
		return "FlightAvailability [id=" + id + ", availableseats=" + availableseats + ", date=" + date + ", price="
				+ price + ", flight=" + flight + "]";
	}

}
