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

### get all merge requests
If I do the request for all merge requests http://localhost/api/v4/merge_requests with a *guest access token*, then I always get an empty list.

If I do the request for all merge requests of a specific project http://localhost/api/v4/projects/34/merge_requests, then I get a 403 when I use a *guest access token*.
But when I use a *maintainer access token*, then I get the correct result with all the merge requests.