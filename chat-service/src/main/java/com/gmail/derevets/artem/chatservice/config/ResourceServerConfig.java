package com.gmail.derevets.artem.chatservice.config;


import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    protected ResourceServerConfiguration client1Resources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.tokenServices(tokenServices()).resourceId("resource-server-rest-api");
            }

            private RemoteTokenServices tokenServices() {
                final RemoteTokenServices tokenService = new RemoteTokenServices();
                tokenService.setCheckTokenEndpointUrl("http://localhost:5000/auth-service/oauth/check_token");
                tokenService.setClientId("spring-security-oauth2-read-write-client");
                tokenService.setClientSecret("artem");
                return tokenService;
            }
        }));
        resource.setOrder(5);
        return resource;
    }

 /*   @Bean
    protected ResourceServerConfiguration client2Resources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.tokenServices(tokenServices()).resourceId("chat-management-service");
            }

            private RemoteTokenServices tokenServices() {
                final RemoteTokenServices tokenService = new RemoteTokenServices();
                tokenService.setCheckTokenEndpointUrl("http://localhost:5000/auth-service/oauth/check_token");
                tokenService.setClientId("chat-management-service");
                tokenService.setClientSecret("artem");

                return tokenService;
            }
        }));
        resource.setOrder(4);
        return resource;
    }*/

    @Bean
    protected ResourceServerConfiguration client3Resources() {
        ResourceServerConfiguration resource = new ResourceServerConfiguration() {
            public void setConfigurers(List<ResourceServerConfigurer> configurers) {
                super.setConfigurers(configurers);
            }
        };
        resource.setConfigurers(Collections.singletonList(new ResourceServerConfigurerAdapter() {
            @Override
            public void configure(ResourceServerSecurityConfigurer resources) {
                resources.tokenServices(tokenServices()).resourceId("user-management-service");
            }

            private RemoteTokenServices tokenServices() {
                final RemoteTokenServices tokenService = new RemoteTokenServices();
                tokenService.setCheckTokenEndpointUrl("http://localhost:5000/auth-service/oauth/check_token");
                tokenService.setClientId("user-management-service");
                tokenService.setClientSecret("artem");

                return tokenService;
            }
        }));
        resource.setOrder(4);
        return resource;
    }


}