![alt text](img/spring-boot-logo.png)

# POC JAVA11

Proyecto para probar las ultimas tecnologias desde java 8 a 11


## Para empezar

Clonar el proyecto de git o descargar en archivo .zip/.tar.gz y descomprimir


## Prerrequisitos

* JVM 1.11 o superior
* Maven
* Redis en **url** http://localhost **puerto** 6379
* MySql instalado en 3306 con esquema creado segun script *../src/main/resources/create_tables.sql*


## Despliegue

### Localmente con Maven

Para levantar el proyecto solo es necesario ejecutar:
```
mvn clean install spring-boot:run
```

### Desde Jboss o Tomcat

Compilar el proyecto con Maven de la siguiente forma:
```
mvn clean install
```
y luego deployar el **.war** generado dependiendo del server

### Prueba

Realizar un **GET** a http://localhost:8080/alive para recibir un status **200** con body:
```
ALIVE!
```

## Tests

Para correr los test se debe ejecutar el siguiente comando en Maven:
```
mvn test -Dtest=*TestSuite
```
Esto corre todos los test cuyas clases terminen con "TestSuite"


## SonarQube

Para correrlo en SonarQube es necesario ejecutar el siguiente comando **con SonarQube levantado en localhost:9000**
```
mvn sonar:sonar
```


## Configuracion

En la ruta *../src/main/resources/* se encuentran los archivos de properties de spring y la app

