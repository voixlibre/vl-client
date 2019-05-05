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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CampaignProxy campaignProxy;

    @Autowired
    SessionController sessionController;

    @GetMapping("/")
    public String home(Model model, HttpSession session){
        logger.info("calling home.html");
        sessionController.init(session);
        List<Campaign> mostRecent = campaignProxy.getMostRecentCampaigns();
        model.addAttribute("recentCampaigns", campaignProxy.getMostRecentCampaigns());
        return "home";
    }
}
