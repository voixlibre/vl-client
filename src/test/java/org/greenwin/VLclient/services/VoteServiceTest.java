package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceTest {

    @Mock
    private VoteProxy voteProxy;

    @Mock
    private CampaignProxy campaignProxy;

    @Mock
    private HttpSession session;

    @InjectMocks
    private VoteService voteService;

    Campaign campaign = new Campaign();

    private Option option;

    @Test
    public void saveVote() {
        campaign.setId(1);
        campaign.setStartDate(LocalDate.of(2000, 1, 1));
        campaign.setEndDate(LocalDate.of(2999, 1, 1));
        when(session.getAttribute("user")).thenReturn(new AppUser());
        when(campaignProxy.getCampaignById(1)).thenReturn(campaign);
        voteService.saveVote(campaign.getId(), option, session);
        verify(voteProxy, times(1)).saveVote(any());

    }
}
