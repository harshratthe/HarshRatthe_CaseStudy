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

public class Reports {
    private int reportId;
    private int incidentId;
    private int OfficerId;
    private Date reportDate;
    private String reportDetails;
    private String status;
}
