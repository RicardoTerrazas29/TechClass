package com.rest.demo.reportes;

import net.sf.jasperreports.engine.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:5173")
public class ReporteEstudiantes {

    private final DataSource dataSource;

    public ReporteEstudiantes(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<byte[]> generarReporteEstudiantes() {
        try {
            // 1. Cargar el archivo .jasper desde resources
            InputStream jasperStream = new ClassPathResource("reportes/ReporteEstudiante.jasper").getInputStream();

            // 2. Conexión a la base de datos
            Connection conn = dataSource.getConnection();

            // 3. Parámetros de imágenes
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("IMAGE1", getClass().getResourceAsStream("/images/techclasslogo.jpg"));
            parametros.put("IMAGE2", getClass().getResourceAsStream("/images/ToahMario.png"));
            parametros.put("IMAGE3", getClass().getResourceAsStream("/images/ardilla.png"));

            // 4. Llenar el reporte con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, conn);

            // 5. Exportar a PDF
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            // 6. Retornar PDF como archivo descargable
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reporte_estudiantes.pdf")
                    .body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
