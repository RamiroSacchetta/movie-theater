
🎬 Sistema de Gestión de Películas

Proyecto desarrollado en Java 17 con Spring Boot 3.2 para la gestión de películas, aplicando arquitectura MVC, validaciones y manejo de excepciones.

📌 Tecnologías utilizadas
- Java 17
- Spring Boot 3.2
- Spring Web
- Spring Data JPA
- Bean Validation (Jakarta)
- MySQL
- Maven

⚙️ Estructura del Proyecto
El proyecto sigue una arquitectura por capas:
- controller/
- service/
- repository/
- model/
- validator/
- exception/

✅ Funcionalidades
- Crear una nueva película
- Listar todas las películas
- Buscar película por ID
- Buscar películas por año

🎯 Validaciones

A nivel de entidad (@Entity):
- titulo: no nulo, entre 2 y 100 caracteres
- director: no nulo
- anioLanzamiento: entre 1895 y el año actual + 2
- genero: opcional

A nivel de servicio:
- No se permite registrar películas con el mismo título y director
- No se permite el género "documental" para películas anteriores a 1920

⚠️ Manejo de errores
Las excepciones se gestionan desde un @RestControllerAdvice global que:
- Captura errores de validación (MethodArgumentNotValidException)
- Captura excepciones personalizadas (BusinessException)

📩 Ejemplo de Request/Response

Crear película (POST /peliculas)
Request:
POST /peliculas
Content-Type: application/json

{
    "titulo": "Inception",
    "director": "Christopher Nolan",
    "anioLanzamiento": 2010,
    "genero": "Ciencia Ficción"
}

Response exitosa:
{
    "id": 1,
    "titulo": "Inception",
    "director": "Christopher Nolan",
    "anioLanzamiento": 2010,
    "genero": "Ciencia Ficción"
}

Error (validación):
{
    "timestamp": "2023-11-20T12:00:00",
    "status": 400,
    "error": "Bad Request",
    "message": {
        "anioLanzamiento": "debe ser mayor o igual que 1895"
    }
}

🧪 Tests (opcional)
- Tests unitarios para PeliculaService
- Validación de respuestas HTTP desde el controller

📄 Diagrama de Flujo: POST /peliculas
Cliente --> POST /peliculas --> Controller --> @Valid --> Service --> Validaciones --> Repository --> Guardar --> ResponseEntity

🧰 Cómo ejecutar el proyecto

1. Clonar el repositorio:
git clone https://github.com/usuario/movie-theater.git

2. Configurar base de datos MySQL en application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/peliculas
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

3. Ejecutar la aplicación:
./mvnw spring-boot:run

📁 Estructura de carpetas
src/
├── controller/
├── service/
├── repository/
├── model/
├── validator/
├── exception/
└── main/

👤 Autor
- Ramiro Sacchetta

✅ Estado del proyecto
✔️ Completado
🛠️ En desarrollo de mejoras y tests (opcional)

📦 Ejemplo Postman
POST http://localhost:8080/peliculas
Headers:
    Content-Type: application/json
Body (raw, JSON):
{
    "titulo": "Matrix",
    "director": "Lana Wachowski",
    "anioLanzamiento": 1999,
    "genero": "Acción"
}
