package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.greenwin.VLclient.services.CampaignResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicProxy topicProxy;

    @Autowired
    CampaignProxy campaignProxy;

    @Autowired
    CampaignResultsService campaignResultsService;

    @GetMapping("/index")
    public String index(){
        for (Map.Entry<Option, Integer> entry : campaignResultsService.getResults(campaignProxy.getCampaignById(1)).entrySet())
            logger.info("option: " + entry.getKey().getOption() + ": " + entry.getValue());
        return "test/index";
    }

    @GetMapping("/blank")
    public String blank(){
        return "test/blank";
    }
    
    @GetMapping("/buttons")
    public String buttons(){
        return "test/buttons";
    }

    @GetMapping("/cards")
    public String cards(){
        return "test/cards";
    }

    @GetMapping("/forgotPasswords")
    public String forgotPasswords(){
        return "test/forgot-password";
    }
    
}
