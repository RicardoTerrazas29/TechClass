package com.rest.demo.controllers;

import com.rest.demo.models.*;
import com.rest.demo.repository.*;
import com.rest.demo.service.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class PruebasTest {

    // =======================
    // ADMINISTRADOR TESTS
    // =======================
    @Nested
    class AdministradorTests {

        private AdministradorRepository administradorRepository;
        private AdminstradorService administradorService;

        @BeforeEach
        public void setUpAdministrador() {
            administradorRepository = mock(AdministradorRepository.class);
            administradorService = new AdminstradorService();

            try {
                java.lang.reflect.Field field = AdminstradorService.class.getDeclaredField("administradorRepository");
                field.setAccessible(true);
                field.set(administradorService, administradorRepository);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        public void testListarAdministrador() {
            List<Administrador> lista = Arrays.asList(
                    new Administrador(1, "Juan", "juan@mail.com", "1234"),
                    new Administrador(2, "Ana", "ana@mail.com", "abcd")
            );

            when(administradorRepository.findAll()).thenReturn(lista);

            List<Administrador> resultado = administradorService.listarTodos();

            assertEquals(2, resultado.size());
            verify(administradorRepository).findAll();
        }

        @Test
        public void testBuscarPorId() {
            Administrador admin = new Administrador(1, "Carlos", "carlos@mail.com", "clave");
            when(administradorRepository.findById(1)).thenReturn(Optional.of(admin));

            Administrador resultado = administradorService.buscarPorId(1);

            assertNotNull(resultado);
            assertEquals("Carlos", resultado.getName());
            verify(administradorRepository).findById(1);
        }

        @Test
        public void testGuardarAdministrador() {
            Administrador nuevoAdmin = new Administrador(3, "Luis", "luis@mail.com", "123456");

            administradorService.guardar(nuevoAdmin);

            verify(administradorRepository).save(nuevoAdmin);
        }

        @Test
        public void testEliminarAdministrador() {
            int id = 4;

            administradorService.eliminar(id);

            verify(administradorRepository).deleteById(id);
        }
    }

    // =======================
    // CURSO TESTS
    // =======================
    @Nested
    class CursoTests {

        private CursoRepository cursoRepository;
        private CursoService cursoService;

        @BeforeEach
        public void setUpCurso() {
            cursoRepository = mock(CursoRepository.class);
            cursoService = new CursoService();

            try {
                java.lang.reflect.Field field = CursoService.class.getDeclaredField("cursoRepository");
                field.setAccessible(true);
                field.set(cursoService, cursoRepository);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        public void testListarCursos() {
            List<Curso> cursos = Arrays.asList(
                    new Curso(1, "Matemáticas", "Curso de mates", null, null),
                    new Curso(2, "Historia", "Curso de historia", null, null)
            );

            when(cursoRepository.findAll()).thenReturn(cursos);

            List<Curso> resultado = cursoService.obtenerTodosLosCursos();

            assertEquals(2, resultado.size());
            verify(cursoRepository).findAll();
        }

        @Test
        public void testBuscarCursoPorId() {
            Curso curso = new Curso(1, "Lenguaje", "Curso de lenguaje", null, null);
            when(cursoRepository.findById(1)).thenReturn(Optional.of(curso));

            Curso resultado = cursoService.obtenerCursoPorId(1);

            assertNotNull(resultado);
            assertEquals("Lenguaje", resultado.getNombre());
            verify(cursoRepository).findById(1);
        }

        @Test
        public void testGuardarCurso() {
            Curso nuevoCurso = new Curso(3, "Física", "Curso de física", null, null);

            cursoService.guardarCurso(nuevoCurso);

            verify(cursoRepository).save(nuevoCurso);
        }

        @Test
        public void testActualizarCursoExistente() {
            Curso existente = new Curso(1, "Química", "Descripción vieja", null, null);
            Curso actualizado = new Curso(1, "Química Avanzada", "Nueva descripción", null, null);

            when(cursoRepository.findById(1)).thenReturn(Optional.of(existente));

            cursoService.actualizarCurso(1, actualizado);

            assertEquals("Química Avanzada", existente.getNombre());
            assertEquals("Nueva descripción", existente.getDescripcion());
            verify(cursoRepository).save(existente);
        }

        @Test
        public void testActualizarCursoNoExistente() {
            Curso actualizado = new Curso(1, "Biología", "No se encuentra", null, null);

            when(cursoRepository.findById(1)).thenReturn(Optional.empty());

            Exception exception = assertThrows(RuntimeException.class, () -> {
                cursoService.actualizarCurso(1, actualizado);
            });

            assertEquals("Curso no encontrado con id: 1", exception.getMessage());
        }

        @Test
        public void testEliminarCurso() {
            int id = 5;

            cursoService.eliminarCurso(id);

            verify(cursoRepository).deleteById(id);
        }
    }

    // =======================
    // ESTUDIANTE TESTS
    // =======================
    @Nested
    class EstudianteTests {

        private EstudianteRepository estudianteRepository;
        private EstudianteService estudianteService;

        @BeforeEach
        public void setUpEstudiante() {
            estudianteRepository = mock(EstudianteRepository.class);
            estudianteService = new EstudianteService();

            try {
                java.lang.reflect.Field field = EstudianteService.class.getDeclaredField("estudianteRepository");
                field.setAccessible(true);
                field.set(estudianteService, estudianteRepository);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        public void testListarEstudiantes() {
            List<Estudiante> lista = Arrays.asList(
                    new Estudiante(1, "Carlos Ramirez", "12345678", "M", "Av. Siempre Viva 123", "carlos@mail.com", "clave1"),
                    new Estudiante(2, "María Torres", "87654321", "F", "Calle Falsa 456", "maria@mail.com", "clave2")
            );

            when(estudianteRepository.findAll()).thenReturn(lista);

            List<Estudiante> resultado = estudianteService.listarTodos();

            assertEquals(2, resultado.size());
            verify(estudianteRepository).findAll();
        }

        @Test
        public void testBuscarPorId() {
            Estudiante estudiante = new Estudiante(1, "Carlos Ramirez", "12345678", "M", "Av. Siempre Viva 123", "carlos@mail.com", "clave1");
            when(estudianteRepository.findById(1)).thenReturn(Optional.of(estudiante));

            Estudiante resultado = estudianteService.buscarPorId(1);

            assertNotNull(resultado);
            assertEquals("Carlos Ramirez", resultado.getName());
            verify(estudianteRepository).findById(1);
        }

        @Test
        public void testGuardarEstudiante() {
            Estudiante nuevoEstudiante = new Estudiante(3, "Luis Fernandez", "11223344", "M", "Av. Central 789", "luis@mail.com", "clave123");

            estudianteService.guardar(nuevoEstudiante);

            verify(estudianteRepository).save(nuevoEstudiante);
        }

        @Test
        public void testActualizarEstudiante() {
            Estudiante existente = new Estudiante(1, "Carlos Ramirez", "12345678", "M", "Av. Siempre Viva 123", "carlos@mail.com", "clave1");
            Estudiante actualizado = new Estudiante(1, "Carlos R.", "99999999", "M", "Av. Nueva 321", "carlos.new@mail.com", "nuevaclave");

            when(estudianteRepository.findById(1)).thenReturn(Optional.of(existente));

            estudianteService.actualizar(1, actualizado);

            verify(estudianteRepository).findById(1);
            verify(estudianteRepository).save(existente);

            assertEquals("Carlos R.", existente.getName());
            assertEquals("99999999", existente.getDni());
            assertEquals("M", existente.getGenero());
            assertEquals("Av. Nueva 321", existente.getAddress());
            assertEquals("carlos.new@mail.com", existente.getMail());
            assertEquals("nuevaclave", existente.getClave());
        }

        @Test
        public void testEliminarEstudiante() {
            int id = 4;

            estudianteService.eliminar(id);

            verify(estudianteRepository).deleteById(id);
        }
    }

    // =======================
    // PROFESOR TESTS
    // =======================
    @Nested
    class ProfesorTests {

        private ProfesorRepository profesorRepository;
        private ProfesorService profesorService;

        @BeforeEach
        public void setUpProfesor() {
            profesorRepository = mock(ProfesorRepository.class);
            profesorService = new ProfesorService();

            try {
                java.lang.reflect.Field field = ProfesorService.class.getDeclaredField("profesorRepository");
                field.setAccessible(true);
                field.set(profesorService, profesorRepository);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Test
        public void testListarProfesores() {
            List<Profesor> lista = Arrays.asList(
                    new Profesor(1, "Juan Perez", 987654321, "juan@mail.com", "clave1"),
                    new Profesor(2, "Ana Gomez", 912345678, "ana@mail.com", "clave2")
            );

            when(profesorRepository.findAll()).thenReturn(lista);

            List<Profesor> resultado = profesorService.listarTodos();

            assertEquals(2, resultado.size());
            verify(profesorRepository).findAll();
        }
        @Test
        public void testBuscarPorId() {
            Profesor profesor = new Profesor(1, "Juan Perez", 987654321, "juan@mail.com", "clave1");
            when(profesorRepository.findById(1)).thenReturn(Optional.of(profesor));

            Profesor resultado = profesorService.buscarPorId(1);

            assertNotNull(resultado);
            assertEquals("Juan Perez", resultado.getName());
            verify(profesorRepository).findById(1);
        }

        @Test
        public void testGuardarProfesor() {
            Profesor nuevoProfesor = new Profesor(3, "Luis Martinez", 999888777, "luis@mail.com", "clave123");

            profesorService.guardar(nuevoProfesor);

            verify(profesorRepository).save(nuevoProfesor);
        }

        @Test
        public void testActualizarProfesor() {
            Profesor profesorExistente = new Profesor(1, "Juan Perez", 987654321, "juan@mail.com", "clave1");
            Profesor profesorActualizado = new Profesor(1, "Juan P.", 123456789, "juan.p@mail.com", "nuevaclave");

            when(profesorRepository.findById(1)).thenReturn(Optional.of(profesorExistente));

            profesorService.actualizar(1, profesorActualizado);

            verify(profesorRepository).findById(1);
            verify(profesorRepository).save(profesorExistente);

            assertEquals("Juan P.", profesorExistente.getName());
            assertEquals(123456789, profesorExistente.getPhone());
            assertEquals("juan.p@mail.com", profesorExistente.getMail());
            assertEquals("nuevaclave", profesorExistente.getClave());
        }

        @Test
        public void testEliminarProfesor() {
            int id = 4;

            profesorService.eliminar(id);

            verify(profesorRepository).deleteById(id);
        }
    }
}
