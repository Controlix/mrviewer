### allow gitlab container to access host ip
```yaml
extra_hosts:
  - host.docker.internal:host-gateway
```

### what do the events look like?
see https://docs.gitlab.com/ee/user/project/integrations/webhook_events.html

### wrong feign documentation
The documentation of spring-boot, Baeldung, ... says that you can configure feign with the properties
```yaml
feign:
  client:
    config:
      default:
        loggerLevel: FULL
      gitlab:
```
This is **wrong**; the correct prefix is *spring.cloud.openfeign* instead of *feign*
```yaml
spring:
  cloud:
    openfeign:
      client:
        config:
          default:
            loggerLevel: FULL
```