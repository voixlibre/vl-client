package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.proxies.LogInProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    LogInProxy logInProxy;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/")
    public String signInForm(){
        logger.info("signInForm");
        return "sign/sign";
    }

    @PostMapping("/")
    public String signIn(@ModelAttribute AppUser user){
        logger.info("in signIn");
        user.toString();

        AppUser user1 = logInProxy.login(user.getEmail(), user.getPassword());
        user1.toString();

        return "sign/success";
    }
}
