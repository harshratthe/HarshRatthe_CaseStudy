package com.java.cars.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.java.cars.model.Incidents;
import com.java.cars.model.Officers;
import com.java.cars.model.Reports;
import com.java.cars.model.Suspects;
import com.java.cars.model.Victims;
import com.java.cars.util.ConnectionHelper;

public class CrimeAnalysisServiceImpl implements CrimeAnalysisService {

	

	    @Override
	    public boolean createIncident(Incidents incident) throws ClassNotFoundException, SQLException {
	        String cmd = "INSERT INTO Incidents (incidentType, incidentDate, location, description, status, victimId, suspectId, officerId) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	        Connection conn = ConnectionHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(cmd);
	        ps.setString(1, incident.getIncidentType());
	        ps.setDate(2, new java.sql.Date(incident.getIncidentDate().getTime()));
	        ps.setString(3, incident.getLocation());
	        ps.setString(4, incident.getDescription());
	        ps.setString(5, incident.getStatus());
	        ps.setInt(6, incident.getVictimId());
	        ps.setInt(7, incident.getSuspectId());
	        ps.setInt(8, incident.getOfficerId());

	        int rows = ps.executeUpdate();
	        return rows > 0;
	    }

	    @Override
	    public boolean updateIncidentStatus(int incidentId, String status) throws ClassNotFoundException, SQLException {
	        String cmd = "UPDATE Incidents SET status = ? WHERE incidentId = ?";
	        Connection conn = ConnectionHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(cmd);
	        ps.setString(1, status);
	        ps.setInt(2, incidentId);
	        return ps.executeUpdate() > 0;
	    }

	    @Override
	    public List<Incidents> getIncidentsInDateRange(Date start, Date end) throws ClassNotFoundException, SQLException {
	        List<Incidents> list = new ArrayList<>();
	        String cmd = "SELECT * FROM Incidents WHERE incidentDate BETWEEN ? AND ?";
	        Connection conn = ConnectionHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(cmd);
	        ps.setDate(1, new java.sql.Date(start.getTime()));
	        ps.setDate(2, new java.sql.Date(end.getTime()));
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Incidents i = new Incidents(
	                rs.getInt("incidentId"),
	                rs.getString("incidentType"),
	                rs.getDate("incidentDate"),
	                rs.getString("location"),
	                rs.getString("description"),
	                rs.getString("status"),
	                rs.getInt("victimId"),
	                rs.getInt("suspectId"),
	                rs.getInt("officerId")
	            );
	            list.add(i);
	        }

	        return list;
	    }

	    @Override
	    public List<Incidents> searchIncidentsByType(String type) throws ClassNotFoundException, SQLException {
	        List<Incidents> list = new ArrayList<>();
	        String cmd = "SELECT * FROM Incidents WHERE incidentType = ?";
	        Connection conn = ConnectionHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(cmd);
	        ps.setString(1, type);
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            Incidents i = new Incidents(
	                rs.getInt("incidentId"),
	                rs.getString("incidentType"),
	                rs.getDate("incidentDate"),
	                rs.getString("location"),
	                rs.getString("description"),
	                rs.getString("status"),
	                rs.getInt("victimId"),
	                rs.getInt("suspectId"),
	                rs.getInt("officerId")
	            );
	            list.add(i);
	        }

	        return list;
	    }

	    @Override
	    public Incidents getIncidentById(int incidentId) throws ClassNotFoundException, SQLException {
	        String cmd = "SELECT * FROM Incidents WHERE incidentId = ?";
	        Connection conn = ConnectionHelper.getConnection();
	        PreparedStatement ps = conn.prepareStatement(cmd);
	        ps.setInt(1, incidentId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return new Incidents(
	                rs.getInt("incidentId"),
	                rs.getString("incidentType"),
	                rs.getDate("incidentDate"),
	                rs.getString("location"),
	                rs.getString("description"),
	                rs.getString("status"),
	                rs.getInt("victimId"),
	                rs.getInt("suspectId"),
	                rs.getInt("officerId")
	            );
	        }

	        return null;
	    }

	    @Override
	    public Reports generateIncidentReport(Incidents incident) throws ClassNotFoundException, SQLException {
	    	 Scanner sc = new Scanner(System.in);

	    	    System.out.print("Enter Report Status: ");
	    	    String status = sc.nextLine();

	    	    System.out.print("Enter Report Details: ");
	    	    String details = sc.nextLine();

	    	    String sql = "INSERT INTO Reports (incidentId, officerId, reportDate, reportDetails, status) VALUES (?, ?, ?, ?, ?)";
	    	    Connection conn = ConnectionHelper.getConnection();
	    	    PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

	    	    Date reportDate = new Date();

	    	    ps.setInt(1, incident.getIncidentId());
	    	    ps.setInt(2, incident.getOfficerId());
	    	    ps.setDate(3, new java.sql.Date(reportDate.getTime()));
	    	    ps.setString(4, details); 
	    	    ps.setString(5, status);

	    	    int rows = ps.executeUpdate();

	    	    if (rows > 0) {
	    	        ResultSet rs = ps.getGeneratedKeys();
	    	        if (rs.next()) {
	    	            int reportId = rs.getInt(1);
	    	            return new Reports(reportId, incident.getIncidentId(), incident.getOfficerId(), reportDate, details, status);
	    	        }
	    	    }
	    	    return null;
	    }

		@Override
		public int addVictim(Victims victim) throws SQLException, ClassNotFoundException {
			 String sql = "INSERT INTO victims (firstName, lastName, dateOfBirth, gender, address) VALUES (?, ?, ?, ?, ?)";
			    try (Connection conn = ConnectionHelper.getConnection();
			         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			        
			        ps.setString(1, victim.getFirstName());
			        ps.setString(2, victim.getLastName());
			        ps.setDate(3, new java.sql.Date(victim.getDateOfBirth().getTime()));
			        ps.setString(4, victim.getGender().toString());
			        ps.setString(5, victim.getAddress());

			        ps.executeUpdate();

			        ResultSet rs = ps.getGeneratedKeys();
			        if (rs.next()) {
			            return rs.getInt(1); 
			        }
			    }
			    return -1; // if insert failed
		}

		@Override
		public int addSuspect(Suspects suspect) throws SQLException, ClassNotFoundException {
			String sql = "INSERT INTO suspects (firstName, lastName, dateOfBirth, gender, phoneNumber) VALUES (?, ?, ?, ?, ?)";
		    try (Connection conn = ConnectionHelper.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
		        
		        ps.setString(1, suspect.getFirstName());
		        ps.setString(2, suspect.getLastName());
		        ps.setDate(3, new java.sql.Date(suspect.getDateOfBirth().getTime()));
		        ps.setString(4, suspect.getGender().toString());
		        ps.setString(5, suspect.getPhoneNumber());

		        ps.executeUpdate();

		        ResultSet rs = ps.getGeneratedKeys();
		        if (rs.next()) {
		            return rs.getInt(1); 
		        }
		    }
		    return -1; // if insert failed
		}

		@Override
		public int addOfficer(Officers officer) throws ClassNotFoundException {
			int officerId = 0;
		    String sql = "INSERT INTO Officers (firstName, lastName, badgeNumber, officerRank, contactInformation, agencyId) VALUES (?, ?, ?, ?, ?, ?)";

		    try (Connection conn = ConnectionHelper.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

		        pstmt.setString(1, officer.getFirstName());
		        pstmt.setString(2, officer.getLastName());
		        pstmt.setString(3, officer.getBadgeNumber());
		        pstmt.setString(4, officer.getOfficerRank());
		        pstmt.setString(5, officer.getContactInformation());
		        pstmt.setInt(6, officer.getAgencyId());

		        int rows = pstmt.executeUpdate();

		        if (rows > 0) {
		            ResultSet rs = pstmt.getGeneratedKeys();
		            if (rs.next()) {
		                officerId = rs.getInt(1); 
		            }
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return officerId;
		}

		@Override
		public List<Reports> getAllReports() throws ClassNotFoundException {
			 List<Reports> reportsList = new ArrayList<>();
		        String query = "SELECT * FROM Reports"; // SQL query to fetch all reports

		        try (Connection connection = ConnectionHelper.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(query);
		             ResultSet resultSet = preparedStatement.executeQuery()) {

		            while (resultSet.next()) {
		                Reports report = new Reports(
		                        resultSet.getInt("reportId"),
		                        resultSet.getInt("incidentId"),
		                        resultSet.getInt("OfficerId"),
		                        resultSet.getDate("reportDate"),
		                        resultSet.getString("reportDetails"),
		                        resultSet.getString("status")
		                );
		                reportsList.add(report); 
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		        return reportsList;
		    }
		    
}

