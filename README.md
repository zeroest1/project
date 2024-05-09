# Project Name

## Description
This project is a full-stack application using Spring Boot for the backend and React for the frontend. It demonstrates a CRUD application with user authentication and dynamic data handling. The backend is built with Spring Boot, utilizing PostgreSQL as the database, Liquibase for database migration, Lombok to reduce boilerplate code, and MapStruct for object mapping. The frontend is developed with React to provide a responsive user interface.

## Features
- **Spring Boot**: Simplifies backend development with an embedded server and extensive configuration capabilities.
- **PostgreSQL**: Robust, open-source relational database to manage application data.
- **Liquibase**: Manages database schema changes with tracking and rollback capabilities.
- **Lombok**: Reduces boilerplate code in Java applications.
- **MapStruct**: Provides efficient object mapping to automatically convert one object type to another.
- **React**: Creates a dynamic and responsive user interface.

## Prerequisites
Before you begin, ensure you have met the following requirements:
- Java 11 or newer
- Node.js and npm
- PostgreSQL
- Gradle
- Ensure you have Docker and Docker Compose installed on your system.

## Setup and Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/zeroest1/project.git
   cd project
## Backend Setup

To set up the backend of your project, follow these steps:

1. **Running Docker compose**:
   Run Docker Compose from the command line:
   ```bash
   docker-compose up

3. **Build the Project**:
   Build the project using Gradle. You can use the installed version of Gradle or the included Gradle Wrapper. This process will compile the Java code, run any tests, and generate the build artifacts in the `build/` directory.
   ```bash
   ./gradlew build

5. **Run the Spring Boot Application**:
   Start the server using the appropriate Gradle command.
   ```bash
   ./gradlew bootRun


## Frontend Setup

To prepare and run the frontend portion of the project, complete the following steps:

1. **Navigate to the Frontend Directory**:
   Access the frontend directory of the project, which contains all the React application files.
    ```bash
    cd frontend
2. **Install Dependencies**:
   Install all necessary dependencies for the React project.
   ```bash
   npm install

4. **Run the Application**:
   Start the React application to begin serving the frontend.
   ```bash
   npm start

6. **Access the Application**:
   Once the server is running, you can access the application via the local web address.
   [localhohttp://localhost:3000/](http://localhost:3000/)





