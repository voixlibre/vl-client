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

import static org.greenwin.VLclient.values.ValueType.HOME;
import static org.greenwin.VLclient.values.ValueType.SIGN;

@Controller
@RequestMapping(SIGN)
public class SignController {

    @Autowired
    LogInProxy logInProxy;

    @Autowired
    LoginService loginService;

    @Autowired
    SessionController sessionController;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * access to login form
     * @return model
     */
    @GetMapping("/")
    public String signInForm(){

        return "sign/sign";
    }

    /**
     * send user data for checking
     * @param
     * @return
     */
    @PostMapping("/signIn")
    public String signIn(@RequestParam String email1, @RequestParam String password1, Model model, HttpSession session){
        logger.info(getClass() + "### signIn method ###");
        AppUser user = new AppUser();
        user.setEmail(email1);
        user.setPassword(password1);
        AppUser user1 = loginService.signIn(user, session);

        if (user1 != null) {
            successfulAuthentication(session, model, user1);
            return "redirect:" + HOME;
        }else {
            model.addAttribute("message", "L'identification a échoué.");
            sessionController.addSessionAttributes(session, model);
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
        logger.info(getClass() + "### signUp method ###");
        loginService.signUp(user, session);
        successfulAuthentication(session, model, user);
        return HOME;
    }

    /**
     * remove all session attributes
     * @param session
     * @return home page
     */
    @GetMapping("/out")
    public String signOut(HttpSession session){
        logger.info(getClass() + "### signOut method ###");
        sessionController.removeSessionAttributes(session);
        return "redirect:" + HOME;
    }

    /**
     * register user in session and model
     * @param session
     * @param model
     * @param user
     */
    public void successfulAuthentication(HttpSession session, Model model, AppUser user){
        logger.info(getClass() + "### successfulAuthentication method ###");
        model.addAttribute("message", "Merci de vous être authentifié");
        session.setAttribute("user", user);
        sessionController.addSessionAttributes(session, model);
    }


}
