package org.greenwin.VLclient.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String index(){
        return "test/index";
    }

    @GetMapping("/blank")
    public String blank(){
        return "test/blank";
    }
    
    @GetMapping("/buttons")
    public String buttons(){
        return "test/buttons";
    }

    @GetMapping("/cards")
    public String cards(){
        return "test/cards";
    }

    @GetMapping("/forgotPasswords")
    public String forgotPasswords(){
        return "test/forgot-password";
    }
    
}
