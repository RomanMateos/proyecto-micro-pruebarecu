# 🚀 Sistema de Microservicios: Gestión de Préstamos de Biblioteca

Este proyecto consiste en un ecosistema distribuido de microservicios desarrollado con **Spring Boot 3.5.x** y **Java 17**, diseñado para gestionar de forma desacoplada el ciclo de préstamos de libros en una biblioteca escolar. Cada microservicio es dueño absoluto de sus datos, implementando el patrón de **Base de Datos por Microservicio** mediante esquemas independientes en MySQL (Laragon).

## 📐 Arquitectura de la Solución

Los componentes se comunican entre sí de manera síncrona utilizando **Spring Cloud OpenFeign**:

* **`ms-libro` (Puerto 8091):** Administra el catálogo de libros, su paginación, fechas de publicación y estado de disponibilidad. Persiste en la base de datos `db_libro`.
* **`ms-autor` (Puerto 8092):** Gestiona los registros de los autores de la biblioteca. Persiste en la base de datos `db_autor`.
* **`ms-prestamo` (Puerto 8093):** Orquesta la lógica central del negocio. Consume los datos de los servicios anteriores mediante Feign Clients para validar las existencias y asociar el nombre del alumno con el libro solicitado. Persiste en la base de datos `db_prestamo`.

---

## 🛠️ Tecnologías Utilizadas

* **Java 17** (LTS)
* **Spring Boot 3.5.15**
* **Spring Data JPA / Hibernate** (Capa de persistencia)
* **Spring Cloud OpenFeign** (Comunicación inter-servicio)
* **MySQL 8.x / 8.4** (Motor de base de datos de Laragon)
* **Lombok** (Generación automática de código)
* **Mapper Manual** (Conversión manual entre Entidad y DTO)
* **Maven** (Gestor de dependencias)

---

## 🗄️ Requisitos y Configuración de Base de Datos

El sistema requiere que el servidor MySQL de Laragon esté activo. Es obligatorio que existan los esquemas limpios e independientes en tu HeidiSQL:

```sql
CREATE DATABASE db_libro;
CREATE DATABASE db_autor;
CREATE DATABASE db_prestamo;

```
---

## 🚀 Pasos para Iniciar el Ecosistema

Para asegurar la correcta resolución de las peticiones cruzadas a través de **Feign Clients**, debes levantar los proyectos en este orden secuencial dentro de **IntelliJ IDEA**:

1. **Levantar `ms-libro` (Puerto 8091):**
   * Abre el proyecto/módulo, localiza la clase principal **`LibroApplication.java`** y dale a **Run**.
   * Espera a que la consola confirme que está corriendo en el puerto **8091**.

2. **Levantar `ms-autor` (Puerto 8092):**
   * Abre el módulo, localiza la clase principal **`AutorApplication.java`** y dale a **Run**.
   * Espera a que la consola confirme su ejecución en el puerto **8092**.

3. **Levantar `ms-prestamo` (Puerto 8093):**
   * Abre el módulo, localiza la clase principal **`PrestamoApplication.java`** y dale a **Run**.
   * **Nota:** Este servicio cuenta con un componente **`CommandLineRunner` (`PrestamoRunner`)** que insertará de forma automática 3 préstamos iniciales de prueba en la tabla si detecta que la base de datos `db_prestamo` se encuentra vacía.

---

## 🧪 Catálogo de Endpoints (Pruebas en Postman)

Una vez que los tres entornos estén corriendo en paralelo, puedes usar estas configuraciones en **Postman** para validar los flujos de la API:

### 📖 Microservicio de Libros (Puerto 8091)
* **Listar Catálogo Completo:**
  * **Método:** `GET`
  * **URL:** `http://localhost:8091/api/libros`
* **Registrar Nuevo Libro:**
  * **Método:** `POST`
  * **URL:** `http://localhost:8091/api/libros`
  * **Body (`json`):**
    ```json
    {
        "titulo": "Cien años de soledad",
        "paginas": 496,
        "fechaPublicacion": "1967-05-30",
        "disponible": true
    }
    ```

### ✍️ Microservicio de Autores (Puerto 8092)
* **Listar Todos los Autores:**
  * **Método:** `GET`
  * **URL:** `http://localhost:8092/api/autores`

### 💳 Microservicio de Préstamos (Puerto 8093)
* **Listar Historial de Préstamos:**
  * **Método:** `GET`
  * **URL:** `http://localhost:8093/api/prestamos`
* **Registrar un Nuevo Préstamo (Orquestado):**
  * **Método:** `POST`
  * **URL:** `http://localhost:8093/api/prestamos`
  * **Body (`json`):**
    ```json
    {
        "nombreAlumno": "Roman Mateos",
        "libroId": 1,
        "autorId": 1,
        "fechaPrestamo": "2026-06-14",
        "diasPrestamo": 7,
        "activo": true
    }
    ```
