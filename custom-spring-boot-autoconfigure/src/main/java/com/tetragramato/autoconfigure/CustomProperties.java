package com.tetragramato.autoconfigure;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Properties for auto configuration.
 *
 * @author vivienbrissat
 * Date: 27/08/2019
 */
@Validated
@Configuration
@ConfigurationProperties(prefix = "tetragramato.http")
public class CustomProperties {

    @NotNull
    private Boolean enabled;

    private Map<String, String> headers;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(final Map<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Custom JSR 303 to be sure to have "headers" if enabled = true.
     *
     * @return true if OK
     */
    @AssertTrue(message = "{javax.validation.constraints.CustomConfiguration.message}")
    public boolean isCustomConfigurationOK() {
        if (enabled != null) {
            return !enabled || (MapUtils.isNotEmpty(headers));
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("enabled", enabled)
                .append("headers", headers)
                .toString();
    }
}
