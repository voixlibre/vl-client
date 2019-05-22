package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Campaign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Component
@FeignClient(name = "ms-campaign", url = "http://localhost:8642/campaign")
public interface CampaignProxy {

    @PostMapping("/")
    Campaign addCampaign(@RequestBody Campaign campaign);

    @GetMapping("/{id}")
    Campaign getCampaignById(@PathVariable("id") int id);

    @GetMapping("/recent")
    List<Campaign> getMostRecentCampaigns();

    @GetMapping("/results/{id}")
    Map<Integer, Integer> getCampaignResults(@PathVariable ("id") int id);

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Campaign updateCampaign(@RequestBody Campaign c);
}
