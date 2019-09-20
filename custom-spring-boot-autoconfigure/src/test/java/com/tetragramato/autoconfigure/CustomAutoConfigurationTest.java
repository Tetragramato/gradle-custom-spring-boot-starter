package com.tetragramato.autoconfigure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vivienbrissat
 * Date: 28/08/2019
 */
@RunWith(SpringRunner.class)
public class CustomAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner().withConfiguration(AutoConfigurations.of(
            CustomAutoConfiguration.class));

    @Test
    public void testLoadEnabledFalseOK() {
        this.contextRunner.withPropertyValues("tetragramato.http.enabled=false")
                          .run((context) -> {
                              assertThat(context).hasNotFailed();
                              assertThat(context.getBeanDefinitionNames()).doesNotContain("customAutoConfiguration",
                                                                                          "restTemplate",
                                                                                          "customRestTemplateCustomizer",
                                                                                          "tetragramato.http-com.tetragramato.autoconfigure.CustomProperties");
                          });
    }

    @Test
    public void testLoadNoPropertiesOK() {
        this.contextRunner.run((context) -> {
            assertThat(context).hasNotFailed();
            assertThat(context.getBeanDefinitionNames()).doesNotContain("customAutoConfiguration",
                                                                        "restTemplate",
                                                                        "customRestTemplateCustomizer",
                                                                        "tetragramato.http-com.tetragramato.autoconfigure.CustomProperties");
        });
    }

    @Test
    public void testLoadPropertiesEnabledTrueOK() {
        this.contextRunner.withPropertyValues("tetragramato.http.enabled=true", "tetragramato.http.headers[custom-headers]=blabla")
                          .run((context) -> {
                              assertThat(context).hasNotFailed();
                              assertThat(context.getBeanDefinitionNames()).contains("customAutoConfiguration",
                                                                                    "restTemplate",
                                                                                    "customRestTemplateCustomizer",
                                                                                    "tetragramato.http-com.tetragramato.autoconfigure.CustomProperties");
                          });
    }

    @Test
    public void testLoadPropertiesEnabledFalseOK() {
        this.contextRunner.withPropertyValues("tetragramato.http.enabled=false", "tetragramato.http.headers[custom-headers]=blabla")
                          .run((context) -> {
                              assertThat(context).hasNotFailed();
                              assertThat(context.getBeanDefinitionNames()).doesNotContain("customAutoConfiguration",
                                                                                          "restTemplate",
                                                                                          "customRestTemplateCustomizer",
                                                                                          "tetragramato.http-com.tetragramato.autoconfigure.CustomProperties");
                          });
    }
}