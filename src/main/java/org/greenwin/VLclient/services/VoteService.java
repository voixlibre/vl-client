package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.beans.Vote;
import org.greenwin.VLclient.exception.CampaignNotValidException;
import org.greenwin.VLclient.exception.UserNotAuthenticatedException;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.VoteProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Service
public class VoteService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VoteProxy voteProxy;

    @Autowired
    private CampaignProxy campaignProxy;

    public Vote saveVote(int campaignId, Option option, HttpSession session) {
        logger.info(getClass() + "### saveVote method ###");
        AppUser user = (AppUser) session.getAttribute("user");
        Campaign campaign = campaignProxy.getCampaignById(campaignId);

        if (campaign.getStartDate().isAfter(LocalDate.now()) || campaign.getEndDate().isBefore(LocalDate.now()))
            throw new CampaignNotValidException();

        if (user == null)
            throw new UserNotAuthenticatedException();

        Vote vote = new Vote();
        vote.setCampaign(campaign);
        vote.setOption(option);
        vote.setUserId(user.getId());

        return voteProxy.saveVote(vote);
    }


}
