## ACMEFresh.com API

### API ER Diagram


![ACMEFresh_ERDiagram](https://user-images.githubusercontent.com/93640541/201098460-2e4a2550-285b-4300-ae3c-8c6ae79813da.png)


# Functionalities
### - For Custoemrs
-   register/login/update/delete - The users
-   see/addTocart/proceedToOrder - The Products
-   Find products by category
### - For Admins
-   add/update/delete - The products
-   add/update/delete - The category
-   add/update/delete - The Shippers


## Backend Work
-  Proper Exception Handling
-  Proper Input Validation
-   Data Stored in the database(mySQL)
-   User Authentication of signUp and Login

## Installation and Run
-  You can clone this repo and start the serve on localhost
-   Before running the API server, we should update the database config inside the application.properties file.
-   Update the port number, username and password as per our local database config.
    -   server.port=8888
    -   spring.datasource.url=jdbc:mysql://localhost:3306/acmefreshdb
    -   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    -   spring.datasource.username=root
    -   spring.datasource.password=**YourPassword**
    -   spring.jpa.hibernate.ddl-auto=update

# Tech Stacks

-   Java Core
-   Spring Data JPA
-   Spring Boot
-   Hibernate
-   MySQL
