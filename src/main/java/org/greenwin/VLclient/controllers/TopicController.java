package org.greenwin.VLclient.controllers;

import org.greenwin.VLclient.beans.Topic;
import org.greenwin.VLclient.proxies.TopicProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/topics")
public class TopicController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicProxy topicProxy;

    @Autowired
    private SessionController sessionController;

    @GetMapping("/")
    public String topics(Model model, HttpSession session){
        logger.info("Displaying the list of all topics");
        model.addAttribute("topics", topicProxy.getTopics());
        sessionController.addSessionAttributes(session, model);
        return "topics/list";
    }

    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable int id){
        return topicProxy.getTopicById(id);
    }

    @PostMapping("/")
    public Topic saveTopic(@ModelAttribute Topic topic){
        return topicProxy.saveTopic(topic);
    }

}
