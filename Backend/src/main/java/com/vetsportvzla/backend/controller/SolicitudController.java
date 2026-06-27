package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.SolicitudDto;
import com.vetsportvzla.backend.service.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitud")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<SolicitudDto> createSolicitud(@RequestBody SolicitudDto solicitud) {
        SolicitudDto createdSolicitud = solicitudService.createSolicitud(solicitud);
        return ResponseEntity.ok(createdSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDto> updateSolicitud(@PathVariable int id, @RequestBody SolicitudDto solicitud) {
        solicitud.setSoCdSolicitud(id);
        SolicitudDto updatedSolicitud = solicitudService.updateSolicitud(solicitud);
        return ResponseEntity.ok(updatedSolicitud);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable int id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<SolicitudDto>> searchSolicitudes(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer ubicacionId) {
        List<SolicitudDto> solicitudes = solicitudService.searchSolicitudes(id, type, status, ubicacionId);
        return ResponseEntity.ok(solicitudes);
    }
}
