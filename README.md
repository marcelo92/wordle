# Wordle

Classic wordle game implemented in API form for testing purposes

## Frontend
React app, cd into frontend-react/ and run with npm

`$ cd frontend-react`

`$ npm install`

`$ npm start`

It'll run on localhost:3000

To build the production code and copy to the main application, run at root:

`$ ./gradlew buildFront`

## Backend

Run with gradle wrapper from root

`$ ./gradlew bootRun`

It'll run on localhost:8080

### Stack

  Main
    
    - Springboot: 2.14
    - Java: 17

  Test
    
    - Junit 5
    - Cucumber: 7

## Docker
To run everything in docker, just build the image with gradle (no Dockerfile needed)

`$ ./gradlew bootBuildImage` 

It'll create a docker image called wordle:latest

Then run:

`$ docker run -d -p 8080:8080 wordle`