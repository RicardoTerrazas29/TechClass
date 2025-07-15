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
public class ReportesAdministradores {

    private final DataSource dataSource;

    public ReportesAdministradores(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @GetMapping("/administradores")
    public ResponseEntity<byte[]> generarReporteAdministradores() {
        try {
            // 1. Cargar el archivo .jasper desde resources
            InputStream jasperStream = new ClassPathResource("reportes/ReporteAdministrador.jasper").getInputStream();

            // 2. Conexión a la base de datos usando DataSource (de Spring Boot)
            Connection conn = dataSource.getConnection();

            // 3. Parámetros (imagen desde resources)
            HashMap<String, Object> parametros = new HashMap<>();
            parametros.put("IMAGE_PATH", getClass().getResourceAsStream("/images/techclasslogo.jpg"));

            // 4. Llenar el reporte con datos
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, conn);

            // 5. Exportar a PDF en memoria
            byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);

            // 6. Retornar como archivo descargable
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ReporteAdministradores.pdf")
                    .body(pdf);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

