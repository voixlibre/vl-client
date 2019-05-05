package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.beans.Vote;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/votes")
public class VoteController {

    @Autowired
    VoteProxy voteProxy;

    @Autowired
    OptionProxy optionProxy;

    @Autowired
    CampaignProxy campaignProxy;


    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/")
    public String vote(@RequestParam int optionId, @RequestParam int campaignId, Model model, HttpSession session){
        //TODO: vérifier que l'utilisateur est identifié
        if(session.getAttribute("user") == null) {
                model.addAttribute("message", "Veuillez s'il vous plait vous authentifier");
                return "sign/sign";
        }

        Vote vote = new Vote();
        vote.setCampaign(campaignProxy.getCampaignById(campaignId));
        vote.setOption(optionProxy.getOptionById(optionId));


        try {
            Vote voteConfirmation = voteProxy.saveVote(vote);

            model.addAttribute("message", "Votre vote a été pris en compte.");
            model.addAttribute("vote", voteConfirmation);
            return "votes/success";
        }catch (Exception e){

            return "votes/failure";
        }
    }
}
