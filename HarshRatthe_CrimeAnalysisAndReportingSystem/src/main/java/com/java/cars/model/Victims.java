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
public class Victims {
    private int victimId;
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Gender gender; 
    private String address;
}