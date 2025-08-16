package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.service.ExcelService;
import com.libreria.Llibreria.service.ExcelService.Registro;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/excel")
public class ExcelUploadController {

    private final ExcelService service;
    public ExcelUploadController(ExcelService service) { this.service = service; }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Registro> upload(@RequestPart("file") MultipartFile file) throws Exception {
        try (InputStream is = file.getInputStream()) {
            return service.leerExcel(is); // devuelve JSON automático
        }
    }
}
