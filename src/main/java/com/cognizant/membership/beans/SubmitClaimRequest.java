package com.cognizant.membership.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class SubmitClaimRequest {

    @NotNull(message = "User ID cannot be null")
    @NotBlank(message = "User ID cannot be blank")
    private String userid;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    private String password;

    private Integer memberid;

    @JsonProperty("claim")
    private ClaimRequest claimRequest;
}
