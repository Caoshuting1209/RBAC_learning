package com.shuting.rbac.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "auth")
@Data
public class AuthorizationProperties {
    private List<String> whiteList;
}
