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


SELECT * FROM curso;
SELECT*FROM profesor;
SELECT*FROM estudiante;



