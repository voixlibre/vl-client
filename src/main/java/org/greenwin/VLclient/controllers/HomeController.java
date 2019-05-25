package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

import static org.greenwin.VLclient.values.ValueType.HOME;

@Controller
@RequestMapping(HOME)
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    private TopicProxy topicProxy;

    @Autowired
    private SessionController sessionController;

    /**
     *
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/")
    public String home(Model model, HttpSession session){

        logger.info(getClass() + "### home method ###");
        logger.info("user: " + session.getAttribute("user"));
        sessionController.addSessionAttributes(session, model);

        //get most recent campaigns and assign each their respective topic
        List<Campaign> mostRecent = campaignProxy.getMostRecentCampaigns();
        for (Campaign campaign : mostRecent)
            campaign.setTopic(topicProxy.getTopicById(campaign.getTopicId()));
        model.addAttribute("recentCampaigns", mostRecent);

        return "home";
    }


}
