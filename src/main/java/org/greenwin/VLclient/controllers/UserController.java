package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.proxies.AppUserProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserProxy appUserProxy;

    @Autowired
    SessionController sessionController;

    @GetMapping("/list")
    public String list(Model model){
        logger.info(getClass() + "### list method ###");
        model.addAttribute("users", appUserProxy.getAllUsers());
        return "users/list";
    }

    @GetMapping("/account")
    public String userAccount(Model model, HttpSession session){
        logger.info(getClass() + "### userAccount method ###");
        sessionController.addSessionAttributes(session, model);
        return "users/account";
    }
}
