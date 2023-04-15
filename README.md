# Insurance-Management-System
This project uses Spring Web to handle Customer, Policy, Claim and Address information via RESTful web services.

Uses Spring Data JPA for repository support, mysql database server, swagger documentation, exception handling to maintain data integrity and basic authentication

Swagger Documentation at URL - http://localhost:8080/swagger-ui/index.html

## List of endpoints
### Customers
1. GET /v1/customers - Fetch all customers
2. GET /v1/customers/{id} - Fetch a specific customer by ID
3. POST /v1/customers - Create a new customer
4. PUT /v1/clients/{id} - Update a customers's information
5. DELETE /v1/customers/{id} - Delete a customer.

### Policies
1. GET /v1/policies - Fetch all policies
2. GET /v1/policies/{id} - Fetch a specific policy by ID
3. POST /v1/customers/{id}/policies - Create a new policy for a customer
4. PUT /v1/policies/{id} - Update a policies's information
5. DELETE /v1/policies/{id} - Delete a policy.

### Claims
1. GET /v1/claims - Fetch all policies
2. GET /v1/claims/{id} - Fetch a specific claim by ID
3. POST /v1/policies/{id}/claims - Create a new claim for a policy
4. PUT /v1/claims/{id} - Update a claim's information
5. DELETE /v1/claims/{id} - Delete a claim.
