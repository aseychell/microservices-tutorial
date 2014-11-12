h1. Microservices Example

h2. Setup

1. Install Java JDK (1.7 or above) http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
2. Install Apache Maven (3.0 or above) http://maven.apache.org/
3. Install an IDE of your choice for Java (preferably Eclipse)
4. Install Erlang and RabbitMQ (http://www.rabbitmq.com/)
5. Install Chrome and the Postman plugin (for testing Rest services)
6. Run "mvn clean install" in command prompt inside all services
7. Run "mvn spring-boot:run" to startup a service

h2. Service Addresses

User Registration : http://localhost:18080
/register

User Management : http://localhost:48080
/addUser

Email Service : http://localhost:28080
Through messageQueue : uom-users

Overall Health Service : http://localhost:38080
/all
