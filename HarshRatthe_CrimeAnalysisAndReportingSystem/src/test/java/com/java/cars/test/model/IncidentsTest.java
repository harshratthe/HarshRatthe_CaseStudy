package com.java.cars.test.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.java.cars.model.Incidents;

public class IncidentsTest {

	@Test
    public void testIncidentsConstructor() {
        Date date = new Date();
        Incidents incident = new Incidents(1, "Chain Snatching", date, "Gokulpeth, Nagpur", "Victim was attacked on a scooter", "Open", 101, 201, 301);

        assertEquals(1, incident.getIncidentId());
        assertEquals("Chain Snatching", incident.getIncidentType());
        assertEquals(date, incident.getIncidentDate());
        assertEquals("Gokulpeth, Nagpur", incident.getLocation());
        assertEquals("Victim was attacked on a scooter", incident.getDescription());
        assertEquals("Open", incident.getStatus());
        assertEquals(101, incident.getVictimId());
        assertEquals(201, incident.getSuspectId());
        assertEquals(301, incident.getOfficerId());
    }

    @Test
    public void testIncidentsSettersAndGetters() {
        Date date = new Date();
        Incidents incident = new Incidents();

        incident.setIncidentId(2);
        incident.setIncidentType("Cyber Fraud");
        incident.setIncidentDate(date);
        incident.setLocation("Vashi, Navi Mumbai");
        incident.setDescription("Online UPI scam of ₹10,000");
        incident.setStatus("Under Investigation");
        incident.setVictimId(102);
        incident.setSuspectId(202);
        incident.setOfficerId(302);

        assertEquals(2, incident.getIncidentId());
        assertEquals("Cyber Fraud", incident.getIncidentType());
        assertEquals(date, incident.getIncidentDate());
        assertEquals("Vashi, Navi Mumbai", incident.getLocation());
        assertEquals("Online UPI scam of ₹10,000", incident.getDescription());
        assertEquals("Under Investigation", incident.getStatus());
        assertEquals(102, incident.getVictimId());
        assertEquals(202, incident.getSuspectId());
        assertEquals(302, incident.getOfficerId());
    }

    @Test
    public void testIncidentsToString() {
        Date date = new Date();
        Incidents incident = new Incidents(3, "ATM Theft", date, "Kharadi, Pune", "ATM broken into at midnight", "Filed", 103, 203, 303);

        String expected = "Incidents(incidentId=3, incidentType=ATM Theft, incidentDate=" + date.toString()
                + ", location=Kharadi, Pune, description=ATM broken into at midnight, status=Filed, victimId=103, suspectId=203, officerId=303)";
        assertEquals(expected, incident.toString());
    }

}
