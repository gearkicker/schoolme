# School Me
School Me is a Java REST api that conatins two end points.
1. Search
    * Search for students by their name(first, last, or both). The following fileds are returned:
       * First Name
       * Last Name
       * GPA - Student's Grade Point Average
2. Student detail
    * Return details about a student based on the student id. The following fields are returned:
       * First Name
       * Last Name
       * Email Addess
       * GPA - Student's Grade Point Average
       * List of classes with a grade for each class

This application utlizes the following projects:
* Jetty 9.4 A javax.servlet container https://www.eclipse.org/jetty/
* Jersey 2.27 As a JAX-RS complient RESTful Web Services framework. https://jersey.github.io
* Swagger 2.06 for API documentation supporting Open API 3.02 https://swagger.io
* JUnit 4.122 Unit testing Framework https://junit.org/junit4/
* logback 1.2.3  A logging framework https://logback.qos.ch

## Usage
Clone the code from the link above
```
git clone https://github.com/gearkicker/schoolme.git
```
Build the project which also runs the unit tests
```
mvn clean package
```
Run the server
```
java -jar target/schoolme-1.0.jar
```
The server runs embedded in a fat jar so it doesnt't have to be deployed into a servlet container. The server listens on port 8080 upon start up. 
## Testing the API
The data for the API is stored in the students_classes.json file and is loaded when the server starts up. Navigate to the following endpoints to test them:
```
http://localhost:8080/api/v1/student/1
http://localhost:8080/api/v1/student/search?last=smith
http://localhost:8080/api/v1/student/
http://localhost:8080/api/v1/openapi.json
```
Using curl to hit the endpoints:
```
curl 'http://localhost:8080/api/v1/student/search?last=smith'
curl 'http://localhost:8080/api/v1/student/1'

Next results in a NOT FOUND error:
curl 'http://localhost:8080/api/v1/student/d'

Next returns 404 student not found:
curl 'http://localhost:8080/api/v1/student/33'
```
If wou want to make the JSON more readable use `jq` https://stedolan.github.io/jq/
```
For Mac OS X.
sudo brew install jq
```
Using an HTTP header to specify content type Accept:application/json.
```
curl -HAccept:application/json 'http://localhost:8080/api/v1/student/1' | jq 
curl -HAccept:application/json 'http://localhost:8080/api/v1/student/search?last=smith' | jq
curl -HAccept:application/json 'http://localhost:8080/api/v1/student/d' | jq
curl 'http://localhost:8080/api/v1/student/33' | jq
```
Let's look at the data in XML format.
```
curl -HAccept:application/xml 'http://localhost:8080/api/v1/student/1' 
curl -HAccept:application/xml 'http://localhost:8080/api/v1/student/search?last=smith'
curl -HAccept:application/xml 'http://localhost:8080/api/v1/student/33'
```
To look at the open API resouce listing navigate to http://localhost:8080/api/v1/openapi.json
```
json format:
curl 'http://localhost:8080/api/v1/openapi.json' | jq
yaml format:
curl 'http://localhost:8080/api/v1/openapi.yaml'
```
## API Doumentation
To generate javadoc issue the following:
```
mvn javadoc:javadoc
```
Open the html page in this file: `target/site/apidocs/index.html`

