# Budget Buddy
#### Spring-boot based application to track and manage personal expenses. This application is made up of two REST microservice and one WebService:
1. Authentication
2. ExpenseTracker
3. BudgetBuddy [WebService]

**Authentication** This is a REST microservice, as the name suggests, handle the authentication part. User profile and the tokens for managing the sessions are stored in the Structured DB (SQL Server). [*Spring Data JPA*](https://spring.io/projects/spring-data-jpa) is being used to handle all the operations on DB. The flexibility of using *Spring Data JPA* is that we can use any other structured DB like: OracleDB, IBM DB2, etc. without worrying of making any code level modification.

**ExpenseTracker** This is a REST microservice, which will actually handle the main meaningful data, like the groups, expense details, etc. The data over here will be stored in a NoSQL DB (MongoDB) and the idea is to use [*Spring Data MongoDB*](https://spring.io/projects/spring-data-mongodb) to handle the operation on DB. The advantage remains the same as mentioned above. 

**BudgetBuddy** This is a webservice which will utilize the above two REST microservice to manage and get the data based upon users. 