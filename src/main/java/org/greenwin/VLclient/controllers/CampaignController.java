package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.greenwin.VLclient.services.CampaignService;
import org.greenwin.VLclient.services.CategoryService;
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
    private VoteProxy voteProxy;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SessionController sessionController;

    @Autowired
    private OptionService optionService;


    @GetMapping("/id/{id}")
    public String campaignDescription(@PathVariable ("id") int id, Model model, HttpSession session){
        logger.info("### campaignDescription method ###");
        sessionController.addSessionAttributes(session, model);
        AppUser user = (AppUser) session.getAttribute("user");
        if(user != null)
            model.addAttribute("vote", voteProxy.getVoteByUserAndCampaign(user.getId(), id));
        model.addAttribute("campaign", campaignService.getCampaignById(id));
        return "campaign/description";
    }

    @GetMapping
    public String allCampaign(Model model, HttpSession session){
        //model.addAttribute("campaigns", campaignService.)
        sessionController.addSessionAttributes(session, model);
        return "campaign/list";
    }

    @GetMapping("/form")
    public String form(Model model, HttpSession session){
        logger.info("### form method ###");
        model.addAttribute("categories", categoryService.getAllCategories());
        sessionController.addSessionAttributes(session, model);
        return "campaign/form";
    }

    @PostMapping("/select")
    public String selectCampaign(@RequestParam String startDate, @RequestParam String endDate, Model model, HttpSession session){
        Campaign campaign  = new Campaign();
        campaign.setStartDate(toLocalDate(startDate));
        campaign.setEndDate(toLocalDate(endDate));
        model.addAttribute("campaigns", campaignService.selectCampaigns(campaign));
        logger.info("size: " + campaignService.selectCampaigns(campaign));
        sessionController.addSessionAttributes(session, model);
        return "campaign/list";
    }

    @PostMapping("/")
    public String saveCampaign(@ModelAttribute Campaign campaign,
                               @RequestParam int categoryId,
                               @RequestParam String start,
                               @RequestParam String end,
                               @RequestParam String option1,
                               @RequestParam String option2,
                               @RequestParam String option3,
                               @RequestParam String option4,
                               Model model,
                               HttpSession session
    ){
        logger.info("### saveCampaign method ###");

        campaign.setStartDate(toLocalDate(start));
        campaign.setEndDate(toLocalDate(end));
        campaign.setCategory(categoryService.getCategoryById(categoryId));
        campaign.setOptions(new ArrayList<>());
        Campaign registeredCampaign = campaignService.saveCampaign(campaign);
        addOptionIfNotEmpty(registeredCampaign, option1);
        addOptionIfNotEmpty(registeredCampaign, option2);
        addOptionIfNotEmpty(registeredCampaign, option3);
        addOptionIfNotEmpty(registeredCampaign, option4);
        model.addAttribute("campaign", registeredCampaign);
        sessionController.addSessionAttributes(session, model);
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

    private LocalDate toLocalDate(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date,formatter);
        return localDate;
    }
}
