package com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.bean.Flight;
import com.bean.FlightAvailability;

public class HibernateStandAloneDemo {

	public String getFlightById(int flightid) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		String hql = "FROM Flight f WHERE f.flightid = :flightid";
		Query query = session.createQuery(hql);
		query.setParameter("flightid", flightid);
		Flight flight = (Flight) query.uniqueResult();

		String result = "Flight [ Flight id: " + flight.getFlightid() + " Total Seats: " + flight.getTotalseats()
				+ " Description: " + flight.getDescription() + " ]";

		session.getTransaction().commit();
		session.close();
		return result;

	}

	public List<String> sortFlights(Date fromdate, Date todate, String destination, String origin, int maxprice) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		String hql = "FROM FlightAvailability fa WHERE flight.origin=:origin AND "
				+ "flight.destination=:destination AND fa.price <=:maxprice"
				+ " AND fa.date between :fromdate AND :todate";
		Query query = session.createQuery(hql);
		query.setParameter("origin", origin);
		query.setParameter("destination", destination);
		query.setParameter("maxprice", maxprice);
		query.setParameter("fromdate", fromdate);
		query.setParameter("todate", todate);
		List<FlightAvailability> flightavailability = query.list();
		List<String> resultset = new ArrayList<String>();
		for (FlightAvailability f : flightavailability) {
			String result = "[ Flight Id: " + f.getFlight().getFlightid() + " Available Seats: " + f.getAvailableseats()
					+ " Price: " + f.getPrice() + " Date: " + f.getDate() + " ]";
			resultset.add(result);
		}
		session.getTransaction().commit();
		session.close();

		return resultset;
	}

	public void updateAvailability(int flightid, int seats, Date date) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		FlightAvailability fa = new FlightAvailability();
		session.beginTransaction();
		String hql = "SELECT fa.availableseats FROM FlightAvailability fa WHERE flight.flightid = :flightid"
				+ "AND fa.date=:date";
		Query query = session.createQuery(hql);
		query.setParameter("flightid", flightid);
		query.setParameter("date", date);
		int availableseats = Integer.parseInt(query.uniqueResult().toString());
		fa.setAvailableseats(availableseats - seats);
		session.getTransaction().commit();
		session.close();

	}
}
