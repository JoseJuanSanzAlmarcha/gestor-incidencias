# Gestor de Incidencias

API REST desarrollada en Spring Boot para gestionar incidencias de un sistema.

## Tecnologías

- Java 17
- Spring Boot 3.5.13
- Spring Data JPA
- H2 Database (en memoria)
- Maven

## Estructura del proyecto

- model → Entidad Incidencia con enums de estado y prioridad
- epository → Acceso a datos con Spring Data JPA
- service → Lógica de negocio
- controller → Endpoints REST

## Cómo ejecutar

1. Clonar el repositorio
   git clone https://github.com/JoseJuanSanzAlmarcha/gestor-incidencias.git
2. Entrar al proyecto
   cd gestor-incidencias
3. Ejecutar con Maven
   ./mvnw spring-boot:run
4. La API estará disponible en http://localhost:8080

## Endpoints

| Método | URL | Descripción |
|--------|-----|-------------|
| POST | /incidencias | Crear incidencia |
| GET | /incidencias | Listar todas / filtrar |
| GET | /incidencias/{id} | Obtener por id |
| PUT | /incidencias/{id} | Actualizar |
| DELETE | /incidencias/{id} | Eliminar |

## Filtros disponibles

- Por estado: GET /incidencias?estado=ABIERTO
- Por prioridad: GET /incidencias?prioridad=ALTA
- Por ambos: GET /incidencias?estado=ABIERTO&prioridad=ALTA

## Valores posibles

- Estado: ABIERTO, EN_PROGRESO, CERRADO
- Prioridad: BAJA, MEDIA, ALTA

## Ejemplo de creación

{
    "titulo": "Error en login",
    "descripcion": "No deja acceder a la aplicación",
    "estado": "ABIERTO",
    "prioridad": "ALTA"
}
