package com.tetragramato.autoconfigure.http;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * Intercept requests and add custom headers before forward.
 *
 * @author vivienbrissat
 * Date: 19/09/2019
 */
public class HeadersInterceptor implements ClientHttpRequestInterceptor {

    private Map<String, String> headers;

    public HeadersInterceptor(final Map<String, String> headers) {
        this.headers = headers;
    }

    @Override
    public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution) throws IOException {

        headers.forEach((key, value)->{
            if(!request.getHeaders().containsKey(key)) {
                request.getHeaders().add(key, value);
            }
        });

        return execution.execute(request, body);
    }
}
