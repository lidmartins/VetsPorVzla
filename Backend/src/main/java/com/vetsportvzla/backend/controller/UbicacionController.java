package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.UbicacionDto;
import com.vetsportvzla.backend.service.UbicacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
public class UbicacionController {

    private final UbicacionService ubicacionService;

    public UbicacionController(UbicacionService ubicacionService) {
        this.ubicacionService = ubicacionService;
    }

    @PostMapping
    public ResponseEntity<UbicacionDto> createUbicacion(@RequestBody UbicacionDto ubicacion) {
        UbicacionDto createdUbicacion = ubicacionService.createUbicacion(ubicacion);
        return ResponseEntity.ok(createdUbicacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UbicacionDto> updateUbicacion(@PathVariable int id, @RequestBody UbicacionDto ubicacion) {
        ubicacion.setUrCdUbicacion(id);
        UbicacionDto updatedUbicacion = ubicacionService.updateUbicacion(ubicacion);
        return ResponseEntity.ok(updatedUbicacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUbicacion(@PathVariable int id) {
        ubicacionService.deleteUbicacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<UbicacionDto>> searchUbicaciones(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer estadoId,
            @RequestParam(required = false) String city) {
        List<UbicacionDto> ubicaciones = ubicacionService.searchUbicaciones(id, estadoId, city);
        return ResponseEntity.ok(ubicaciones);
    }
}
