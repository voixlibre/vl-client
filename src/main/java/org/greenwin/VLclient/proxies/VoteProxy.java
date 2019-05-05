package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Vote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;

@Component
@FeignClient(name = "ms-campaign", url = "http://localhost:8642/votes")
public interface VoteProxy {

    @PostMapping(value = "/")
    Vote saveVote(@RequestBody Vote vote);
}
