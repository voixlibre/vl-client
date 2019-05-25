package org.greenwin.VLclient.proxies;

import org.greenwin.VLclient.beans.AppUser;
import org.greenwin.VLclient.beans.UserAuthentication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;

import static org.greenwin.VLclient.values.ValueType.*;

@Component
@FeignClient(name = ZUUL_SERVER_NAME, url = ZUUL_LOCALHOST)
@RibbonClient(name = "ms-auth")
public interface AuthProxy {

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    HttpServletResponse login(UserAuthentication authentication);
}
