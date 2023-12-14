# Pizza-Panel

Pizza-Panel is a project that made for Tel-Ran school. It provides user to create/read/update/delete his cafes and pizzerias! Also making connections between **Meals** and **Pizzerias** objects!

## Technical Stack

- [Java 17](https://docs.oracle.com/en/java/javase/17/docs/api/)
- [Maven](https://maven.apache.org/guides/index.html)
- [Spring 3.1.5](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [MySQL](https://dev.mysql.com/doc/)
- [JPA](https://docs.spring.io/spring-data/jpa/docs/current-SNAPSHOT/reference/html/#reference)
- [Hibernate](https://hibernate.org/orm/documentation/6.4/)
- [Liquidbase](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/springboot/springboot/)
- [Lombok Plugin](https://projectlombok.org/features/)
- [SpringDOC](https://springdoc.org/)
- [Slf4j](https://www.slf4j.org/docs.html)
- [JUnit5](https://junit.org/junit5/docs/current/user-guide/)
- [Jacoco Plugin](https://www.eclemma.org/jacoco/trunk/doc/maven.html)
- [FreeMaker](https://freemarker.apache.org/docs/index.html)
- [JQuery 3.6.4](https://api.jquery.com/)

## Start

1. Download [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17)
2. You need [Maven](https://maven.apache.org/install.html) for build application, I used IntelliJ IDEA as IDE for development that contains [Maven](https://maven.apache.org/install.html)
   Download this repository, `git clone
   https://github.com/sourcels/spring-pizza`
3. Start your MySQL server. You can do it also with Docker-Compose, `docker-compose -up -d`
4. Build and start your project! Run `PizzaApplication.java` (or compile `javac Application.java`)
5. You can access it through port **8080**, of whatever you have in **application.properties** file, access parametr to port configuration `server.port=8080`