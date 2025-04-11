package com.java.cars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Officers {
    private int officerId;
    private String firstName;
    private String lastName;
    private String badgeNumber;
    private String officerRank;
    private String contactInformation;
    private int agencyId;
}