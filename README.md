# Food Vendor App API

[![Build Status](https://travis-ci.org/johnebri/FoodVendorApp.svg?branch=master)](https://travis-ci.org/johnebri/FoodVendorApp)
[![Coverage Status](https://coveralls.io/repos/github/johnebri/FoodVendorApp/badge.svg?branch=master)](https://coveralls.io/github/johnebri/FoodVendorApp?branch=master)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [MySQL](https://https://dev.mysql.com/downloads/installer/)

## Running the application locally
1. Clone this repo into your computer
1. Create a MySQL database "food-vendor-app
3. Open project directory in a terminal
4. Run the command 
  (for Windows users) mvnw spring-boot:run
  (for MacOS/Linux users) ./mvnw spring-boot:run
5. Application is started on http://localhost:8080
6. If port 8080 is already used by another application in your computer, open the application.properties file and change port with 'server.port=8181'

## Running the application online
The application is hosted on https://john-foodvendorapp.herokuapp.com/

## Note
At login, a token is generated that must be used in every other subsequent request. In the Headers tab on postman, add key of Authorization and value of Bearer {token}. 

![Postman](https://res.cloudinary.com/dxi6ee9zf/image/upload/v1590460175/Capture_mdjsbg.jpg)

