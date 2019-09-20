# Gradle Custom Spring Boot starter and Autoconfigure

Example for making custom spring boot Starter and Autoconfigure with `Gradle`.

I choose as example to make a custom configuration around the `RestTemplateBuilder`, for adding new Headers to `RestTemplate` requests. 

You can play with unit tests to see how it works, like the `ITCustomRestTemplateAutoconfigured`.

# How it works

You can import in your project via Gradle the `custom-spring-boot-starter`, and just play with the `RestTemplate` since it's auto configured.

Add in your `application.xml` the following values :

```yaml
tetragramato:
  http:
    enabled: true # or false
    headers: # Add 'key: value' for header
      header-toto: titi
      head: motor
```

The `interceptor` in the `RestTemplate` will intercept all requests, and add each header described in `application.xml` before forward to the destination.

_This little project is here to demonstrate how we can build a custom spring boot starter and autoconfigure with Gradle._