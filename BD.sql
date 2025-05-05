--crear bd TechClass

-- Crear la tabla de administradores
CREATE TABLE administrador (
    idAdministrador SERIAL PRIMARY KEY, 
    name VARCHAR(100),
    mail VARCHAR(100),
    clave VARCHAR(60)
);

-- Crear la tabla de profesores
CREATE TABLE profesor (
    idProfesor SERIAL PRIMARY KEY, 
    name VARCHAR(100),
    phone INTEGER,
    mail VARCHAR(100),
    clave VARCHAR(60)
);

-- Crear la tabla de estudiantes
CREATE TABLE estudiante (
    idEstudiante SERIAL PRIMARY KEY,
    name VARCHAR(100),
    dni VARCHAR(8),
    genero VARCHAR(10) CHECK (genero IN ('masculino', 'femenino')),
    address VARCHAR(100),
    mail VARCHAR(100),
    clave VARCHAR(60)
);

-- Crear la tabla de cursos
CREATE TABLE curso (
    idCurso SERIAL PRIMARY KEY,
    foto TEXT,
    nombre VARCHAR(255),
    descripcion TEXT,
    idProfesorCurso INT
);

-- Crear la tabla de contenidos
CREATE TABLE contenido (
    idContenido SERIAL PRIMARY KEY,
    idCurso INT,
    titulo VARCHAR(255),
    descripcion TEXT,
    tipoContenido VARCHAR(50), -- Ejemplo: video, PDF, texto
    urlContenido VARCHAR(255) -- URL del contenido, si es aplicable
);

-- Crear la tabla de asignaciones de cursos (relaciona estudiantes con cursos)
CREATE TABLE asignacion_curso (
    idAsignacion SERIAL PRIMARY KEY,
    idCurso INT,
    idEstudiante INT
);

-- --- Añadir las claves foráneas a las tablas usando ALTER TABLE

-- Agregar la clave foránea en la tabla "curso" para vincularla con "profesor"
ALTER TABLE curso
ADD CONSTRAINT fk_curso_profesor 
FOREIGN KEY (idProfesorCurso) REFERENCES profesor(idProfesor);

-- Agregar la clave foránea en la tabla "contenido" para vincularla con "curso"
ALTER TABLE contenido
ADD CONSTRAINT fk_contenido_curso 
FOREIGN KEY (idCurso) REFERENCES curso(idCurso);

-- Agregar la clave foránea en la tabla "asignacion_curso" para vincularla con "curso"
ALTER TABLE asignacion_curso
ADD CONSTRAINT fk_asignacion_curso 
FOREIGN KEY (idCurso) REFERENCES curso(idCurso);

-- Agregar la clave foránea en la tabla "asignacion_curso" para vincularla con "estudiante"
ALTER TABLE asignacion_curso
ADD CONSTRAINT fk_asignacion_estudiante 
FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante);


-- --- Insertar datos:

--todas las contraseñas son 1234
-- administrador
INSERT INTO administrador (name, mail, clave) 
VALUES 
('admin', 'admin@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Lucas', 'lucas@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Valeria', 'valeria@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Martin', 'martin@correo.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Sofia', 'sofia.admin@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Andres', 'andres@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.');

-- profesor
INSERT INTO profesor (name, phone, mail, clave) 
VALUES 
('Rene', 987456321, 'rene@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Claudia Medina', 998877665, 'claudia.medina@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Carlos Herrera', 912345678, 'carlos.h@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Isabel Torres', 923456789, 'isabel.torres@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Miguel Ángel', 934567890, 'miguel.angel@uni.edu', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.');

-- estudiante
INSERT INTO estudiante (name, dni, genero, address, mail, clave) 
VALUES
('Rodrigo Goes', '78965412', 'masculino', 'Av. Tecnológica', 'estudiante@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Camila Soto', '65478912', 'femenino', 'Calle 5 Sur', 'camila.soto@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Luis Peña', '12345678', 'masculino', 'Av. Principal 123', 'luis.pena@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Andrea Ruiz', '87654321', 'femenino', 'Pasaje Las Flores', 'andrea.ruiz@mail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.'),
('Sebastian Méndez', '45678901', 'masculino', 'Jr. Los Olivos', 'sebastian.m@gmail.com', '$2b$12$0bEJ1sknaO6uIJJT1tV.uOxijq/3LnCFKtswPsw8znWszkncV.jP.');

-- curso
INSERT INTO curso (foto, nombre, descripcion, idProfesorCurso)
VALUES 
('imagenes-guardar/curso-1.jpg', 'Matematicas', '22885-presencia', 1),
('imagenes-guardar/curso-2.jpg', 'Historia', '22883-presencial', 2),
('imagenes-guardar/curso-3.jpg', 'Comunicacion', '22880-virtual', 1),
('imagenes-guardar/curso-4.jpeg', 'Ciencia', '22884-presencia', 3),
('imagenes-guardar/curso-5.png', 'Computacion', '22881-virtual', 1);

--rama ricardo

SELECT * FROM curso;
SELECT*FROM administrador;
SELECT*FROM profesor;
SELECT*FROM estudiante;
SELECT*FROM contenido;



