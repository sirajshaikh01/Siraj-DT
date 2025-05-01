**Prerequisites**

Java 24 is installed and available on your PATH.

Postman desktop app.

**Setup & Build**

Clone the repository:

Build and run using Maven:

mvn clean spring-boot: run

This compiles the code and launches the service on port 8080/pickSpot. You should see a log message:

Tomcat started on port(s): 8080 (http)

**Testing in Postman**

* **1. Create a new request**

Open Postman, click New → HTTP Request, and save it to a collection (e.g., “PickSpot API”).

Name it Post PickSpot.

* 2. Configure the request

Method: POST

URL: http://localhost:8080/pickSpot


3. Set the request body

Select Body → raw → JSON

Paste the JSON payload:
{
  "container": {
    "id": "C1",
    "size": "small",
    "needsCold": false,
    "x": 1,
    "y": 1
  },
  "yardMap": [
    { "x": 1, "y": 2, "sizeCap": "small", "hasColdUnit": false, "occupied": false },
    { "x": 2, "y": 2, "sizeCap": "big",   "hasColdUnit": true,  "occupied": false }
  ]
}

* 4. Send and verify

Click Send.

On success, you’ll see:

{
  "containerId": "C1",
  "targetX": 2,
  "targetY": 2
}

For the error test, edit the payload so all slots are occupied, then Send again. Expect a 400 Bad Request with:

{"error":"no suitable slot"}

