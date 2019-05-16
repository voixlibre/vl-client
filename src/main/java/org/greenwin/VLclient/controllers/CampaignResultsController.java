package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.services.CampaignResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/results")
public class CampaignResultsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    private CampaignResultsService campaignResultsService;

    @GetMapping("/{campaign_id}")
    public String showResults(@PathVariable ("campaign_id") int id, Model model){
        logger.info(getClass() + "### showResults method ###");
        Campaign campaign = campaignProxy.getCampaignById(id);
        model.addAttribute("campaign", campaign);
        model.addAttribute("results", campaignResultsService.getResults(campaign));
        logger.info("nombre de votes: " + campaign.getVotes().size());
        return "campaign/results";
    }
}
