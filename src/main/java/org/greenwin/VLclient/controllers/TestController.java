package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.proxies.TopicProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicProxy topicProxy;

    @GetMapping("/index")
    public String index(){
        logger.info("test proxy: " + topicProxy.getTopicById(4));
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
