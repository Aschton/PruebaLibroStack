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
- **Funcionalidades:**
  - CRUD completo de libros
  - Validación de campos obligatorios con Bean Validation (@NotBlank, @Min)
  - El stock no puede ser negativo
  - El estado solo acepta los valores NUEVO o USADO
  - Manejo de errores con respuestas HTTP estructuradas

### 2. Microservicio Usuarios
Se encarga de gestionar los usuarios registrados en el sistema.
- **Atributos:** id, nombre, rut, correo
- **Endpoints:** GET, POST, PUT, DELETE `/api/usuarios`
- **Funcionalidades:**
  - CRUD completo de usuarios
  - Validación de formato de correo electrónico
  - Validación de formato de RUT chileno
  - El RUT y correo deben ser únicos en el sistema
  - Manejo de errores con respuestas HTTP estructuradas


### 3. Microservicio Préstamos
Se encarga de gestionar los préstamos de libros. Aparte, se comunica con los otros microservicios mediante Feign Client.
- **Atributos:** id, libroId, usuarioId, fechaPrestamo, estadoPrestamo (ACTIVO/DEVUELTO)
- **Endpoints:** GET, POST, PUT, DELETE `/api/prestamos`
- **Funcionalidades:**
  - CRUD completo de préstamos
  - Verifica que el libro exista consultando microservicio-libros via Feign Client
  - Verifica que el usuario exista consultando microservicio-usuarios via Feign Client
  - La fecha del préstamo se asigna automáticamente al momento de crearlo
  - Logs estructurados con SLF4J en todas las capas

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
  "estado": "NUEVO"
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
