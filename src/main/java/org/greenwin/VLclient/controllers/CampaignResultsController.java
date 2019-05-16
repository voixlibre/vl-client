package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class CampaignResultsController {

    @Autowired
    CampaignProxy campaignProxy;

    @GetMapping("/{campaign_id}")
    public String showResults(@PathVariable ("campaign_id") int id, Model model){
        Campaign campaign = campaignProxy.getCampaignById(id);
        model.addAttribute("campaign", campaign);
        return "campaign/results";
    }
}
