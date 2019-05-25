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

import static org.greenwin.VLclient.values.ValueType.TOPICS;

@Controller
@RequestMapping(TOPICS)
public class TopicController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TopicProxy topicProxy;

    @Autowired
    private SessionController sessionController;

    @GetMapping("/")
    public String topics(Model model, HttpSession session){
        logger.info("### topics method ###");
        model.addAttribute("topics", topicProxy.getTopics());
        sessionController.addSessionAttributes(session, model);
        return "topics/list";
    }

    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable int id, Model model, HttpSession session){
        logger.info("### getTopicById method ###");
        sessionController.addSessionAttributes(session, model);
        return topicProxy.getTopicById(id);
    }

    @PostMapping("/")
    public Topic saveTopic(@ModelAttribute Topic topic, Model model, HttpSession session){
        sessionController.addSessionAttributes(session, model);
        logger.info("### saveTopic method ###");
        return topicProxy.saveTopic(topic);
    }

}
