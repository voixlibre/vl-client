package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.beans.Vote;
import org.greenwin.VLclient.exception.CampaignNotValidException;
import org.greenwin.VLclient.exception.UserNotAuthenticatedException;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.greenwin.VLclient.services.VoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.time.LocalDate;

import static org.greenwin.VLclient.values.ValueType.VOTES;

@Controller
@RequestMapping(VOTES)
public class VoteController {

    @Autowired
    private VoteService voteService;

    @Autowired
    private OptionProxy optionProxy;

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    private SessionController sessionController;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/")
    public String vote(@RequestParam int optionId, @RequestParam int campaignId, Model model, HttpSession session){
        logger.info("### vote method ###");
        String message;
        Option option =  optionProxy.getOptionById(optionId);

        try{
            Vote vote = voteService.saveVote(campaignId, option, session);
            message = "Votre vote a été pris en compte.";
            model.addAttribute("vote", vote);
        }catch (UserNotAuthenticatedException e){
            message = "Veuillez s'il vous plait vous authentifier.";
            sessionController.addSessionAttributes(session, model);
            return "sign/sign";
        }catch (CampaignNotValidException e){
            message = "La période de vote n'est pas en cours.";
        }

        model.addAttribute("message", message);
        sessionController.addSessionAttributes(session, model);

        return "votes/results";
    }

}
