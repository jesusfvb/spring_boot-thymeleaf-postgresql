# Aplicación con Spring-Boot,Tymeleaf y Spring-Security con autorización mediante una Base de datos de PostgreSQL

## Datos útiles para su despliegue

- Tener Maven, Java y el PostgreSQL instalado
- Conocer como ejecutar una aplicación de Spring Boot. Puede consultar las siguentes documentaciones

  1. [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
  2. [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/maven-plugin/reference/html/)

  3. [Spring Web](https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#boot-features-developing-web-applications)

  4. [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
  5. [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

- Modificar el archivo _application.properties_ para cunfigurar el accseos a la base de datos donde:
  - `spring.datasource.url = jdbc:postgresql://localhost/(Nombre de la Base De Datos del PostgreSQL creada para la aplicación)`
  - `spring.datasource.username = (nombre de usuario del PostgreSQL)`
  - `spring.datasource.password = (Contraseña del Servidor de PostgreSQL)`
- Para acceder una ves ejecutada la aplicación usar como _usuario: admin_ y como _contraseña: 1234_

## Bueno espero que le sirva