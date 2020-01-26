#

####Dynamo DB local
- docker run -p 8000:8000 amazon/dynamodb-local
- docker run --name motometer-local -e POSTGRES_PASSWORD=secret -p 5432:5432 -d postgres:11 
 