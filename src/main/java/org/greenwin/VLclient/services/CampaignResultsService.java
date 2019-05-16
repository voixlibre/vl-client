package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Campaign;
import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.CampaignProxy;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CampaignResultsService {

    @Autowired
    private CampaignProxy campaignProxy;

    @Autowired
    private OptionProxy optionProxy;

    /**
     * get results of one campaign
     * @return
     */
    public Map<Option, Integer> getResults(Campaign campaign){
        Map<Integer, Integer> resultsMap = campaignProxy.getCampaignResults(campaign.getId());
        Map<Option, Integer> convertedMap = convertMap(resultsMap);
        return convertedMap;
    }

    /**
     * convert integer keys of the map of the results fetched via the proxy, into a map <Option, Integer>
     * @param map
     * @return converted map
     */
    private Map<Option, Integer> convertMap(Map<Integer, Integer> map){
        Map<Option, Integer> convertedMap = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            convertedMap.put(optionProxy.getOptionById(entry.getKey()), entry.getValue());
        }
        return convertedMap;
    }
}


