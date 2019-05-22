package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class CampaignService {

    @Autowired
    CampaignProxy campaignProxy;

    @Autowired
    TopicProxy topicProxy;

    public Campaign getCampaignById(int id){
        //TODO: vérifier que l'utilisateur est identifié
        //TODO: vérifier s'il a voté et modifier le code en fonction
        return campaignProxy.getCampaignById(id);
    }

    public String form(Model model, HttpSession session){
        model.addAttribute("topics", topicProxy.getTopics());
        return "campaign/form";
    }

    public Campaign saveCampaign(Campaign campaign){
        return campaignProxy.addCampaign(campaign);
    }

    public Campaign updateCampaign(Campaign campaign){
        return campaignProxy.updateCampaign(campaign);
    }


}
