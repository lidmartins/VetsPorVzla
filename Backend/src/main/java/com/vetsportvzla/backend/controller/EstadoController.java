package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.EstadoDto;
import com.vetsportvzla.backend.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @PostMapping
    public ResponseEntity<EstadoDto> createEstado(@RequestBody EstadoDto estado) {
        EstadoDto createdEstado = estadoService.createEstado(estado);
        return ResponseEntity.ok(createdEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> updateEstado(@PathVariable int id, @RequestBody EstadoDto estado) {
        estado.setEsCdEstado(id);
        EstadoDto updatedEstado = estadoService.updateEstado(estado);
        return ResponseEntity.ok(updatedEstado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstado(@PathVariable int id) {
        estadoService.deleteEstado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<EstadoDto>> searchEstados(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String status) {
        List<EstadoDto> estados = estadoService.searchEstados(id, nombre, status);
        return ResponseEntity.ok(estados);
    }
}
