package com.java.cars.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cars.model.Officers;

public class OfficersTest {

	  @Test
	    public void testOfficersConstructor() {
	        Officers officer = new Officers(1, "Rajesh", "Thakur", "MH2023", "Inspector", "rajesh.thakur@nagpurpolice.in", 101);

	        assertEquals(1, officer.getOfficerId());
	        assertEquals("Rajesh", officer.getFirstName());
	        assertEquals("Thakur", officer.getLastName());
	        assertEquals("MH2023", officer.getBadgeNumber());
	        assertEquals("Inspector", officer.getOfficerRank());
	        assertEquals("rajesh.thakur@nagpurpolice.in", officer.getContactInformation());
	        assertEquals(101, officer.getAgencyId());
	    }

	    @Test
	    public void testOfficersSettersAndGetters() {
	        Officers officer = new Officers();

	        officer.setOfficerId(2);
	        officer.setFirstName("Kiran");
	        officer.setLastName("Deshmukh");
	        officer.setBadgeNumber("DL456");
	        officer.setOfficerRank("Sub Inspector");
	        officer.setContactInformation("kiran.deshmukh@delhipolice.gov.in");
	        officer.setAgencyId(202);

	        assertEquals(2, officer.getOfficerId());
	        assertEquals("Kiran", officer.getFirstName());
	        assertEquals("Deshmukh", officer.getLastName());
	        assertEquals("DL456", officer.getBadgeNumber());
	        assertEquals("Sub Inspector", officer.getOfficerRank());
	        assertEquals("kiran.deshmukh@delhipolice.gov.in", officer.getContactInformation());
	        assertEquals(202, officer.getAgencyId());
	    }

	    @Test
	    public void testOfficersToString() {
	        Officers officer = new Officers(3, "Anita", "Joshi", "GJ987", "ACP", "anita.joshi@ahmedabadpolice.in", 303);

	        String expected = "Officers(officerId=3, firstName=Anita, lastName=Joshi, badgeNumber=GJ987, officerRank=ACP, contactInformation=anita.joshi@ahmedabadpolice.in, agencyId=303)";
	        assertEquals(expected, officer.toString());
	    }	
}
