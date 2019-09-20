package com.tetragramato;

import com.tetragramato.autoconfigure.CustomAutoConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * @author vivienbrissat
 * Date: 19/09/2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CustomAutoConfiguration.class)
@ActiveProfiles("test")
public class ITCustomRestTemplateAutoconfigured {

    private MockRestServiceServer mockServer;

    @Autowired
    RestTemplate restTemplate;

    @Before
    public void setup() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void shouldSendHeaders() throws Exception {

        mockServer.expect(requestTo("http://test"))
                  .andExpect(method(HttpMethod.GET))
                  .andExpect(header("header-toto", "titi"))
                  .andExpect(header("head", "motor"))
                  .andRespond(withSuccess());

        restTemplate.getForEntity("http://test", String.class);

        mockServer.verify();
    }

}
