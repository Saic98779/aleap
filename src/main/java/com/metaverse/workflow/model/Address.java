package com.metaverse.workflow.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String houseNo;
    private String streetName;
    private String landmark;
    private String location;
    private String village;
    private String villageOther;
    private String mandal;
    private String mandalOther;
    private String district;
    private String state;
    private String pincode;
}