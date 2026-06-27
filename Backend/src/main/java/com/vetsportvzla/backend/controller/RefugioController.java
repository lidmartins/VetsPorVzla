package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.RefugioDto;
import com.vetsportvzla.backend.service.RefugioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refugio")
public class RefugioController {

    private final RefugioService refugioService;

    public RefugioController(RefugioService refugioService) {
        this.refugioService = refugioService;
    }

    @PostMapping
    public ResponseEntity<RefugioDto> createRefugio(@RequestBody RefugioDto refugio) {
        RefugioDto createdRefugio = refugioService.createRefugio(refugio);
        return ResponseEntity.ok(createdRefugio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RefugioDto> updateRefugio(@PathVariable int id, @RequestBody RefugioDto refugio) {
        refugio.setReCdRefugio(id);
        RefugioDto updatedRefugio = refugioService.updateRefugio(refugio);
        return ResponseEntity.ok(updatedRefugio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRefugio(@PathVariable int id) {
        refugioService.deleteRefugio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RefugioDto>> searchRefugios(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer ubicacionId,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String speciesAllowed) {
        List<RefugioDto> refugios = refugioService.searchRefugios(id, ubicacionId, status, speciesAllowed);
        return ResponseEntity.ok(refugios);
    }
}
