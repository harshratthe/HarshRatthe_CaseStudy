package com.java.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Incidents {

    private int incidentId;
    private String incidentType;
    private Date incidentDate;
    private String location;
    private String description;
    private String status;

    private int victimId;
    private int suspectId;
    private int officerId;
}