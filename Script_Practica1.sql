-- Crear la base de datos
CREATE DATABASE practica01;

-- Seleccionar la base de datos
USE practica01;

-- Crear la tabla arbol
CREATE TABLE arbol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    activo BOOLEAN NOT NULL,
    descripcion VARCHAR(255),
    dureza_madera VARCHAR(100),
    nombre_comun VARCHAR(100),
    ruta_imagen VARCHAR(255),
    tipo_flor VARCHAR(100)
);


-- Crear el usuario con contraseña
CREATE USER 'usuario_practica'@'%' IDENTIFIED BY 'la_Clave';

-- Darle permisos sobre la base de datos practica
GRANT ALL PRIVILEGES ON practica01.* TO 'usuario_practica'@'%';

-- Aplicar los cambios de privilegios
FLUSH PRIVILEGES;
