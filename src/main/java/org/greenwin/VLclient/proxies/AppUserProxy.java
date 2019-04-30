package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.AppUser;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Component
@FeignClient(name = "zuul-server", url = "http://localhost:8652/")
@RibbonClient(name = "ms-users")
public interface AppUserProxy {

    @GetMapping(value = "/ms-users/users")
    List<AppUser> getAllUsers();
}
