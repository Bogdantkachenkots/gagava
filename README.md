# Mineseeper

A Java application that uses Spring Boot 2, Gradle and Docker

## A multi-stage docker image

```bash
docker build . -t my-app
```

## Run the docker image

```bash
docker run -p 8080:8080 my-app
```

Visit http://localhost:8080 in your browser.
