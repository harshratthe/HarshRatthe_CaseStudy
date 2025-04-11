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
public class LawEnforcementAgencies {
    private int agencyId;
    private String agencyName;
    private String jurisdiction;
    private String contactInformation;
}