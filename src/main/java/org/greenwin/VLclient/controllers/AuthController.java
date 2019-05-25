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

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthProxy authProxy;

    @GetMapping("/")
    public String authForm(){
        return "auth/authForm";
    }

    @PostMapping("/")
    public String auth(@ModelAttribute UserAuthentication authentication, Model model){

        Logger logger = LoggerFactory.getLogger(this.getClass());

            authProxy.login(authentication);

        return "auth/auth-headers";
    }
}
