package com.cognizant.membership.controller;


import com.cognizant.membership.beans.SubmitClaimRequest;
import com.cognizant.membership.beans.SubmitClaimResponse;
import com.cognizant.membership.service.ClaimService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cms")
public class ClaimController {

    Logger logger = LoggerFactory.getLogger(ClaimController.class);

    @Autowired
    private ClaimService service;

    @RequestMapping(path = "/submitClaim", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public @ResponseBody
    SubmitClaimResponse submitClaim(@Valid @RequestBody SubmitClaimRequest submitClaimRequest) {

        logger.info("SubmitClaimRequest:::" + submitClaimRequest);
        SubmitClaimResponse response = service.submitClaim(submitClaimRequest);
        logger.info("SubmitClaimResponse:::" + response);

        return response;
    }
}
