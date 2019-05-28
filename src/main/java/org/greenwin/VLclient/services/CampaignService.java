package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    CampaignProxy campaignProxy;

    @Autowired
    TopicProxy topicProxy;

    public Campaign getCampaignById(int id){
        return campaignProxy.getCampaignById(id);
    }

    public Campaign saveCampaign(Campaign campaign){
        return campaignProxy.addCampaign(campaign);
    }

    public Campaign updateCampaign(Campaign campaign){
        return campaignProxy.updateCampaign(campaign);
    }

    public List<Campaign> selectCampaigns(Campaign campaign){
        return campaignProxy.selectCampaign(campaign);
    }
}
