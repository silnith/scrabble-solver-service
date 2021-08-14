# Scrabble Solver Service

```sh
mvn clean verify site
```

This is a standard WAR file.  The application server must have JAX-RS and CDI
support enabled.  Glassfish and Wildfly have this by default.  Tomcat and Jetty
require additional libraries to be added.

This is built against Java 1.8 using the pre-Jakarta imports.
