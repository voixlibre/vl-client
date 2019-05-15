package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.proxies.LogInProxy;
import org.greenwin.VLclient.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/sign")
public class SignController {

    @Autowired
    LogInProxy logInProxy;

    @Autowired
    LoginService loginService;

    @Autowired
    SessionController sessionController;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * access to to login form
     * @return model
     */
    @GetMapping("/")
    public String signInForm(){
        logger.info("signInForm");
        return "sign/sign";
    }

    /**
     * sends user data for checking
     * @param
     * @return
     */
    @PostMapping("/signIn")
    public String signIn(@RequestParam String email1, @RequestParam String password1, Model model, HttpSession session){

        logger.info("email:" + email1);
        logger.info("password: " + password1);
        AppUser user = new AppUser();
        user.setEmail(email1);
        user.setPassword(password1);
        AppUser user1 = loginService.signIn(user);

        if (user1 != null) {
            model.addAttribute("message", "Merci de vous être authentifié");
            session.setAttribute("user", user1);
            sessionController.addSessionAttributes(session, model);
            return "home";
        }else {
            model.addAttribute("message", "L'identification a échoué.");
            return "sign/sign";
        }

    }

    /**
     *
     * @param user
     * @return
     */
    @PostMapping("/signUp")
    public String signUp(@ModelAttribute AppUser user, HttpSession session, Model model){


        return "sign/success";
    }
}
