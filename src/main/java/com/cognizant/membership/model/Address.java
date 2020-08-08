package com.cognizant.membership.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addressid")
    private Integer addressid;

    private String lineNumber1;

    private String lineNumber2;

    private String city;

    private String state;

    private String country;

    private String pincode;
}
