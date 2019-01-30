package com.wenzhan.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "github")
@Data
public class GithubConfig {

    private String GithubID;

    private String repo;

    private String ClientID;

    private String ClientSecret;

}
