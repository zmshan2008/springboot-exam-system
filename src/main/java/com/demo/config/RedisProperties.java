package com.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("redis")
@Data
public class RedisProperties {
    private Integer port;
    private String host;
}
