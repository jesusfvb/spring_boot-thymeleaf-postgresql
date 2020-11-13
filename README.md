# Aplicación con Spring-Boot,Tymeleaf y Spring-Security con autorización mediante una Base de datos de PostgreSQL

## Aspectos requeridos para su funcionamiento

- Tener Maven, Java y el PostgreSQL instalado
- Modificar el archivo _application.properties_ para cunfigurar el accseos a la base de datos donde modificara las siguientes propiedades:
  - `spring.datasource.url = jdbc:postgresql://localhost/(Nombre de la Base De Datos del PostgreSQL creada para la aplicación)`
  - `spring.datasource.username = (nombre de usuario del PostgreSQL)`
  - `spring.datasource.password = (Contraseña del Servidor de PostgreSQL)`

### Documentación

- [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
- [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)
- [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/#build-image)
- [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

### Guías

- [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
- [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
- [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
