package org.greenwin.VLclient.controllers;

import feign.Request;
import feign.Response;
import org.greenwin.VLclient.beans.UserAuthentication;
import org.greenwin.VLclient.proxies.AuthProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
@RequestMapping("/auth")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
        sessionController.addSessionAttributes(session, model);

        Response response = authProxy.login(authentication);
        logger.info("auth: " + response.headers().get("Authorization"));
        Collection<String> token = response.headers().get("Authorization");
        for (String t : token)
            new Cookie("Authorization", t);



        return "auth/auth-headers";
    }
}
