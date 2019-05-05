package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.proxies.CampaignProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    CampaignProxy campaignProxy;

    @RequestMapping("/id/{id}")
    public String campaignDescription(@PathVariable ("id") int id, Model model){
        //TODO: vérifier que l'utilisateur est identifié
        //TODO: vérifier s'il a voté et modifier le code en fonction
        model.addAttribute("campaign", campaignProxy.getCampaignById(id));
        return "campaign/description";
    }
}
