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

import static org.greenwin.VLclient.values.ValueType.*;

@Component
//@FeignClient(name = "zuul-server", url = "http://localhost:8652/")
@FeignClient(name = ZUUL_SERVER_NAME, url = ZUUL_LOCALHOST + MS_TOPICS + API_TOPICS)
@RibbonClient(name = "ms-topics")
public interface TopicProxy {

    //@GetMapping("/ms-topics/topics/")
    @GetMapping("/")
    List<Topic> getTopics();

    //@GetMapping("/ms-topics/{id}")
    @GetMapping("/id/{id}")
    Topic getTopicById(@PathVariable("id") int id);

    //@PostMapping("/ms-topics/")
    @PostMapping("/")
    Topic saveTopic(@RequestBody Topic topic);

}
