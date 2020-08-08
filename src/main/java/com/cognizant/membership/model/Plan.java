package com.cognizant.membership.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planid")
    private Integer planid;

    private String planname;

    private Integer planamount;

    @JsonProperty("plan_start_date")
    @Column(name = "plan_start_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plan_start_date;

    @JsonProperty("plan_end_date")
    @Column(name = "plan_end_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plan_end_date;
}
