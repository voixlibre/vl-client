package org.greenwin.VLclient.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String home(){
        logger.info("calling home.html");
        return "home";
    }
}
