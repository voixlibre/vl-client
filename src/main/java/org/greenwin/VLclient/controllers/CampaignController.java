package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.greenwin.VLclient.services.CampaignService;
import org.greenwin.VLclient.services.OptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static org.greenwin.VLclient.values.ValueType.CAMPAIGNS;

@Controller
@RequestMapping(CAMPAIGNS)
public class CampaignController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicProxy topicProxy;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private OptionService optionService;


    @GetMapping("/id/{id}")
    public String campaignDescription(@PathVariable ("id") int id, Model model){
        logger.info("### campaignDescription method ###");
        //TODO: vérifier que l'utilisateur est identifié
        //TODO: vérifier s'il a voté et modifier le code en fonction
        model.addAttribute("campaign", campaignService.getCampaignById(id));
        return "campaign/description";
    }

    @GetMapping
    public String allCampaign(Model model, HttpSession session){

        return "campaign/list";
    }

    @GetMapping("/form")
    public String form(Model model, HttpSession session){
        logger.info("### form method ###");
        model.addAttribute("topics", topicProxy.getTopics());
        sessionController.addSessionAttributes(session, model);
        return "campaign/form";
    }

    @PostMapping("/")
    public String saveCampaign(@ModelAttribute Campaign campaign,
                               @RequestParam String start,
                               @RequestParam String end,
                               @RequestParam String option1,
                               @RequestParam String option2,
                               @RequestParam String option3,
                               @RequestParam String option4,
                               Model model){
        logger.info("### saveCampaign method ###");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(start,formatter);
        LocalDate endDate = LocalDate.parse(end, formatter);
        campaign.setStartDate(startDate);
        campaign.setEndDate(endDate);
        campaign.setOptions(new ArrayList<>());
        Campaign registeredCampaign = campaignService.saveCampaign(campaign);
        addOptionIfNotEmpty(registeredCampaign, option1);
        addOptionIfNotEmpty(registeredCampaign, option2);
        addOptionIfNotEmpty(registeredCampaign, option3);
        addOptionIfNotEmpty(registeredCampaign, option4);
        model.addAttribute("campaign", registeredCampaign);
        return "campaign/confirmation";
    }

    private void addOptionIfNotEmpty(Campaign campaign, String option){
        logger.info("### addOptionIfNotEmpty method ###");
        Option o = new Option(option, campaign);
        logger.info("option: " + option);
        if (option != "") {
            o.setCampaignId(campaign.getId());
            optionService.saveOption(o);
        }
    }
}
