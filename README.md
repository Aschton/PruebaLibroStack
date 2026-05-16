# Sistema de Gestión de Librería - Microservicios

## Descripción del Proyecto
Sistema de gestión de librería con arquitectura de microservicios usando Spring Boot. Este permite administrar libros, usuarios y préstamos.

## Integrantes del Grupo
- Darliette Loncopan
- Aschton Medolphe

## Microservicios

### 1. Microservicio Libros
Gestiona el catálogo de libros.
- **Atributos:** id, titulo, autor, stock, estado (Nuevo/Usado)
- **Endpoints:** GET, POST, PUT, DELETE `/api/libros`

### 2. Microservicio Usuarios
Se encarga de gestionar los usuarios registrados en el sistema.
- **Atributos:** id, nombre, rut, correo
- **Endpoints:** GET, POST, PUT, DELETE `/api/usuarios`

### 3. Microservicio Préstamos
Se encarga de gestionar los préstamos de libros. Aparte, se comunica con los otros microservicios mediante Feign Client.
- **Atributos:** id, libroId, usuarioId, fechaPrestamo, estadoPrestamo (ACTIVO/DEVUELTO)
- **Endpoints:** GET, POST, PUT, DELETE `/api/prestamos`

## Pasos para Ejecutar

**1. Microservicio Libros**
```bash
cd microservicio-libros
mvn spring-boot:run
```

**2. Microservicio Usuarios**
```bash
cd microservicio-usuarios
mvn spring-boot:run
```

**3. Microservicio Préstamos**
```bash
cd microservicio-prestamos
mvn spring-boot:run
```

## Pruebas con Postman

**Crear un libro:**
```
POST http://localhost:8081/api/libros
{
  "titulo": "El Quijote",
  "autor": "Cervantes",
  "stock": 5,
  "estado": "nuevo"
}
```

**Crear un usuario:**
```
POST http://localhost:8082/api/usuarios
{
  "nombre": "Juan Pérez",
  "rut": "12345678-9",
  "correo": "juan@gmail.com"
}
```

**Crear un préstamo:**
```
POST http://localhost:8083/api/prestamos
{
  "libroId": 1,
  "usuarioId": 1,
  "estadoPrestamo": "ACTIVO"
}
```
