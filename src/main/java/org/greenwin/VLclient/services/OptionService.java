package org.greenwin.VLclient.services;

import org.greenwin.VLclient.beans.Option;
import org.greenwin.VLclient.proxies.OptionProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    @Autowired
    private OptionProxy optionProxy;

    public Option saveOption(Option option){
        return optionProxy.saveOption(option);
    }
}
