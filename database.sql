-- ==========================================
-- CREAR BASE DE DATOS
-- ==========================================

CREATE DATABASE CoquiCafe;
GO

USE CoquiCafe;
GO

-- ==========================================
-- TABLA TAREA
-- ==========================================

CREATE TABLE Tarea (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    titulo VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    fecha_entrega DATE NOT NULL,
    prioridad VARCHAR(20) NOT NULL,
    estado VARCHAR(20) NOT NULL
);
GO

-- ==========================================
-- TABLA USUARIO
-- ==========================================

CREATE TABLE Usuario (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    rol VARCHAR(50) NOT NULL,
    fecha_creacion DATE NOT NULL,
    estado VARCHAR(20) NOT NULL
);
GO

-- ==========================================
-- TABLA ACCESO
-- ==========================================

CREATE TABLE Acceso (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    contrasena VARCHAR(255) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    usuario_id BIGINT NOT NULL,

    CONSTRAINT FK_Acceso_Usuario
    FOREIGN KEY (usuario_id)
    REFERENCES Usuario(id)
);
GO

-- ==========================================
-- DATOS DE PRUEBA PARA TAREA
-- ==========================================

INSERT INTO Tarea
(titulo, descripcion, fecha_entrega, prioridad, estado)
VALUES
('Torta de Chocolate', 'Cumpleaños (20 personas)', '2026-04-15', 'Alta', 'PENDIENTE'),
('50 Cupcakes', 'Evento corporativo', '2026-04-20', 'Media', 'EN_PROGRESO'),
('Tarta de Fresa', 'Boda pequeña', '2026-04-10', 'Baja', 'COMPLETADA'),
('Mini Alfajores Personalizados', 'Fiesta de 15 años', '2026-04-25', 'Baja', 'COMPLETADA'),
('Birretes de Chocolate', 'Fiesta de Promoción', '2026-04-25', 'Media', 'PENDIENTE');
GO

-- ==========================================
-- DATOS DE PRUEBA PARA USUARIO
-- ==========================================

INSERT INTO Usuario
(nombre, correo, rol, fecha_creacion, estado)
VALUES
('CASTILLO LLIHUA Luis', 'luis.castillo@coquicafe.com', 'Gerente General', '2026-03-01', 'Activo'),
('JEREMIAS AREVALO Diana', 'diana.jeremias@coquicafe.com', 'Administradora', '2026-03-01', 'Activo'),
('MARCOS VICENTE Kevin', 'kevin.marcos@coquicafe.com', 'Contador', '2026-03-01', 'Activo'),
('LAGOS MORALES Fernando', 'fernando.lagos@coquicafe.com', 'Supervisor', '2026-03-01', 'Activo'),
('CASAS MONTENEGRO Javier', 'javier.casas@coquicafe.com', 'Gerente de Marketing', '2026-03-01', 'Activo');
GO

-- ==========================================
-- DATOS DE ACCESO AL SISTEMA
-- ==========================================

INSERT INTO Acceso
(nombre_usuario, contrasena, correo, usuario_id)
VALUES
('lcastillo', '123456', 'luis.castillo@coquicafe.com', 1),
('djeremias', '123456', 'diana.jeremias@coquicafe.com', 2),
('kmarcos', '123456', 'kevin.marcos@coquicafe.com', 3),
('flagos', '123456', 'fernando.lagos@coquicafe.com', 4),
('jcasas', '123456', 'javier.casas@coquicafe.com', 5);
GO

-- ==========================================
-- CONSULTAS DE VERIFICACIÓN
-- ==========================================

SELECT * FROM Tarea;
SELECT * FROM Usuario;
SELECT * FROM Acceso;
GO