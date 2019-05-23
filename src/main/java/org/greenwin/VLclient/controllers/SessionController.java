package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.proxies.AppUserProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Component
public class SessionController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AppUserProxy appUserProxy;

    @Bean
    public SessionController getSession(){
        return new SessionController();
    }

    public void init(HttpSession session){

    }

    public void addSessionAttributes(HttpSession session, Model model){
        logger.info(getClass() + "### addSessionAttributes method ###");
        try {
            AppUser user = (AppUser) session.getAttribute("user");
            model.addAttribute("user", user);
        }catch (NullPointerException e){
            logger.info("No user logged in.");
        }
    }

    public void removeSessionAttributes(HttpSession session){
        logger.info(getClass() + "### removeSessionAttributes method ###");
        session.removeAttribute("user");
    }




}
