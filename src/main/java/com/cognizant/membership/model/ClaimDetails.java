package com.cognizant.membership.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@Table
public class ClaimDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer memberid;

    private Integer planid;

    @JsonProperty("date")
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer amount;

    private String prescriber_name;

    private String therapy_type;
}
