package com.java.cars.exception;

public class IncidentNumberNotFoundException extends Exception {

	public IncidentNumberNotFoundException() {
        super("Incident not found with the given ID.");
    }

    public IncidentNumberNotFoundException(String message) {
        super(message);
    }
}
