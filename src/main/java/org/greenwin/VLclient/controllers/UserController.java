package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.proxies.AppUserProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserProxy appUserProxy;

    @GetMapping("/list")
    public String list(Model model){
        logger.info("Displaying the list of all users.");
        model.addAttribute("users", appUserProxy.getAllUsers());
        return "users/list";
    }
}
