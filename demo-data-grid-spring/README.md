# Demo Red Hat Data Grid Spring

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
```

<strong>⚠️ Warning:</strong> Este é um bloco de aviso.
<strong>🚨 Alert:</strong> Este é um bloco de alerta.



