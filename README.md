# Terminaldo

## Description
Terminaldo is a Java application that integrates with MongoDB to provide a server for terminal-based task management system.

## Prerequisites
- Java 11 or higher
- Maven
- MongoDB

## Setup
1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd terminaldo
   ```

2. Install dependencies:
   ```bash
   mvn install
   ```

3. Configure MongoDB in `terminaldo.properties`:
   ```properties
   databaseUrl=mongodb://localhost:27017/yourdbname
   ```

## Running the Application
To run the application, use the following command:
```bash
mvn spring-boot:run
```
## Usage
- Access the application through the terminal.
- Follow the prompts to manage your tasks.

## Contributing
Feel free to submit issues or pull requests for improvements.

## License
This project is licensed under the MIT License.