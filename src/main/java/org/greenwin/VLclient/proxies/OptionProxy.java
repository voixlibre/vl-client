package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.Option;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "ms-campaign", url = "http://localhost:8642/options")
public interface OptionProxy {

    @GetMapping("/id/{id}")
    Option getOptionById(@PathVariable("id") int id);

    @PostMapping("/")
    Option saveOption(@RequestBody Option option);
}
