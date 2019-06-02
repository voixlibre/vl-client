package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Category;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.exception.WrongPeriodDefinitionException;
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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

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


    /**
     * display the description of the campaign, and allows the vote only if user is logged in
     * @param id
     * @param model
     * @param session
     * @return
     */
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

    /**
     * display the page with the list of campaigns, and a form to filter them
     * @param model
     * @param session
     * @return
     */
    @GetMapping
    public String allCampaign(Model model, HttpSession session){
        //model.addAttribute("campaigns", campaignService.)
        sessionController.addSessionAttributes(session, model);
        return "campaign/list";
    }

    /**
     * display the form to save a new campaign
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/form")
    public String form(Model model, HttpSession session){
        logger.info("### form method ###");
        model.addAttribute("categories", categoryService.getAllCategories());
        //initialize startDate and endDate
        model.addAttribute("start", LocalDate.now());
        model.addAttribute("end", LocalDate.now());
        sessionController.addSessionAttributes(session, model);
        return "campaign/form";
    }

    @PostMapping("/select")
    public String selectCampaign(@RequestParam String startDate, @RequestParam String endDate, @RequestParam String categoryId,  Model model, HttpSession session){
        Campaign campaign  = new Campaign();
        if(categoryId != "0")
            campaign.setCategory(categoryService.getCategoryById(Integer.parseInt(categoryId)));

        checkSearchValidity(campaign, startDate, endDate, model);


        sessionController.addSessionAttributes(session, model);
        return "campaign/list";
    }

    /**
     * fetch all form fields and make a campaign out of it
     * @param campaign
     * @param categoryId
     * @param start
     * @param end
     * @param option1
     * @param option2
     * @param option3
     * @param option4
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/")
    public String saveCampaign(@ModelAttribute Campaign campaign,
                               @RequestParam String categoryId,
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

        if(categoryId == "0")
            return "campaign/form";

        campaign.setStartDate(toLocalDate(start));
        campaign.setEndDate(toLocalDate(end));
        campaign.setCategory(categoryService.getCategoryById(Integer.parseInt(categoryId)));
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

    /**
     * dispplay the specified campaign, and allows its change
     * @param id
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/edit/{id}")
    public String editCampaign(@PathVariable ("id") int id, Model model, HttpSession session){
        Campaign campaign = campaignService.getCampaignById(id);
        model.addAttribute("campaign", campaign);
        sessionController.addSessionAttributes(session, model);
        return "campaign/edit";
    }

    @PostMapping("/update/{id}")
    public String updateCampaign(@ModelAttribute Campaign campaign, Model model, HttpSession session){
        campaignService.updateCampaign(campaign);
        return "campaign/confirmation";
    }

    /**
     * displays a list of campaigns that contain keyword parameter
     * @param keyword
     * @return html page: "campaign/list"
     */
    @PostMapping("/search")
    public String searchCampaign(@RequestParam String keyword, Model model, HttpSession session){
        List<Campaign> campaigns = campaignService.searchCampaign(keyword);
        model.addAttribute("campaigns", campaigns);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        sessionController.addSessionAttributes(session, model);
        return "campaign/list";
    }

    /**
     * save an option only if not empty
     * @param campaign
     * @param option
     */
    private void addOptionIfNotEmpty(Campaign campaign, String option){
        logger.info("### addOptionIfNotEmpty method ###");
        Option o = new Option(option, campaign);
        logger.info("option: " + option);
        if (option != "") {
            o.setCampaignId(campaign.getId());
            optionService.saveOption(o);
        }
    }

    /**
     * convert a string into a localdate
     * @param date
     * @return
     */
    private LocalDate toLocalDate(String date){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    /**
     * try to set start and end date to the campaign, send error message if start date is after end date,
     * or set minimum start date and maximum end date if category exists.
     * This way, at least one criteria exists for search purpose
     * @param campaign
     * @param startDate
     * @param endDate
     * @param model
     */
    private void checkSearchValidity(Campaign campaign, String startDate, String endDate, Model model){
        try {
            campaign.setStartDate(toLocalDate(startDate));
            campaign.setEndDate(toLocalDate(endDate));
            model.addAttribute("campaigns", campaignService.selectCampaigns(campaign));
        }catch (DateTimeParseException e){
            if(campaign.getCategory() == null)
                model.addAttribute("error", "Veuillez entrer une date valide.");
            else{
                campaign.setStartDate(LocalDate.MIN);
                campaign.setEndDate(LocalDate.MAX);
            }

        }catch (WrongPeriodDefinitionException e){
            model.addAttribute("error", "Veuillez entrer une période valide.");
        }
    }
}
