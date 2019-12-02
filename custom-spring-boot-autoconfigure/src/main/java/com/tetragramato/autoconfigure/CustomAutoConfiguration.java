package com.tetragramato.autoconfigure;

import com.tetragramato.resttemplate.CustomRestTemplateCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Custom configuration for custom RestTemplate.
 *
 * @author vivienbrissat
 * Date: 27/08/2019
 */
@Configuration
@EnableConfigurationProperties(CustomProperties.class)
@ConditionalOnProperty(
        value="tetragramato.http.enabled",
        havingValue = "true")
public class CustomAutoConfiguration {

    private CustomProperties customProperties;

    public CustomAutoConfiguration(final CustomProperties customProperties) {
        this.customProperties = customProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public RestTemplate restTemplate(){
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder(customRestTemplateCustomizer());
        return restTemplateBuilder.build();
    }

    @Bean
    @ConditionalOnMissingBean
    public CustomRestTemplateCustomizer customRestTemplateCustomizer(){
        return new CustomRestTemplateCustomizer(customProperties.getHeaders());
    }

}
