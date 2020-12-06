![alt text](./src/main/resources/static/img/logoHT.png)

# HotelTulip - Human Compilers

Proyecto realizado para la asignatura de Proceso de Software y Calidad. Se trata de una aplicación web para el Hotel Tulip de San Sebastián. 
A través de ella es posible realizar reservas tanto de habitaciones como de salas de reuniones.
Además, se puede consultar la disponibilidad de todas ellas para una fecha concreta. 

## Ejecución de la aplicación
```
mvn [clean | validate | compile | exec:java | test]
```
1. ```mvn clean```: Elimina cualquier archivo creado fuera de la ejecución.

2. ```mvn validate```: Verifica que el proyecto es correcto.

3. ```mvn compile```: Compila y carga el archivo pom.xml.

4. ```mvn exec:java ```: Ejecuta la aplicación.

### Ejecución de los tests

```mvn test```: Ejecuta los tests de la aplicación.

## Funcionalidades HotelTulip

Las funcionalidades principales del hotel son:
* Gestión de usuarios
    * Creación de cuenta
    * Inicio de Sesión
    * Cerrar sesión
    
* Gestión de reservas
    * Creación de reserva de sala
    * Creación de reserva de habitación
    * Consulta de disponibilidad de salas en fechas determinadas
    * Consulta de disponibilidad de habitaciones en fechas determinadas
    * Eliminación de reserva de sala
    * Eliminación de reserva de habitación

* Información
    * Información sobre precios
    * Información sobre tipo de habitaciones y salas
    * Información sobre ubicación y datos de contacto

## Construido con
* [Maven](https://maven.apache.org/) - Gestión de Dependencias
* [SpringBoot](https://spring.io/projects/spring-boot) - 

## Autores
* **Gabriela Garaizabal** (https://github.com/gabrigaraizabal)
* **Maarten Handels** (https://github.com/Maarten30)
* **Laura Llorente** (https://github.com/laullorente17)
* **Ibone Urquiola** (https://github.com/iboneurquiola)



