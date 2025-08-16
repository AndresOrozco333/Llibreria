package com.libreria.Llibreria.controller;

import com.libreria.Llibreria.service.ExcelService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService service;

    public ExcelController(ExcelService service) {
        this.service = service;
    }

    // Nota: .+ para incluir el ".xlsx" en el path variable
    @GetMapping(value = "/{archivo:.+}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> leer(@PathVariable("archivo") String archivo) {
        var registros = service.leerExcel(archivo);
        return ResponseEntity.ok(service.registrosAJson(registros));
    }
}
