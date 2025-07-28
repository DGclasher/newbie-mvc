# Newbie MVC

A simple SpringBoot REST MVC project. For the dummies.

## Run the database
```angular2html
docker compose -f postgres.yml up -d
```

## Run tests
```angular2html
./mvnw test
```

## Run the application
```angular2html
./mvnw clean package
```
```angular2html
java -jar target/newbie-mvc-0.0.1-SNAPSHOT.jar
```

### [API Docs](https://documenter.getpostman.com/view/24270306/2sB3B7PE8y)