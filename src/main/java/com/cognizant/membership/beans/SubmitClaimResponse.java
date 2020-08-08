package com.cognizant.membership.beans;

import lombok.Data;

@Data
public class SubmitClaimResponse {

    private Integer claimId;

    private Integer statusCode;

    private String statusMessage;

}
