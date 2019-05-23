package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Vote;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static org.greenwin.VLclient.values.ValueType.VOTES;

@Controller
@RequestMapping(VOTES)
public class VoteController {

    @Autowired
    private VoteProxy voteProxy;

    @Autowired
    private OptionProxy optionProxy;

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    private SessionController sessionController;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/")
    public String vote(@RequestParam int optionId, @RequestParam int campaignId, Model model, HttpSession session){
        logger.info(getClass() + "### vote method ###");
        //TODO: vérifier que l'utilisateur est identifié
        //TODO: vérifie que l'utilisateur n'a pas déjà voté
        //TODO: vérifier que la période de vote est active

        session.setAttribute("user", new AppUser());
        if(session.getAttribute("user") == null) {
                model.addAttribute("message", "Veuillez s'il vous plait vous authentifier");
            sessionController.addSessionAttributes(session, model);
                return "sign/sign";
        }

        Vote vote = new Vote();
        vote.setCampaign(campaignProxy.getCampaignById(campaignId));
        vote.setOption(optionProxy.getOptionById(optionId));
        logger.info("campaign id :" + vote.getCampaign().getId());

        try {
            Vote voteConfirmation = voteProxy.saveVote(vote);
            model.addAttribute("message", "Votre vote a été pris en compte.");
            model.addAttribute("vote", voteConfirmation);
            sessionController.addSessionAttributes(session, model);
            return "votes/results";
        }catch (Exception e){
            sessionController.addSessionAttributes(session, model);
            return "votes/failure";
        }
    }

}
