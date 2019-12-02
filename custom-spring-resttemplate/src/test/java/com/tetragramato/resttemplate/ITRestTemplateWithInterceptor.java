package com.tetragramato.resttemplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author Brissat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ITRestTemplateWithInterceptor {

    /**
     * Custom configuration to make a working restTemple.
     */
    @Configuration
    public static class ConfigurationTest {

        Map<String, String> headers = Collections.singletonMap("motor", "head");

        @Bean
        public RestTemplate restTemplate(){
            RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder(customRestTemplateCustomizer());
            return restTemplateBuilder.build();
        }

        @Bean
        public CustomRestTemplateCustomizer customRestTemplateCustomizer(){
            return new CustomRestTemplateCustomizer(headers);
        }

    }

    private MockRestServiceServer mockServer;

    @Autowired
    RestTemplate restTemplate;

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void shouldSendHeaders() {

        mockServer.expect(requestTo("http://test"))
                  .andExpect(method(HttpMethod.GET))
                  .andExpect(header("motor", "head"))
                  .andRespond(withSuccess());

        restTemplate.getForEntity("http://test", String.class);

        mockServer.verify();
    }

}