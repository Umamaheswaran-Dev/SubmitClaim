package com.cognizant.membership.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberid")
    private Integer id;

    private String name;

    private String userid;

    private String email;

    private String password;

    @Column(name = "dataOfBirth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataOfBirth;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "planid", referencedColumnName = "planid")
    private Plan plan;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressid", referencedColumnName = "addressid")
    private Address address;

    private String contact;
}
