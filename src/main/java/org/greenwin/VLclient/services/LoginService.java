package org.greenwin.VLclient.services;

import feign.FeignException;
import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.proxies.AppUserProxy;
import org.greenwin.VLclient.proxies.LogInProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {

    @Autowired
    LogInProxy logInProxy;

    @Autowired
    AppUserProxy userProxy;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * checks if a user is already registered in the session
     * @param session
     * @return true or false
     */
    public boolean UserAlreadyConnected(HttpSession session){
        AppUser user;
        try{
            user = (AppUser)session.getAttribute("user");
        }catch (NullPointerException e){
            logger.info("No user in session.");
            return false;
        }
        logger.info("A user is already connected.");
        return true;
    }



    public AppUser signIn(AppUser user){

        AppUser user1 = logInProxy.login(user);
        //TODO: cas où l'utilisateur n'existe pas

        //TODO: cas où pas de connexion avec le proxy
        //TODO: cas où un utilisateur est déjà enregistré

        return user1;
    }

}
