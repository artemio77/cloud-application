package com.gmail.derevets.artem.usermanagementservice.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@RefreshScope
@Component
@ConfigurationProperties(prefix = "security.oauth2.client")
public class ClientOAuthCredential extends ClientCredentialsResourceDetails {

    private String clientId;

    private String clientSecret;

    private String accessTokenUri;

    private String grantType;

    private List<String> scope;
}
