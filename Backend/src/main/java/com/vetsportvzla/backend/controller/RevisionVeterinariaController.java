package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.RevisionVeterinariaDto;
import com.vetsportvzla.backend.service.RevisionVeterinariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/revision-veterinaria")
public class RevisionVeterinariaController {

    private final RevisionVeterinariaService revisionVeterinariaService;

    public RevisionVeterinariaController(RevisionVeterinariaService revisionVeterinariaService) {
        this.revisionVeterinariaService = revisionVeterinariaService;
    }

    @PostMapping
    public ResponseEntity<RevisionVeterinariaDto> createRevisionVeterinaria(@RequestBody RevisionVeterinariaDto revision) {
        RevisionVeterinariaDto createdRevision = revisionVeterinariaService.createRevisionVeterinaria(revision);
        return ResponseEntity.ok(createdRevision);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RevisionVeterinariaDto> updateRevisionVeterinaria(@PathVariable int id, @RequestBody RevisionVeterinariaDto revision) {
        revision.setRvCdRevisionVet(id);
        RevisionVeterinariaDto updatedRevision = revisionVeterinariaService.updateRevisionVeterinaria(revision);
        return ResponseEntity.ok(updatedRevision);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevisionVeterinaria(@PathVariable int id) {
        revisionVeterinariaService.deleteRevisionVeterinaria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<RevisionVeterinariaDto>> searchRevisionesVeterinarias(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) Integer animalId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String status) {
        List<RevisionVeterinariaDto> revisiones = revisionVeterinariaService.searchRevisionesVeterinarias(id, animalId, userId, status);
        return ResponseEntity.ok(revisiones);
    }
}
