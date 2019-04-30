package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.AppUser;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name = "zuul-server", url = "http://localhost:8652/")
//@RibbonClient(name = "auth-center")
public interface LogInProxy {

    @PostMapping("/login")
    AppUser login(@RequestParam String username, @RequestParam String password);
}
