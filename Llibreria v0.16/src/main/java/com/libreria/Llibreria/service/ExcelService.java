package com.libreria.Llibreria.service;

import com.google.gson.Gson;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    public static class Registro {
        private final String nombre;
        private final String categoria;
        private final double precio;

        public Registro(String nombre, String categoria, double precio) {
            this.nombre = nombre;
            this.categoria = categoria;
            this.precio = precio;
        }
        public String getNombre() { return nombre; }
        public String getCategoria() { return categoria; }
        public double getPrecio() { return precio; }
    }

    // EXISTENTE: lee desde resources por nombre
    public List<Registro> leerExcel(String nombreArchivo) {
        try (InputStream is = getClass().getResourceAsStream("/" + nombreArchivo)) {
            if (is == null) throw new IllegalArgumentException("No se encontró en resources: " + nombreArchivo);
            return leerExcel(is);
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo Excel: " + e.getMessage(), e);
        }
    }

    // NUEVO: lee desde un InputStream (para uploads)
    public List<Registro> leerExcel(InputStream is) {
        List<Registro> registros = new ArrayList<>();
        DataFormatter fmt = new DataFormatter();

        try (Workbook wb = WorkbookFactory.create(is)) {
            Sheet hoja = wb.getSheetAt(0);
            for (Row fila : hoja) {
                if (fila.getRowNum() == 0) continue;
                String nombre = fmt.formatCellValue(fila.getCell(0));
                String categoria = fmt.formatCellValue(fila.getCell(1));
                String precioStr = fmt.formatCellValue(fila.getCell(2));
                if (nombre.isBlank() && categoria.isBlank() && precioStr.isBlank()) continue;
                double precio = 0.0;
                try { precio = Double.parseDouble(precioStr.replace(",", ".")); } catch (NumberFormatException ignored) {}
                registros.add(new Registro(nombre, categoria, precio));
            }
            return registros;
        } catch (Exception e) {
            throw new RuntimeException("Error leyendo Excel subido: " + e.getMessage(), e);
        }
    }

    public String registrosAJson(List<Registro> registros) {
        return new Gson().toJson(registros);
    }
}
