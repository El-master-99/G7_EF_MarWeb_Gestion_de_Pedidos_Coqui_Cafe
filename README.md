# Coqui Café - Sistema de Gestión de Pedidos

Aplicación web para la gestión de pedidos de producción de Coqui Café, desarrollada con Spring Boot, Spring Data JPA, Thymeleaf y Spring Security.

## Tecnologías utilizadas

- **Backend:** Spring Boot 3.3.4 (Java 17)
- **Frontend:** Thymeleaf + Bootstrap
- **Persistencia:** Spring Data JPA (Hibernate)
- **Base de datos:** SQL Server
- **Seguridad:** Spring Security (autenticación por sesión, roles USER/ADMIN)
- **Gestor de dependencias:** Maven

## Requisitos previos

- JDK 17 o superior
- Maven (o usar el wrapper `mvnw` incluido en el proyecto)
- SQL Server (local o remoto) con una instancia accesible en el puerto `1433`
- Un cliente para ejecutar scripts SQL (SQL Server Management Studio, Azure Data Studio, etc.)

## 1. Clonar el repositorio

```bash
git clone <aqui poner la url del repo>
cd <aqui poner el nombre del proyecto>
```

## 2. Crear la base de datos

El script de creación de tablas se encuentra en el archivo `base de datos.txt`, en la raíz del proyecto. Ejecútalo en tu instancia de SQL Server (por ejemplo, desde SQL Server Management Studio) para crear la base de datos `CoquiCafe` junto con las tablas `Tarea`, `Usuario` y `Acceso`.

```sql
-- Ejecutar el contenido completo de "base de datos.txt"
-- Esto crea la base de datos CoquiCafe y sus tablas
```

## 3. Configurar la conexión a la base de datos

Las credenciales de conexión se configuran en el archivo:

```
src/main/resources/application.properties
```

Ajusta los siguientes valores según el entorno local:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=CoquiCafe;encrypt=true;trustServerCertificate=true;
spring.datasource.username=<tu_usuario>
spring.datasource.password=<tu_contraseña>
```

> `spring.jpa.hibernate.ddl-auto=update` está configurado para que Hibernate actualice el esquema automáticamente si detecta cambios en las entidades.

## 4. Ejecutar la aplicación

Desde la raíz del proyecto, usando el wrapper de Maven incluido:

```bash
mvnw.cmd spring-boot:run
```

También puedes compilar y ejecutar el `.jar` generado:

```bash
./mvnw clean package
java -jar target/coquiCafe-0.0.1-SNAPSHOT.jar
```

## 5. Acceder a la aplicación

La aplicación corre por defecto en el puerto **9091**:

```
http://localhost:9091
```

- `/login` — Iniciar sesión
- `/registro` — Registrar un nuevo usuario (rol USER por defecto)
- `/tasks` — Gestión de pedidos (requiere estar autenticado)
- `/admin/usuarios` — Gestión de usuarios (requiere rol ADMIN)

## 6. Usuarios de prueba

El script `base de datos.txt` incluye datos de prueba (usuarios y pedidos) para verificar el funcionamiento del sistema sin necesidad de registrar cuentas nuevas. Revisa el script para conocer las credenciales de acceso insertadas.

## Estructura del proyecto

```
src/main/java/com/grupo07/coquicafe/
├── config/        # Configuración de seguridad (SecurityConfig, manejo de errores de login)
├── controller/     # Controladores MVC (tareas, usuarios, registro)
├── model/          # Entidades JPA (Tarea, Usuario, Acceso, Estado)
├── repository/      # Repositorios Spring Data JPA
└── service/        # Lógica de negocio (servicio de tareas, UserDetailsService)

src/main/resources/
├── templates/       # Vistas Thymeleaf
├── static/Imagenes/  # Recursos estáticos
└── application.properties
```
