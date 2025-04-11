package com.java.cars.main;

import com.java.cars.dao.CrimeAnalysisService;
import com.java.cars.dao.CrimeAnalysisServiceImpl;
import com.java.cars.exception.IncidentNumberNotFoundException;
import com.java.cars.model.*;
import com.java.cars.util.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainModule {

    static CrimeAnalysisService crimeDao;
    static Scanner sc;

    static {
        crimeDao = new CrimeAnalysisServiceImpl();
        sc = new Scanner(System.in);
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n**** CRIME ANALYSIS AND REPORTING SYSTEM ****\n");
            System.out.println("1. Create New Incident");
            System.out.println("2. Update Incident Status");
            System.out.println("3. Get Incidents in Date Range");
            System.out.println("4. Search Incidents by Type");
            System.out.println("5. Generate Incident Report");
            System.out.println("6. show all Incident Report");
            System.out.println("7. Exit\n");
            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createIncident();
                    break;
                case 2:
                    updateIncidentStatus();
                    break;
                case 3:
                    getIncidentsInDateRange();
                    break;
                case 4:
                    searchIncidentsByType();
                    break;
                case 5:
                    generateIncidentReport();
                    break;
                case 6: 
                	showAllReports(crimeDao); 
                    break;
                case 7:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 6);
    }

    public static void createIncident() {
        try {
            sc.nextLine(); 

            int victimId = createVictim();
            int suspectId = createSuspect();
            int officerId = createOfficer();

            System.out.println("\nEnter Incident Details:");
            System.out.print("Incident Type: ");
            String type = sc.nextLine();
            System.out.print("Incident Date (yyyy-MM-dd): ");
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
            System.out.print("Location: ");
            String location = sc.nextLine();
            System.out.print("Description: ");
            String desc = sc.nextLine();
            System.out.print("Status: ");
            String status = sc.nextLine();

            Incidents incident = new Incidents(0, type, date, location, desc, status, victimId, suspectId, officerId);
            boolean success = crimeDao.createIncident(incident);
            System.out.println(success ? "\n✅ Incident created successfully!" : "\n❌ Failed to create incident.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int createVictim() throws ParseException, SQLException, ClassNotFoundException {
        System.out.println("Enter Victim Details:");
        System.out.print("First Name: ");
        String vFirst = sc.nextLine();
        System.out.print("Last Name: ");
        String vLast = sc.nextLine();
        System.out.print("Date of Birth (yyyy-MM-dd): ");
        Date vDob = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
        System.out.print("Gender (MALE/FEMALE): ");
        Gender vGender = Gender.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Address: ");
        String vAddress = sc.nextLine();

        Victims victim = new Victims(0, vFirst, vLast, vDob, vGender, vAddress);
        return crimeDao.addVictim(victim);
    }

    public static int createSuspect() throws ParseException, SQLException, ClassNotFoundException {
        System.out.println("\nEnter Suspect Details:");
        System.out.print("First Name: ");
        String sFirst = sc.nextLine();
        System.out.print("Last Name: ");
        String sLast = sc.nextLine();
        System.out.print("Date of Birth (yyyy-MM-dd): ");
        Date sDob = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
        System.out.print("Gender (MALE/FEMALE): ");
        Gender sGender = Gender.valueOf(sc.nextLine().toUpperCase());
        System.out.print("Phone Number: ");
        String phone = sc.nextLine();

        Suspects suspect = new Suspects(0, sFirst, sLast, sDob, sGender, phone);
        return crimeDao.addSuspect(suspect);
    }

    public static int createOfficer() throws SQLException, ClassNotFoundException {
        System.out.println("\nEnter Officer Details:");
        System.out.print("First Name: ");
        String first = sc.nextLine();
        System.out.print("Last Name: ");
        String last = sc.nextLine();
        System.out.print("Badge Number: ");
        String badge = sc.nextLine();
        System.out.print("Rank: ");
        String rank = sc.nextLine();
        System.out.print("Contact Info: ");
        String contact = sc.nextLine();
        System.out.print("Agency ID: ");
        int agencyId = sc.nextInt();
        sc.nextLine(); 

        Officers officer = new Officers(0, first, last, badge, rank, contact, agencyId);
        return crimeDao.addOfficer(officer); 
    }

    public static void updateIncidentStatus() {
        System.out.print("Enter Incident ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // clear buffer
        System.out.print("Enter New Status: ");
        String status = sc.nextLine();

        try {
            boolean updated = crimeDao.updateIncidentStatus(id, status);
            if (!updated) {
                throw new IncidentNumberNotFoundException("No incident found with ID: " + id);
            }
            System.out.println("Status updated successfully.");
        } catch (ClassNotFoundException | SQLException | IncidentNumberNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void getIncidentsInDateRange() {
        try {
            sc.nextLine();
            System.out.print("Enter Start Date (yyyy-MM-dd): ");
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());
            System.out.print("Enter End Date (yyyy-MM-dd): ");
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(sc.nextLine());

            List<Incidents> list = crimeDao.getIncidentsInDateRange(startDate, endDate);
            if (list.isEmpty()) {
                System.out.println("No incidents found in given range.");
            } else {
                list.forEach(System.out::println);
            }
        } catch (ParseException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void searchIncidentsByType() {
        sc.nextLine();
        System.out.print("Enter Incident Type to Search: ");
        String type = sc.nextLine();

        try {
            List<Incidents> list = crimeDao.searchIncidentsByType(type);
            if (list.isEmpty()) {
                System.out.println("No incidents found for type: " + type);
            } else {
                list.forEach(System.out::println);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateIncidentReport() {
        System.out.print("Enter Incident ID to Generate Report: ");
        int id = sc.nextInt();

        try {
            Incidents incident = crimeDao.getIncidentById(id);
            sc.nextLine();

            if (incident == null) {
                System.out.println("❌ No incident found.");
                return;
            }

            Reports report = crimeDao.generateIncidentReport(incident);
            if (report != null) {
                System.out.println("\n✅ Report Generated:");
                System.out.println("Details:\n" + report.getReportDetails());
            } else {
                System.out.println("❌ Report generation failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void showAllReports(CrimeAnalysisService crimeAnalysisService) {
        
    	List<Reports> reportsList = null;
		try {
			reportsList = crimeAnalysisService.getAllReports();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Fetch all reports

        if (reportsList.isEmpty()) {
            System.out.println("No reports found.");
        } else {
            System.out.println("\nAll Reports:");
            for (Reports report : reportsList) {
                System.out.println(report); 
            }
        }
    }
    
    
    
    
    
}