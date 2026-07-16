# ☕ Sistema Web de Gestión de Pedidos - Coqui Café

## 📖 Descripción

Sistema web desarrollado para la gestión de pedidos de la pastelería **Coqui Café**.

La aplicación permite administrar los pedidos de los clientes mediante una interfaz web responsiva, facilitando el registro, consulta, edición y eliminación de información. Asimismo, incorpora autenticación de usuarios y persistencia de datos utilizando Spring Boot y SQL Server.

---

# 🛠 Tecnologías utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Thymeleaf
- Bootstrap 5
- SQL Server
- Maven
- Git

---

# 📋 Requisitos previos

Antes de ejecutar el proyecto es necesario contar con lo siguiente:

- Java JDK 21 o superior
- Apache Maven 3.9 o superior (opcional si se usa Maven Wrapper)
- SQL Server
- SQL Server Management Studio (SSMS)
- Git
- Visual Studio Code, IntelliJ IDEA o Spring Tool Suite (STS)

---

# 📥 Instalación del proyecto

## 1. Clonar el repositorio

```bash
git clone https://github.com/El-master-99/G7_EF_MarWeb_Gestion_de_Pedidos_Coqui_Cafe.git
```

Ingresar al proyecto:

```bash
cd G7_EF_MarWeb_Gestion_de_Pedidos_Coqui_Cafe
```

---

# 🗄 Configuración de la Base de Datos

El proyecto utiliza **SQL Server**.

## 1. Abrir SQL Server Management Studio (SSMS)

Conectarse a la instancia local de SQL Server.

## 2. Ejecutar el script SQL

Dentro del proyecto se encuentra el archivo:

```text
database.sql
```

Ejecutar dicho archivo para crear automáticamente:

- Base de datos **CoquiCafe**
- Tabla **Tarea**
- Tabla **Usuario**
- Tabla **Acceso**
- Datos iniciales de prueba

---

# ⚙ Configuración del proyecto

Editar el archivo:

```text
src/main/resources/application.properties
```

Configurar los datos de conexión:

```properties
spring.datasource.url=jdbc:sqlserver://localhost:1433;databaseName=CoquiCafe;encrypt=true;trustServerCertificate=true
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.SQLServerDialect
```

> **Importante:** No subir credenciales reales al repositorio.

---

# ▶ Compilar el proyecto

Si se utiliza Maven:

```bash
mvn clean install
```

Si se utiliza Maven Wrapper:

```bash
.\mvnw.cmd clean install
```

---

# 🚀 Ejecutar la aplicación

Con Maven Wrapper:

```bash
.\mvnw.cmd spring-boot:run
```

O utilizando Maven:

```bash
mvn spring-boot:run
```

---

# 🌐 Acceso al sistema

Una vez iniciada la aplicación, abrir el navegador e ingresar a:

```
http://localhost:8080
```

---

# 👤 Usuario de prueba

Credenciales incluidas en la base de datos:

```
Usuario: kmarcos
Contraseña: 123456
```

---

# ✨ Funcionalidades principales

- Inicio de sesión de usuarios.
- Gestión de pedidos.
- Registro de nuevos pedidos.
- Edición de pedidos.
- Eliminación de pedidos.
- Visualización de información.
- Persistencia de datos mediante SQL Server.
- Interfaz responsiva desarrollada con Bootstrap.

---

# 📂 Estructura del proyecto

```
src
│
├── main
│   ├── java
│   │   ├── controller
│   │   ├── entity
│   │   ├── repository
│   │   ├── service
│   │   ├── security
│   │   └── config
│   │
│   └── resources
│       ├── static
│       ├── templates
│       └── application.properties
│
└── test
```

---

# 🔐 Seguridad

La aplicación implementa **Spring Security** para el control de acceso.

Solo los usuarios registrados pueden acceder a las funcionalidades del sistema mediante autenticación.

---

# ⚠ Problemas comunes

### Error de conexión con SQL Server

Verificar:

- Que SQL Server esté iniciado.
- Que la base de datos **CoquiCafe** exista.
- Que el usuario y contraseña configurados en `application.properties` sean correctos.

### Puerto ocupado

Si el puerto **8080** está siendo utilizado por otra aplicación, detener el proceso correspondiente o modificar el puerto en la configuración del proyecto.

---

# 👥 Integrantes

- CASTILLO LLIHUA Luis Daniel
- JEREMIAS AREVALO Diana Carolina
- MARCOS VICENTE Kevin Daniel
- LAGOS MORALES Fernando Valentín
- CASAS MONTENEGRO Javier

---

# 📄 Licencia

Proyecto desarrollado con fines académicos para el curso **Marcos de Desarrollo Web**.