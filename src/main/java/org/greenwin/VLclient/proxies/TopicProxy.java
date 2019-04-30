package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Topic;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@FeignClient(name = "zuul-server", url = "http://localhost:8652/")
@RibbonClient(name = "ms-topics")
public interface TopicProxy {

    @GetMapping("/ms-topics/topics/")
    List<Topic> getTopics();

    @GetMapping("/ms-topics/{id}")
    Topic getTopicById(@PathVariable("id") int id);

    @PostMapping("/ms-topics/")
    Topic saveTopic(@RequestBody Topic topic);

}
