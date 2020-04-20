## tb.lx Backend Challenge  
Backend coding challenge proposed by tb.lx during interview phase  

## About  
The goal of this challenge is to build a web service that exposes Vehicle, Operator and Trace data from Dulin Bus GPS sample data, for a given time frame.  
The service exposes a RESTful API to answer the following questions:  
  
 1. Given a time frame [startTime, endTime], what is the list of running operators?  
 2. Given a time frame [startTime, endTime] and an Operator, what is the list of vehicle IDs?  
 3. Given a time frame [startTime, endTime] and an Operator, which vehicles are at a stop?  
 4. Given a time frame [startTime, endTime] and a vehicle ID, return the trace of that vehicle (GPS entries, ordered by timestamp).  
  
## Technology stack  
 1. Java 11  
 2. Spring Boot  
 3. MongoDB  
 4. Swagger (for generating documentation)  
 5. Mockito (for tests)  
 6. Docker and Docker compose
  
  
## Getting started  
To be able to run this project you must have **docker and docker compose** installed.  
Their installation guide are in:  https://docs.docker.com/install/ and https://docs.docker.com/compose/install/, please visit their websites.
  
## Running this project  
**YOU MUST HAVE DOCKER AND DOCKER COMPOSE INSTALLED**
 1. Clone this repository or download its zip;
 2. Go to the repository folder and run the following command in your terminal:
    - 2.1. `docker-compose up` 
3. This may take few minutes because MongoDB image will import all csv data;
4. Go to http://localhost:8888/swagger-ui.html, there are all requests that this API answers and all information you'll to run your tests;
5. If you want to run the tests, you **must** delete the docker containers for MongoDB using the following command:
   - 5.1 docker rm mongodb
   - 5.2 docker rm mongo_seed

## Project Organization
- The api source code is under the `src` folder;
- In the folder `data` we have the .csv that will be used and its initialization script;
  
## Endpoint answers for the challenge questions  
List of running operators:  
GET /operators?startTime=2012-11-10&endTime=2012-11-11  
  
List of vehicle ids of a given operator:  
GET /vehicles?startTime=2012-11-10&endTime=2012-11-11&operator=PO  
  
List of vehicle ids of a given operator at stop:  
GET /vehicles?startTime=2012-11-10&endTime=2012-11-11&operator=PO&atStop=true  
  
List of traces of a given vehicle id:  
GET /traces?startTime=2012-11-10&endTime=2012-11-11&vehicleId=33438
