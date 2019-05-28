package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CampaignResultsServiceTest {

    @Mock
    private CampaignProxy campaignProxy;

    @Mock
    private OptionProxy optionProxy;

    @InjectMocks
    private CampaignResultsService campaignResultsService;

    @Test
    public void getResults() {
        Campaign campaign = new Campaign();
        Option option = new Option("test", campaign);
        campaign.setId(1);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        when(campaignProxy.getCampaignResults(1)).thenReturn(map);
        when(optionProxy.getOptionById(anyInt())).thenReturn(option);
        campaignResultsService.getResults(campaign);
    }


}
