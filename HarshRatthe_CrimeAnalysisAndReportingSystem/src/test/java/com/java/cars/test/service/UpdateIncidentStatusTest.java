package com.java.cars.test.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.java.cars.dao.CrimeAnalysisService;
import com.java.cars.dao.CrimeAnalysisServiceImpl;
import com.java.cars.model.Incidents;

public class UpdateIncidentStatusTest {

	 private CrimeAnalysisService service;

	    @Before
	    public void setup() {
	        service = new CrimeAnalysisServiceImpl();
	    }

	    @Test
	    public void testUpdateIncidentStatus() throws Exception {
	        
	        List<Incidents> list = service.searchIncidentsByType("Theft");
	        int id = list.get(0).getIncidentId();

	       
	        assertEquals(true, service.updateIncidentStatus(id, "Closed"));
	    }
   

}
