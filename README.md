# OTTO spring boot 3 coding challenge 
My solution to the OTTO AWS coding challenge to retrieve and filter ip ranges from AWS resource: https://ip-ranges.amazonaws.com/ip-ranges.json

These were the requirements

## Description

As a Developer I want to see all the ip ranges for selectable regions from AWS ip ranges
in order to create ip filter on security groups.

## Acceptance Criteria

* A Spring Boot Application is created
* The application has a REST controller and lists the expected data
* The datasource is public and readable from here: https://ip-ranges.amazonaws.com/ip-ranges.json
* The REST controller takes URL query parameter like "?region=EU" or "?region=US" or "?region=ALL" to filter the data (valid regions are EU, US, AP, CN, SA, AF, CA)
* The data is presented as MIME type text/plain
* Each value is shown as single line in the output
* The data (from AWS ip-ranges.json) is not stored
* The code should be stored in github

## Hints / Remarks

* Remember to write unit tests for the application.
* Splitting your work in more than one commit is appreciated.
* There is no expectation of creating a security group in AWS
* A pipeline in GitHub Actions is not required but would be a plus.
* Putting the app into a docker container is not required but would be plus.

## Start
Start the spring boot application locally with
```shell
mvn -Plocal spring-boot:run
```
and open
```shell
http://localhost:8080/awsIpRange
```

