package com.java.cars.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cars.model.LawEnforcementAgencies;

public class LawEnforcementAgenciesTest {

	@Test
    public void testAgencyConstructor() {
        LawEnforcementAgencies agency = new LawEnforcementAgencies(1, "Nagpur City Police", "Nagpur", "0712-2563123");

        assertEquals(1, agency.getAgencyId());
        assertEquals("Nagpur City Police", agency.getAgencyName());
        assertEquals("Nagpur", agency.getJurisdiction());
        assertEquals("0712-2563123", agency.getContactInformation());
    }

    @Test
    public void testAgencySettersAndGetters() {
        LawEnforcementAgencies agency = new LawEnforcementAgencies();

        agency.setAgencyId(2);
        agency.setAgencyName("Mumbai Crime Branch");
        agency.setJurisdiction("Mumbai");
        agency.setContactInformation("022-26123456");

        assertEquals(2, agency.getAgencyId());
        assertEquals("Mumbai Crime Branch", agency.getAgencyName());
        assertEquals("Mumbai", agency.getJurisdiction());
        assertEquals("022-26123456", agency.getContactInformation());
    }

    @Test
    public void testAgencyToString() {
        LawEnforcementAgencies agency = new LawEnforcementAgencies(3, "Cyber Crime Unit", "Bengaluru", "080-22111111");

        String expected = "LawEnforcementAgencies(agencyId=3, agencyName=Cyber Crime Unit, jurisdiction=Bengaluru, contactInformation=080-22111111)";
        assertEquals(expected, agency.toString());
    }

}
