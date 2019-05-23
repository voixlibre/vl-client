package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.AppUser;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.greenwin.VLclient.values.ValueType.*;

@Component
//@FeignClient(name = "ms-users", url = "http://localhost:8640/users/")
@FeignClient(name = ZUUL_SERVER_NAME, url = ZUUL_LOCALHOST + MS_USERS + API_USERS)
@RibbonClient(name = "auth-center")
public interface LogInProxy {

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AppUser login(@RequestBody AppUser user);

    @GetMapping("/id/{id}")
    AppUser getUserById(@PathVariable("id") int id);

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AppUser saveUser(@RequestBody AppUser appUser);
}
