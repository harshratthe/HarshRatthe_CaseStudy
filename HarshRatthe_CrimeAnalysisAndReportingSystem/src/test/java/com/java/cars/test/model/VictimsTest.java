package com.java.cars.test.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.java.cars.model.Gender;
import com.java.cars.model.Victims;

public class VictimsTest {


	 @Test
	    public void testVictimsConstructor() {
	        Date dob = new Date();
	        Victims victim = new Victims(1, "Rohit", "Sharma", dob, Gender.MALE, "Dadar, Mumbai");

	        assertEquals(1, victim.getVictimId());
	        assertEquals("Rohit", victim.getFirstName());
	        assertEquals("Sharma", victim.getLastName());
	        assertEquals(dob, victim.getDateOfBirth());
	        assertEquals(Gender.MALE, victim.getGender());
	        assertEquals("Dadar, Mumbai", victim.getAddress());
	    }

	    @Test
	    public void testVictimsSettersAndGetters() {
	        Date dob = new Date();
	        Victims victim = new Victims();

	        victim.setVictimId(2);
	        victim.setFirstName("Anjali");
	        victim.setLastName("Verma");
	        victim.setDateOfBirth(dob);
	        victim.setGender(Gender.FEMALE);
	        victim.setAddress("MG Road, Bengaluru");

	        assertEquals(2, victim.getVictimId());
	        assertEquals("Anjali", victim.getFirstName());
	        assertEquals("Verma", victim.getLastName());
	        assertEquals(dob, victim.getDateOfBirth());
	        assertEquals(Gender.FEMALE, victim.getGender());
	        assertEquals("MG Road, Bengaluru", victim.getAddress());
	    }

	    @Test
	    public void testVictimsToString() {
	        Date dob = new Date();
	        Victims victim = new Victims(3, "Nikhil", "Patel", dob, Gender.MALE, "Shivaji Nagar, Pune");

	        String expected = "Victims(victimId=3, firstName=Nikhil, lastName=Patel, dateOfBirth=" + dob.toString()
	                + ", gender=MALE, address=Shivaji Nagar, Pune)";
	        assertEquals(expected, victim.toString());
	    }

}
