package com.java.cars.test.model;

import static org.junit.Assert.*;

import org.junit.Test;

import com.java.cars.model.Evidence;

public class EvidenceTest {


    @Test
    public void testEvidenceConstructor() {
        Evidence evidence = new Evidence(1, "Knife with fingerprints", "Lajpat Nagar", 101);

        assertEquals(1, evidence.getEvidenceId());
        assertEquals("Knife with fingerprints", evidence.getDescription());
        assertEquals("Lajpat Nagar", evidence.getLocationFound());
        assertEquals(101, evidence.getIncidentId());
    }

    @Test
    public void testEvidenceSettersAndGetters() {
        Evidence evidence = new Evidence();

        evidence.setEvidenceId(2);
        evidence.setDescription("Blood-stained shirt");
        evidence.setLocationFound("Churchgate Station");
        evidence.setIncidentId(102);

        assertEquals(2, evidence.getEvidenceId());
        assertEquals("Blood-stained shirt", evidence.getDescription());
        assertEquals("Churchgate Station", evidence.getLocationFound());
        assertEquals(102, evidence.getIncidentId());
    }

    @Test
    public void testEvidenceToString() {
        Evidence evidence = new Evidence(3, "CCTV footage", "Sector 17, Chandigarh", 103);

        String expected = "Evidence(evidenceId=3, description=CCTV footage, locationFound=Sector 17, Chandigarh, incidentId=103)";
        assertEquals(expected, evidence.toString());
    }

}
