package com.java.cars.test.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.java.cars.model.Gender;
import com.java.cars.model.Suspects;

public class SuspectsTest {

	 @Test
	    public void testSuspectsConstructor() {
	        Date dob = new Date();
	        Suspects suspect = new Suspects(1, "Rakesh", "Yadav", dob, Gender.MALE, "9876543210");

	        assertEquals(1, suspect.getSuspectId());
	        assertEquals("Rakesh", suspect.getFirstName());
	        assertEquals("Yadav", suspect.getLastName());
	        assertEquals(dob, suspect.getDateOfBirth());
	        assertEquals(Gender.MALE, suspect.getGender());
	        assertEquals("9876543210", suspect.getPhoneNumber());
	    }

	    @Test
	    public void testSuspectsSettersAndGetters() {
	        Date dob = new Date();
	        Suspects suspect = new Suspects();

	        suspect.setSuspectId(2);
	        suspect.setFirstName("Pooja");
	        suspect.setLastName("Shetty");
	        suspect.setDateOfBirth(dob);
	        suspect.setGender(Gender.FEMALE);
	        suspect.setPhoneNumber("9988776655");

	        assertEquals(2, suspect.getSuspectId());
	        assertEquals("Pooja", suspect.getFirstName());
	        assertEquals("Shetty", suspect.getLastName());
	        assertEquals(dob, suspect.getDateOfBirth());
	        assertEquals(Gender.FEMALE, suspect.getGender());
	        assertEquals("9988776655", suspect.getPhoneNumber());
	    }

	    @Test
	    public void testSuspectsToString() {
	        Date dob = new Date();
	        Suspects suspect = new Suspects(3, "Aman", "Kumar", dob, Gender.MALE, "9112233445");

	        String expected = "Suspects(suspectId=3, firstName=Aman, lastName=Kumar, dateOfBirth=" + dob.toString()
	                + ", gender=MALE, phoneNumber=9112233445)";
	        assertEquals(expected, suspect.toString());
	    }

}
