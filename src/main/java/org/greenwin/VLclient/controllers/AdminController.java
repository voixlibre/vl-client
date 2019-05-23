package org.greenwin.VLclient.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.greenwin.VLclient.values.ValueType.ADMIN;

@Controller
@RequestMapping(ADMIN)
public class AdminController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * display the admin dashboard
     * @return
     */
    @GetMapping("/dashboard")
    public String getDashboard(){
        logger.info("### getDashboard method ###");
        return "admin/dashboard";
    }

}
