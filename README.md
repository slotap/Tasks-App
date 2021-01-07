
<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/slotap/Tasks-App">
    <img src="/src/main/resources/static/images/rocket.jpg" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Tasks-App</h3>

  <p align="center">
     Application enables you to create and manage Tasks of your choice
    <br />
    <a href="https://slotap.github.io/">View Demo</a>
  </p>
</p>



<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#setup">Set Up</a></li>
      </ul>
    </li>
    <li><a href="#demo">Demo</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

![Tasks App Screenshot](https://snipboard.io/Oe0c8k.jpg)

Tasks App is a simple CRUD Application allowing user to create, modify, delete Tasks that could be a part of a custom to-do list i.e shopping list, travel preparation list.

Additionally application enables user to communicate with an external API (Trello.com) where created Tasks can be exported to an existing Trello Boards.

Simple Email service was implemented which sends an email to an Admin User whenever new Trello Card have been created as well as scheduled email which sends (once a day) a list of all task currently stored in database.



### Built With

* Gradle 6.6.1[]()
* Spring-Boot 2.3.4[]()
* MySQL[]()
* Hibernate 5.4.21[]()
* JUnit 5.6.2[]()
* Mockito 3.3.3[]()
* Thymeleaf 3.0.11[]()
* Swagger 2.9.2[]()



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/slotap/Tasks-App.git
   ```

### SetUp

In order to run the application following steps must be completed

1. Create MySQL database and alter Configuration class package com.crud.tasks.config.DatasourceConfig.class
   ```sh
        .driverClassName("com.mysql.cj.jdbc.Driver")
        .url("jdbc:mysql://localhost:3306/task_crud?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true")
        .username("user1")
        .password("password")
   ```
2. Alter application.properties with relevant Trello API username,key,token
   ```sh
   trello.api.endpoint.prod=https://api.trello.com/1
   trello.app.key=8462c22116c23468c3c2f396ce7f29f2
   trello.app.token=880dcca27bf3ee1a7ea7d82d6a463a7bb86c401fabad7ee5c85d3dbd59128350
   trello.app.username=user
   ```
3. Alter application.properties with relevant email configuration
   ```sh
    spring.mail.host=smtp.gmail.com
    spring.mail.port=587
    spring.mail.properties.mail.smtp.auth=true
    spring.mail.properties.mail.smtp.starttls.enable=true
    spring.mail.username=${MAIL_USERNAME}
    spring.mail.password=${MAIL_PASSWORD}
    
    admin.name = admin
    admin.mail = admin@gmail.com
   ```

<!-- USAGE EXAMPLES -->
## Demo

Demo application is available at https://slotap.github.io/ .

Backend was deployed on a cloud base platform <a href="https://www.heroku.com/">Heroku</a>.
Frontend was configured using GitHub Pages https://github.com/slotap/slotap.github.io

