package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

    @Mock
    private CampaignProxy campaignProxy;

    @InjectMocks
    private CampaignService campaignService;

    Campaign campaign = new Campaign();

    @Test
    public void getCampaignById() {
        campaignService.getCampaignById(1);
        verify(campaignProxy, times(1)).getCampaignById(1);
    }

    @Test
    public void saveCampaign() {
        campaignService.saveCampaign(campaign);
        verify(campaignProxy, times(1)).addCampaign(campaign);
    }

    @Test
    public void updateCampaign() {
        campaignService.updateCampaign(campaign);
        verify(campaignProxy, times(1)).updateCampaign(campaign);
    }

    @Test
    public void selectCampaigns() {
        campaignService.selectCampaigns(campaign);
        verify(campaignProxy, times(1)).selectCampaign(campaign);
    }
}
