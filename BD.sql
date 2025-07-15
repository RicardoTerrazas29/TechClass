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
    tipoContenido TEXT, -- Ejemplo: video, PDF, texto
    urlContenido TEXT -- URL del contenido, si es aplicable
);

-- Crear la tabla de asignaciones de cursos (relaciona estudiantes con cursos)
CREATE TABLE asignacion_curso (
    idAsignacion SERIAL PRIMARY KEY,
    idCurso INT,
    idEstudiante INT
);

CREATE TABLE foro_publicacion (
    idPublicacion SERIAL PRIMARY KEY,
    idEstudiante INT, -- autor del post
    titulo VARCHAR(255),
    contenido TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante)
);

CREATE TABLE foro_comentario (
    idComentario SERIAL PRIMARY KEY,
    idPublicacion INT,
    idEstudiante INT, -- autor del comentario
    comentario TEXT,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (idPublicacion) REFERENCES foro_publicacion(idPublicacion),
    FOREIGN KEY (idEstudiante) REFERENCES estudiante(idEstudiante)
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

INSERT INTO contenido (idCurso, titulo, descripcion, tipoContenido, urlContenido) 
VALUES 
(1, 'Fracciones', 'Explicación sobre fracciones', 'texto', 'https://www.youtube.com/watch?v=s0Od1b1VEpk'),
(2, 'Video: Suma de fracciones', 'Video explicativo', 'video', 'https://www.youtube.com/watch?v=s0Od1b1VEpk'),
(3, 'Infografía de fracciones', 'Imagen con ejemplos', 'imagen', 'https://cde.3.elcomercio.pe/ima/0/1/0/1/4/1014859.jpg'),
(3, 'PDF de ejercicios', 'Descarga el material', 'pdf', 'https://repositorioacademico.upc.edu.pe/bitstream/handle/10757/668190/Aparicio_GC.pdf?sequence=20');

INSERT INTO asignacion_curso (idCurso, idEstudiante) 
VALUES 
(1, 1), (3, 1),
(2, 2), (4, 2);

INSERT INTO foro_publicacion (idEstudiante, titulo, contenido)
VALUES (1, '¿Cómo resolver ecuaciones cuadráticas?', 'Estoy confundido con la fórmula general, ¿alguien puede ayudar?');

INSERT INTO foro_comentario (idPublicacion, idEstudiante, comentario)
VALUES (1, 2, 'Claro, puedo explicarte con un ejemplo paso a paso.');

-- crear tabla de recurso
CREATE TABLE recurso (
    idRecurso SERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    tipoContenido VARCHAR(100),
    url VARCHAR(500),
    idContenido INTEGER,
    CONSTRAINT fk_contenido
        FOREIGN KEY (idContenido)
        REFERENCES contenido(idContenido)
        ON DELETE CASCADE
);


-- crear tabla de logro
CREATE TABLE logro (
    id_logro SERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    icono VARCHAR(255),
    idcurso INTEGER NOT NULL,
    idcontenido INTEGER NOT NULL,
    CONSTRAINT fk_logro_curso FOREIGN KEY (idcurso) 
	REFERENCES curso(idcurso) ON DELETE CASCADE,
    CONSTRAINT fk_logro_contenido FOREIGN KEY (idcontenido) 
	REFERENCES contenido(idcontenido) ON DELETE CASCADE
);

-- crear tabla de logro_estudiante
CREATE TABLE logro_estudiante (
    id SERIAL PRIMARY KEY,
    idestudiante INTEGER NOT NULL,
    idlogro INTEGER NOT NULL,
    CONSTRAINT fk_logroestudiante_estudiante FOREIGN KEY (idestudiante) 
        REFERENCES estudiante(idestudiante) ON DELETE CASCADE,
    CONSTRAINT fk_logroestudiante_logro FOREIGN KEY (idlogro) 
        REFERENCES logro(id_logro) ON DELETE CASCADE
);


CREATE TABLE recurso_revisado (
    id SERIAL PRIMARY KEY,
    id_estudiante INTEGER NOT NULL REFERENCES estudiante(idestudiante) ON DELETE CASCADE,
    id_recurso INTEGER NOT NULL REFERENCES recurso(idrecurso) ON DELETE CASCADE,
    id_contenido INTEGER NOT NULL REFERENCES contenido(idcontenido) ON DELETE CASCADE
);

CREATE TABLE contenido_completado (
    id SERIAL PRIMARY KEY,
    id_estudiante INTEGER NOT NULL REFERENCES estudiante(idestudiante) ON DELETE CASCADE,
    id_contenido INTEGER NOT NULL REFERENCES contenido(idcontenido) ON DELETE CASCADE
);

-- Consultas de prueba
SELECT * FROM curso;
SELECT*FROM administrador;
SELECT*FROM profesor;
SELECT*FROM estudiante;
SELECT * FROM contenido;
SELECT * FROM asignacion_curso;

SELECT e.name AS estudiante, c.nombre AS curso
FROM asignacion_curso ac
JOIN estudiante e ON ac.idEstudiante = e.idEstudiante
JOIN curso c ON ac.idCurso = c.idCurso;

SELECT COUNT(*) AS total_cursos
FROM asignacion_curso
WHERE idEstudiante = 2;

SELECT c.idCurso, c.nombre
FROM asignacion_curso ac
JOIN curso c ON ac.idCurso = c.idCurso
WHERE ac.idEstudiante = 2;

--

SELECT f.idPublicacion, f.titulo, f.contenido, f.fecha, e.name AS autor
FROM foro_publicacion f
JOIN estudiante e ON f.idEstudiante = e.idEstudiante
ORDER BY f.fecha DESC;

SELECT c.idComentario, c.comentario, c.fecha, e.name AS autor
FROM foro_comentario c
JOIN estudiante e ON c.idEstudiante = e.idEstudiante
WHERE c.idPublicacion = 1
ORDER BY c.fecha ASC;

SELECT * FROM foro_comentario;
SELECT * FROM foro_publicacion;

SELECT * FROM administrador
ORDER BY idAdministrador asc;
