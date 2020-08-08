package com.cognizant.membership.service;

import com.cognizant.membership.beans.SubmitClaimRequest;
import com.cognizant.membership.beans.SubmitClaimResponse;
import com.cognizant.membership.repository.ClaimDetailsRepository;
import com.cognizant.membership.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ClaimServiceTest {

    @InjectMocks
    ClaimService claimService;

    @Mock
    private ClaimDetailsRepository cdRepository;

    @Mock
    private MemberRepository mdRepository;

    @Test
    public void testSubmitClaim() {
        SubmitClaimRequest sc_request = new SubmitClaimRequest();
        sc_request.setUserid("mahes876");
        sc_request.setPassword("password");

        Mockito.when(mdRepository.existsByUserid(Mockito.any())).thenReturn(false);

        SubmitClaimResponse submitClaimResponse = claimService.submitClaim(sc_request);
        Assert.assertNotNull(submitClaimResponse);
        Assert.assertEquals(Integer.valueOf(400), submitClaimResponse.getStatusCode());
    }
}
