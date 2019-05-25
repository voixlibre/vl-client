package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.UserAuthentication;
import org.greenwin.VLclient.proxies.AuthProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private SessionController sessionController;

    @Autowired
    private AuthProxy authProxy;

    @GetMapping("/")
    public String authForm(HttpSession session, Model model){
        sessionController.addSessionAttributes(session, model);

        return "auth/authForm";
    }

    @PostMapping("/")
    public String auth(@ModelAttribute UserAuthentication authentication, Model model, HttpSession session){

        Logger logger = LoggerFactory.getLogger(this.getClass());
        sessionController.addSessionAttributes(session, model);

            authProxy.login(authentication);

        return "auth/auth-headers";
    }
}