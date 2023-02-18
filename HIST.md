# What did I do?

## Basic spring-boot web project
A simple project with
- a repo with a default in-memory h2 database
- spring-boot actuator endpoints

#### gitlab webhook endpoint

#### thymeleaf UI

## Gitlab in docker
A docker-compose that starts up gitlab

After the basic setup

- change root pwd
- create a demo project
- push this codebase to that project
- setup a webhook for merge-requests that calls the spring-boot app

## Cucumber tests

#### polymorphic
Run the same cucumber scenario with different implementations
- input data
  - directly in the domain repository
  - through the webhook endpoint
- verify result
  - directly in the domain repository
  - through the spring controller
  - from the web UI


