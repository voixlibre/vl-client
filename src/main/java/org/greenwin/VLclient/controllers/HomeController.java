package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CampaignProxy campaignProxy;

    @GetMapping("/")
    public String home(Model model){
        logger.info("calling home.html");
        List<Campaign> mostRecent = campaignProxy.getMostRecentCampaigns();
        for (int i = 0 ; i < mostRecent.size(); i++){
            for (Option option : mostRecent.get(i).getOptions()
                 ) {
                logger.info("campaign: " + mostRecent.get(i).getId());
                logger.info("option: " + option);

            }
        }
        model.addAttribute("recentCampaigns", campaignProxy.getMostRecentCampaigns());
        return "home";
    }
}
