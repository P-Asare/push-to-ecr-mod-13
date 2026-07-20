# push-to-ecr-mod-13

Simple Spring Boot REST API demo with two static GET endpoints.

## Endpoints

| Method | Path    | Description                          |
|--------|---------|--------------------------------------|
| GET    | /health | Returns application health status    |
| GET    | /info   | Returns application metadata         |

## Build

```bash
mvn clean package
```

## Run locally

```bash
java -jar target/app.jar
```

```bash
curl http://localhost:8080/health
curl http://localhost:8080/info
```

## Build Docker image

The image is built for `linux/amd64` to ensure compatibility with AWS Fargate.

```bash
docker build -t push-to-ecr-mod-13 .
```

## Run Docker container locally

```bash
docker run --rm -p 8080:8080 push-to-ecr-mod-13
```
