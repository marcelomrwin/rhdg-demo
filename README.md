# Data Grid Demo

## Run this demo in the development environment

### Requirements for playing in a local environment
<p>
To reproduce this project in a local environment, the following technologies must be present:
</p>

* JDK 17+
* Apache Maven 3.9+

### Data Grid downloads
Access the [Data Grid Software Downloads](https://access.redhat.com/jbossnetwork/restricted/listSoftware.html?product=data.grid&downloadType=distributions) on the Red Hat customer portal

<strong>ℹ️ Info:</strong> You must have a Red Hat account to access and download Data Grid software.

### Prepare and start the server
```shell
unzip redhat-datagrid-8.4.5-server.zip
cd redhat-datagrid-8.4.5-server/bin
./cli.sh user create admin -p admin -g admin
./server.sh
```

You can access the Red Hat Data Grid administrative console via URL http://localhost:11222/console

<strong>ℹ️ Info:</strong> **PROJECT_ROOT** is an environment variable that represents the project root, the location where the git project was cloned.

### Starting the spring boot version
```shell
cd $PROJECT_ROOT/demo-data-grid-spring
mvn clean spring-boot:run
```

### Starting the quarkus version
```shell
cd $PROJECT_ROOT/demo-data-grid-quarkus
mvn clean quarkus:dev
```

### Testing the demo
<p>
Create an entry through the spring service and observe the reaction of the two applications in the logs
</p>

```shell
curl -X PUT http://localhost:8082/api/cache/1 \
     -H "Content-Type: application/json" \
     -d '{
           "routeId": "1",
           "routeName": "NYC",
           "startLocation": "Main Street",
           "endLocation": "Broadway",
           "operatingHours": "From 8 to 22",
           "busFrequency": 30        
         }'
```

<p>
Create a new record in the cache, this time using the quarkus service. Observe the reaction of services through the logs
</p>

```shell
curl -X PUT http://localhost:8081/api/cache/2 \
     -H "Content-Type: application/json" \
     -d '{
           "routeId": "2",
           "routeName": "VLC",
           "startLocation": "Valencia Center",
           "endLocation": "City of sciences and arts",
           "operatingHours": "From 7 to 23",
           "busFrequency": 5        
         }'
```

<p>
Access the Data Grid console and observe the entries created
</p>

![infinispan console](/docs/imgs/console.png)

<p>
Retrieve the value of register 2 inserted by Quarkus in the Spring service
</p>

```shell
curl -X GET "http://localhost:8082/api/cache/1"
```

<p>
Remove the value of key 1 using the quarkus service
</p>

```shell
curl -X DELETE "http://localhost:8081/api/cache/1"
```

<p>
Check by observing the data grid console that the record whose key is 1 was deleted.
</p>

<p>
With this demo, we present a basic and practical example of how to share data between different applications using a distributed cache such as Red Hat Data Grid.
</p>