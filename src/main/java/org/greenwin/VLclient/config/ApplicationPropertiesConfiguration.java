package org.greenwin.VLclient.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("test-config")
@Data @AllArgsConstructor @NoArgsConstructor
public class ApplicationPropertiesConfiguration {

    private String test;



}
