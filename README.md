# Sistema Web de Gestión de Pedidos - Coqui Café

Aplicación web desarrollada para la gestión de pedidos de la pastelería/cafetería **Coqui Café**.

El sistema permite iniciar sesión, registrar pedidos, visualizar información, editar registros y eliminar datos mediante una interfaz web responsiva.

---

## Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Thymeleaf
- Bootstrap
- SQL Server
- Maven

---

## Requisitos previos

Antes de ejecutar el proyecto, se debe tener instalado:

- Java JDK
- Git
- SQL Server
- SQL Server Management Studio (SSMS)
- Visual Studio Code, IntelliJ IDEA o Spring Tool Suite

---

## Despliegue en entorno local

### 1. Clonar el repositorio

Abrir una terminal y ejecutar:

```bash
git clone https://github.com/El-master-99/G7_EF_MarWeb_Gestion_de_Pedidos_Coqui_Cafe.git
```

Ingresar a la carpeta del proyecto:

```bash
cd G7_EF_MarWeb_Gestion_de_Pedidos_Coqui_Cafe
```

---

### 2. Crear la base de datos

El proyecto trabaja con **SQL Server**.

Abrir **SQL Server Management Studio (SSMS)** y ejecutar el script SQL ubicado en el proyecto.

El script crea la base de datos:

```sql
CREATE DATABASE CoquiCafe;
```

También crea las tablas principales:

- `Tarea`
- `Usuario`
- `Acceso`

Además, inserta datos de prueba para validar el funcionamiento del sistema.

---

### 3. Configurar la conexión a SQL Server

Editar el archivo:

```text
src/main/resources/application.properties
```

Configurar la conexión local a SQL Server:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=CoquiCafe;encrypt=true;trustServerCertificate=true
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
```

> Nota: No subir credenciales reales al repositorio.

---

### 4. Ejecutar la aplicación

En Windows:

```bash
.\mvnw.cmd spring-boot:run
```

También puede ejecutarse con Maven instalado:

```bash
mvn spring-boot:run
```

---

### 5. Abrir la aplicación

Ingresar desde el navegador a:

```text
http://localhost:8080
```

---

## Usuario de prueba

```text
Usuario: kmarcos
Contraseña: 123456
```

---

## Funcionalidades principales

- Inicio de sesión de usuarios.
- Registro de pedidos.
- Listado de pedidos.
- Edición de registros.
- Eliminación de registros.
- Gestión de datos con SQL Server.
- Interfaz responsiva con Bootstrap.
- Integración con Thymeleaf.
- Persistencia de datos con Spring Data JPA.

---

## Base de datos

El sistema utiliza una base de datos llamada:

```text
CoquiCafe
```

Tablas principales:

### Tarea

Almacena los pedidos o tareas de producción.

Campos principales:

- id
- titulo
- descripcion
- fecha_entrega
- prioridad
- estado

### Usuario

Almacena la información de los usuarios del sistema.

Campos principales:

- id
- nombre
- correo
- rol
- fecha_creacion
- estado

### Acceso

Almacena las credenciales de acceso al sistema.

Campos principales:

- id
- nombre_usuario
- contrasena
- correo
- usuario_id

---

## Seguridad

El proyecto utiliza **Spring Security** para el control de acceso.

La aplicación cuenta con autenticación mediante usuario y contraseña.  
Solo los usuarios registrados en la base de datos pueden acceder al sistema.

---

## Estructura general del proyecto

```text
src/main/java
 ├── controller
 ├── model / entity
 ├── repository
 ├── service
 └── security

src/main/resources
 ├── templates
 ├── static
 └── application.properties
```

---

## Comandos útiles

Verificar estado de Git:

```bash
git status
```

Ejecutar proyecto:

```bash
.\mvnw.cmd spring-boot:run
```

Subir cambios a GitHub:

```bash
git add .
git commit -m "Actualizar proyecto"
git push
```

---

## Integrantes

- CASTILLO LLIHUA Luis Daniel
- JEREMIAS AREVALO Diana Carolina
- MARCOS VICENTE Kevin Daniel
- LAGOS MORALES Fernando Valentín
- CASAS MONTENEGRO Javier