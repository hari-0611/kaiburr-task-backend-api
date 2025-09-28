Kaiburr Task 1: 

Java REST API for Task Management This repository contains the solution for Task 1 of the Kaiburr recruitment assessment. It is a RESTful API built with Java and Spring Boot that allows for creating, searching, deleting, and executing shell command tasks.

🌟 Features CRUD Operations: Full support for Creating, Reading, Updating, and Deleting "task" objects.

Database Integration: Uses MongoDB to persist task data.

Shell Command Execution: An endpoint is provided to execute the shell command associated with a task and store its output.

Custom Search: An endpoint to find tasks by their name.

Secure: Includes basic validation to prevent potentially malicious commands.

🏛️ Architecture Overview This project follows a standard three-tier architecture to ensure a robust and scalable application:

Java Spring Boot (The Application Layer): This is the core of the application. It acts as the "brain," handling all incoming HTTP requests, processing the business logic for each endpoint, and coordinating with the database.

MongoDB (The Data Layer): This is the application's permanent "memory." When a new task is created, the Java application sends it to the MongoDB database for persistent storage. Without the database, all data would be lost when the application stops.

🛠️ Prerequisites To run this application, you will need the following installed on your machine:

Java JDK 17 or higher

Apache Maven 3.9+

Postman (or any other API client for testing)

🚀 How to Run the Application Clone the repository:

Run the Spring Boot application using Maven:

mvn spring-boot:run

The application will start and be accessible at http://localhost:8081.

⚙️ API Endpoints The base URL for the API is http://localhost:8081/tasks.

PUT /tasks

Creates a new task. The task object is passed in the request body.

GET /tasks

Returns a list of all tasks.

GET /tasks?id={id}

Returns a single task matching the provided ID.

GET /tasks/search?name={name}

Finds and returns all tasks whose name contains the search string.

PUT /tasks/{id}/execute

Executes the shell command for the specified task.

DELETE /tasks/{id}

Deletes the task with the specified ID.

📸 Screenshots of API Tests Here are the screenshots from Postman proving that all endpoints work as expected.

1. Create a Task (PUT /tasks)
   <img width="1920" height="1080" alt="create" src="https://github.com/user-attachments/assets/a0e8292b-4fbf-4777-965e-fc4a9968e41a" />

2. Get All Tasks (GET /tasks) 
   <img width="1920" height="1080" alt="get_all_tasks" src="https://github.com/user-attachments/assets/0db7920b-8cc0-459a-ac93-78cc7a255ea4" />

3. Get Task by ID (GET /tasks/101) 
   <img width="1920" height="1080" alt="get_by_id" src="https://github.com/user-attachments/assets/3f0aec5f-37cc-4e27-9968-02c049e0a5a9" />

4. Find Task by Name (GET /tasks/search?name=Current)
   <img width="1920" height="1080" alt="search_by_name" src="https://github.com/user-attachments/assets/fabd5342-7ef2-428a-add3-0797163a689d" />

5. Execute a Task (PUT /tasks/101/execute)
   <img width="1920" height="1080" alt="execute_dir" src="https://github.com/user-attachments/assets/0e8e0465-3a44-4102-a45c-98d74b7997dd" />

6. Delete a Task (DELETE /tasks/101)
   <img width="1920" height="1080" alt="delete" src="https://github.com/user-attachments/assets/e751b3cc-b97b-4a68-a02a-b0bf2d398e93" />

   
