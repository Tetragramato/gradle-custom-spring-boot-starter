package com.tetragramato.autoconfigure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author vivienbrissat
 * Date: 2019-07-25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomPropertiesTest {

    @Autowired
    private Validator validator;

    @Configuration
    static class ConfigCustomPropertiesTest {

        @Bean
        public Validator validator() {
            return new LocalValidatorFactoryBean();
        }

    }

    @Test
    public void propertiesWithoutEnabledNOK() {

        //Given
        CustomProperties properties = new CustomProperties();

        //When
        Set<ConstraintViolation<CustomProperties>> violations = validator.validate(properties);

        //Then
        assertThat(violations.isEmpty()).isFalse();
        assertThat(violations.size()).isEqualTo(2);
        assertThat(violations).extracting("getMessageTemplate").contains("{javax.validation.constraints.NotNull.message}",
                                                                         "{javax.validation.constraints.CustomConfiguration.message}");

    }

    @Test
    public void propertiesWithHeadersOK(){

        //Given
        CustomProperties properties = new CustomProperties();
        properties.setEnabled(true);
        properties.setHeaders(Collections.singletonMap("toto", "toto"));

        //When
        Set<ConstraintViolation<CustomProperties>> violations = validator.validate(properties);

        //Then
        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void propertiesWithEnabledFalseOK(){

        //Given
        CustomProperties properties = new CustomProperties();
        properties.setEnabled(false);

        //When
        Set<ConstraintViolation<CustomProperties>> violations = validator.validate(properties);

        //Then
        assertThat(violations.isEmpty()).isTrue();
    }

    @Test
    public void propertiesWithEnabledFalseAndHeadersOK(){

        //Given
        CustomProperties properties = new CustomProperties();
        properties.setEnabled(false);
        properties.setHeaders(Collections.singletonMap("toto", "toto"));

        //When
        Set<ConstraintViolation<CustomProperties>> violations = validator.validate(properties);

        //Then
        assertThat(violations.isEmpty()).isTrue();
    }
}
