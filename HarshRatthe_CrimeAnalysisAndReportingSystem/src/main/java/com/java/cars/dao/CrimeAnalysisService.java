package com.java.cars.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.java.cars.model.Incidents;
import com.java.cars.model.Officers;
import com.java.cars.model.Reports;
import com.java.cars.model.Suspects;
import com.java.cars.model.Victims;

public interface CrimeAnalysisService {
	
	
    boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException;
    
    boolean updateIncidentStatus(int incidentId, String status) throws ClassNotFoundException, SQLException;
    
    List<Incidents> getIncidentsInDateRange(Date start, Date end) throws ClassNotFoundException, SQLException;
    
    List<Incidents> searchIncidentsByType(String type) throws ClassNotFoundException, SQLException;
        
    Reports generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException;
    
    
    // Fetches a specific incident based on its unique ID.
    Incidents getIncidentById(int incidentId) throws ClassNotFoundException, SQLException;
    
 // Adds a new victim record associated with an incident.
    int addVictim(Victims victim) throws SQLException, ClassNotFoundException;
    
 // Adds a new suspect record associated with an incident.
    int addSuspect(Suspects suspect) throws SQLException, ClassNotFoundException;
    
 // Adds a new police officer record to the system.
    int addOfficer(Officers officer) throws ClassNotFoundException;
    
    
 // Retrieves all incident reports from the system
    List<Reports> getAllReports() throws ClassNotFoundException;


}
