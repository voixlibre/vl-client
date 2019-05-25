package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
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

import javax.servlet.http.HttpSession;
import java.util.Map;

import static org.greenwin.VLclient.values.ValueType.CAMPAIGN_RESULTS;

@Controller
@RequestMapping(CAMPAIGN_RESULTS)
public class CampaignResultsController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    SessionController sessionController;

    @Autowired
    private CampaignResultsService campaignResultsService;

    @GetMapping("/{campaign_id}")
    public String showResults(@PathVariable ("campaign_id") int id, Model model, HttpSession session){
        logger.info(getClass() + "### showResults method ###");
        int totalVotes;
        Campaign campaign = campaignProxy.getCampaignById(id);
        Map<Option, Integer> results = campaignResultsService.getResults(campaign);
        model.addAttribute("campaign", campaign);
        model.addAttribute("results", results);
        model.addAttribute("totalVotes", campaign.getVotes().size());
        sessionController.addSessionAttributes(session, model);
        return "campaign/results";
    }
}
