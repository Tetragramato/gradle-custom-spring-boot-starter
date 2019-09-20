package com.tetragramato.autoconfigure.http;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * Customize the RestTemplate with the custom interceptor.
 *
 * @author vivienbrissat
 * Date: 19/09/2019
 */
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

    private Map<String, String> headers;

    /**
     * Add headers for HeadersInterceptor
     *
     * @param headers Map of headers
     */
    public CustomRestTemplateCustomizer(final Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public void customize(final RestTemplate restTemplate) {
        restTemplate.setInterceptors(Collections.singletonList(new HeadersInterceptor(headers)));
    }
}
