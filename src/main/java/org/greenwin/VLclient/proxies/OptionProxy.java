package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Option;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "ms-campaign", url = "http://localhost:8642/options")
public interface OptionProxy {

    @GetMapping("/id/{id}")
    Option getOptionById(@PathVariable("id") int id);
}
