[ ![Download](https://api.bintray.com/packages/vivienbrissat/gradle-custom-spring-boot-starter/gradle-custom-spring-boot-starter/images/download.svg) ](https://bintray.com/vivienbrissat/gradle-custom-spring-boot-starter/gradle-custom-spring-boot-starter/_latestVersion) [![CircleCI](https://circleci.com/gh/Tetragramato/gradle-custom-spring-boot-starter/tree/master.svg?style=shield)](https://circleci.com/gh/Tetragramato/gradle-custom-spring-boot-starter/tree/master) [![codecov](https://codecov.io/gh/Tetragramato/gradle-custom-spring-boot-starter/branch/master/graph/badge.svg)](https://codecov.io/gh/Tetragramato/gradle-custom-spring-boot-starter)
# Gradle Custom Spring Boot starter and Autoconfigure

Example for making custom spring boot Starter and Autoconfigure with `Gradle`.

I choose as example to make a custom configuration around the `RestTemplateBuilder`, for adding new Headers to `RestTemplate` requests. 

You can play with unit tests to see how it works, like the `ITCustomRestTemplateAutoconfigured`.

# How it works

You can import in your Spring Boot project via Gradle or Maven the `custom-spring-boot-starter`, and just play with the `RestTemplate` since it's auto configured.

## Maven
```xml
<repositories>
   <repository>
       <snapshots>
           <enabled>false</enabled>
       </snapshots>
       <url>https://dl.bintray.com/vivienbrissat/gradle-custom-spring-boot-starter</url>
   </repository>
</repositories>

...

<dependency>
  <groupId>com.tetragramato</groupId>
  <artifactId>custom-spring-boot-starter</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>
```

## Gradle
```groovy

repositories {
     maven {
         url  "https://dl.bintray.com/vivienbrissat/gradle-custom-spring-boot-starter"
     }
 }

...

implementation 'com.tetragramato:custom-spring-boot-starter:1.0.0'
```

Add in your `application.yml` the following values :

```yaml
tetragramato:
  http:
    enabled: true # or false
    headers: # Add 'key: value' for header
      header-toto: titi
      head: motor
```

The `interceptor` in the `RestTemplate` will intercept all requests, and add each header described in `application.yml` before forward to the destination.

_This little project is here to demonstrate how we can build a custom spring boot starter and autoconfigure with Gradle._