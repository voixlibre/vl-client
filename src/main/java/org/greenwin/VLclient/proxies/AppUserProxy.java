package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.AppUser;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@FeignClient(name = "zuul-server", url = "http://localhost:8640/users/")
@RibbonClient(name = "ms-users")
public interface AppUserProxy {

    @GetMapping(value = "/ms-users/users")
    List<AppUser> getAllUsers();

    @GetMapping("/id/{id}")
    AppUser getUserById(@PathVariable("id") int id);

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AppUser saveUser(@RequestBody AppUser appUser);

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable ("id") int id);

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    AppUser updateUser(@RequestBody AppUser user);
}
