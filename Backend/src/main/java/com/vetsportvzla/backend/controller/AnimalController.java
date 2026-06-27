package com.vetsportvzla.backend.controller;

import com.vetsportvzla.backend.dto.AnimalDto;
import com.vetsportvzla.backend.service.AnimalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @PostMapping
    public ResponseEntity<AnimalDto> createAnimal(@RequestBody AnimalDto animal) {
        AnimalDto createdAnimal = animalService.createAnimal(animal);
        return ResponseEntity.ok(createdAnimal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnimalDto> updateAnimal(@PathVariable int id, @RequestBody AnimalDto animal) {
        animal.setAnCdAnimal(id);
        AnimalDto updatedAnimal = animalService.updateAnimal(animal);
        return ResponseEntity.ok(updatedAnimal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnimal(@PathVariable int id) {
        animalService.deleteAnimal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnimalDto>> searchAnimals(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String size,
            @RequestParam(required = false) String sex,
            @RequestParam(required = false) String vetReviewStatus) {
        List<AnimalDto> animals = animalService.searchAnimals(id, type, size, sex, vetReviewStatus);
        return ResponseEntity.ok(animals);
    }
}
