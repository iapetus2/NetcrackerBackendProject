package com.projectparty.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test.jwt")
public class JwtProperties {
    private String jwtSecret;
    private long jwtExpirationMs;


}
