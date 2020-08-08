package com.cognizant.membership.service;

import com.cognizant.membership.beans.ClaimRequest;
import com.cognizant.membership.beans.SubmitClaimRequest;
import com.cognizant.membership.beans.SubmitClaimResponse;
import com.cognizant.membership.model.ClaimDetails;
import com.cognizant.membership.model.Member;
import com.cognizant.membership.model.Plan;
import com.cognizant.membership.repository.ClaimDetailsRepository;
import com.cognizant.membership.repository.MemberRepository;
import com.cognizant.membership.repository.PlanRepository;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClaimService {

    Logger logger = LoggerFactory.getLogger(ClaimService.class);

    int totalClaimedAmount = 0;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private ClaimDetailsRepository claimDetailsRepository;

    public SubmitClaimResponse submitClaim(SubmitClaimRequest submitClaimRequest) {
        SubmitClaimResponse response = new SubmitClaimResponse();
        try {
            switch (authenicateUser(submitClaimRequest)) {
                case "USER_INVALID":
                    response.setClaimId(0);
                    response.setStatusCode(400);
                    response.setStatusMessage("Invalid User ID");
                    break;

                case "INVALID_PASSWORD":
                    response.setClaimId(0);
                    response.setStatusCode(400);
                    response.setStatusMessage("Invalid password for the user");
                    break;

                case "INVALIID_PLAN":
                    response.setClaimId(0);
                    response.setStatusCode(400);
                    response.setStatusMessage("Plan ID mismatch with User");
                    break;

                case "AMOUNT_EXCEEDS":
                    response.setClaimId(0);
                    response.setStatusCode(400);
                    response.setStatusMessage("Claimed amount exceeds the plan amount");
                    break;

                case "READY_TO_CLAIM":
                    ClaimDetails claimDetails = new ClaimDetails();
                    ClaimRequest claimRequest = submitClaimRequest.getClaimRequest();
                    BeanUtils.copyProperties(claimRequest, claimDetails);
                    claimDetails.setMemberid(submitClaimRequest.getMemberid());
                    claimDetailsRepository.save(claimDetails);
                    response.setClaimId(claimDetails.getId());
                    response.setStatusCode(201);
                    response.setStatusMessage("SUCCESS");
                    break;

                default:
                    response.setClaimId(0);
                    response.setStatusCode(400);
                    response.setStatusMessage("Please cross check your input values");
            }


        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            response.setClaimId(0);
            response.setStatusCode(500);
            response.setStatusMessage(e.getMessage());
        }


        return response;
    }

    private String authenicateUser(SubmitClaimRequest submitClaimRequest) {
        if (memberRepository.existsByUserid(submitClaimRequest.getUserid())) {
            List<Member> memberList = memberRepository.findByUserid(submitClaimRequest.getUserid());

            if (validatePassword(submitClaimRequest, memberList.get(0).getPassword())) {

                List<Plan> planList = planRepository.findByPlanid(memberList.get(0).getPlan().getPlanid());

                ClaimRequest claimRequest = submitClaimRequest.getClaimRequest();
                if (claimRequest.getPlanid() == planList.get(0).getPlanid()) {

                    List<ClaimDetails> claimDetailsList = claimDetailsRepository.findByMemberid(submitClaimRequest.getMemberid());
                    logger.info("claimDetailsList size::" + claimDetailsList.size());
                    int planAmount = planList.get(0).getPlanamount();
                    totalClaimedAmount = 0;

                    claimDetailsList.forEach(claimDetail -> {
                        logger.info("Cliam ID" + claimDetail.getId() + "Claim Amount:" + claimDetail.getAmount());
                        this.totalClaimedAmount += claimDetail.getAmount();
                    });

                    int remainingAmount = planAmount - totalClaimedAmount;
                    logger.info("Total Claimed Amount::" + totalClaimedAmount);
                    logger.info("Remaining Amount::" + remainingAmount);

                    if (claimRequest.getAmount() > remainingAmount) {
                        return "AMOUNT_EXCEEDS";
                    } else {
                        return "READY_TO_CLAIM";
                    }


                } else {
                    return "INVALIID_PLAN";
                }

            } else {
                return "INVALID_PASSWORD";
            }
        } else {
            return "USER_INVALID";
        }
    }

    private boolean validatePassword(SubmitClaimRequest submitClaimRequest, String dbPassword) {

        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        boolean result = passwordEncryptor.checkPassword(submitClaimRequest.getPassword(), dbPassword);

        return result;
    }
}
