# Patient_Jee-master


# JEE Web Application for Managing Hospital Patients

This project is a JEE web application designed to manage hospital patients. The application allows hospital staff to create, update and delete patients.


## Technologies

- JEE (Java Enterprise Edition) 
- Spring Framework 
- Hibernate ORM (Object Relational Mapping)
- MySQL Database 
- Bootstrap for front-end design 
- Maven for dependency management 


## Setup

1. Clone this repository using `git clone https://github.com/dohaab2/Patient_Jee-master.git`.
2. Import the project into your IDE (Eclipse, IntelliJ, etc.) as a Maven project.
3. Configure the database connection in the `application.properties` file located in the `src/main/resources` folder.
4. Build and run the application using the `mvn clean package` command to build the WAR file, and then deploying it to a Tomcat server.

## Usage

After starting the application, open a web browser and go to `http://localhost:8080/hospital-management/` to access the login page. The default credentials for the administrator account are:
- Username: admin
- Password: 1234
After logging in, you will be taken to the dashboard where you can manage patients.
