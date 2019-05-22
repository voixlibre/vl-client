package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.services.CampaignResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CampaignProxy campaignProxy;

    @Autowired
    CampaignResultsService campaignResultsService;

    @GetMapping("/index")
    public String index(){

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

    @GetMapping("/template")
    public String template(){
        return "template";
    }

    /*
    @RequestMapping(value="/seedstartermng", params={"addRow"})
    public String addRow(final SeedStarter seedStarter, final BindingResult bindingResult) {
        seedStarter.getRows().add(new Row());
        return "seedstartermng";
    }

    @RequestMapping(value="/seedstartermng", params={"removeRow"})
    public String removeRow(
            final SeedStarter seedStarter, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        seedStarter.getRows().remove(rowId.intValue());
        return "seedstartermng";
    }
    */
}
