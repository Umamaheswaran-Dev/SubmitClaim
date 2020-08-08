package com.cognizant.membership.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class ClaimRequest {

    private Integer planid;

    @JsonProperty("date")
    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Integer amount;

    @NotNull(message = "prescriber_name cannot be null")
    @NotBlank(message = "prescriber_name cannot be blank")
    private String prescriber_name;

    @NotNull(message = "therapy_type cannot be null")
    @NotBlank(message = "therapy_type cannot be blank")
    private String therapy_type;
}


