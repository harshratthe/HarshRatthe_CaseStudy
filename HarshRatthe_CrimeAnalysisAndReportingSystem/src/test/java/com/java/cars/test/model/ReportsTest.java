package com.java.cars.test.model;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.java.cars.model.Reports;

public class ReportsTest {

	  @Test
	    public void testReportsConstructor() {
	        Date date = new Date();
	        Reports report = new Reports(1, 101, 201, date, "Detailed investigation report", "Submitted");

	        assertEquals(1, report.getReportId());
	        assertEquals(101, report.getIncidentId());
	        assertEquals(201, report.getOfficerId());
	        assertEquals(date, report.getReportDate());
	        assertEquals("Detailed investigation report", report.getReportDetails());
	        assertEquals("Submitted", report.getStatus());
	    }

	    @Test
	    public void testReportsSettersAndGetters() {
	        Date date = new Date();
	        Reports report = new Reports();

	        report.setReportId(2);
	        report.setIncidentId(102);
	        report.setOfficerId(202);
	        report.setReportDate(date);
	        report.setReportDetails("Filed at Bandra Police Station");
	        report.setStatus("In Review");

	        assertEquals(2, report.getReportId());
	        assertEquals(102, report.getIncidentId());
	        assertEquals(202, report.getOfficerId());
	        assertEquals(date, report.getReportDate());
	        assertEquals("Filed at Bandra Police Station", report.getReportDetails());
	        assertEquals("In Review", report.getStatus());
	    }

	    @Test
	    public void testReportsToString() {
	        Date date = new Date();
	        Reports report = new Reports(3, 103, 203, date, "Final submission for case in Pune", "Closed");

	        String expected = "Reports(reportId=3, incidentId=103, OfficerId=203, reportDate=" + date.toString()
	                + ", reportDetails=Final submission for case in Pune, status=Closed)";
	        assertEquals(expected, report.toString());
	    }

}
