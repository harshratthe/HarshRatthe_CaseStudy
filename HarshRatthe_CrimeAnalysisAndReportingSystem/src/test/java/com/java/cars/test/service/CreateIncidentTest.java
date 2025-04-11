package com.java.cars.test.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

import com.java.cars.dao.CrimeAnalysisService;
import com.java.cars.dao.CrimeAnalysisServiceImpl;
import com.java.cars.model.Gender;
import com.java.cars.model.Incidents;
import com.java.cars.model.Officers;
import com.java.cars.model.Suspects;
import com.java.cars.model.Victims;

public class CreateIncidentTest {

    private CrimeAnalysisService service;

    @Before
    public void setUp() {
        service = new CrimeAnalysisServiceImpl();
    }

    @Test
    public void testCreateIncident() throws Exception {

        Victims victim = new Victims(0, "Ravi", "Sharma",
                new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"),
                Gender.MALE, "21 MG Road, Pune");
        int victimId = service.addVictim(victim);

        Suspects suspect = new Suspects(0, "Neha", "Patil",
                new SimpleDateFormat("yyyy-MM-dd").parse("1985-05-15"),
                Gender.FEMALE, "9876543210");
        int suspectId = service.addSuspect(suspect);

        Officers officer = new Officers(0, "Arjun", "Deshmukh", "MH1201",
                "Sub-Inspector", "arjun.deshmukh@police.in", 1); 
        int officerId = service.addOfficer(officer);

        Incidents incident = new Incidents(0, "Chain Snatching",
                new SimpleDateFormat("yyyy-MM-dd").parse("2024-04-11"),
                "Lokmanya Tilak Chowk, Nagpur", "Victim was attacked by motorbike duo, gold chain stolen", "FIR Filed",
                victimId, suspectId, officerId);

        boolean result = service.createIncident(incident);
        assertTrue("Incident should be created successfully", result);
    }

}
