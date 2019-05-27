package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Vote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.awt.*;

import static org.greenwin.VLclient.values.ValueType.*;

@Component
@FeignClient(name = ZUUL_SERVER_NAME, url = ZUUL_LOCALHOST + MS_CAMPAIGN + API_VOTES)
public interface VoteProxy {

    @PostMapping(value = "/")
    Vote saveVote(@RequestBody Vote vote);

    @GetMapping("/user/{userId}/campaign/{campaignId}")
    Vote getVoteByUserAndCampaign(@PathVariable("userId") int userId, @PathVariable("campaignId") int campaignId);
}
