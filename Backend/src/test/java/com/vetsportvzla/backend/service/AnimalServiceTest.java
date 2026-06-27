package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.AnimalDto;
import com.vetsportvzla.backend.repository.AnimalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnimalServiceTest {

    @Mock
    private AnimalRepository animalRepository;

    @InjectMocks
    private AnimalService animalService;

    private AnimalDto animalDto;

    @BeforeEach
    void setUp() {
        animalDto = new AnimalDto(1, 1, "Fido", "P", "Mestizo", "Negro", "M", "M", 3, "Amigable", "S", null, "P", new Date(), new Date());
    }

    @Test
    void createAnimal() {
        when(animalRepository.createAnimal(any(AnimalDto.class))).thenReturn(animalDto);
        AnimalDto created = animalService.createAnimal(animalDto);
        assertEquals(animalDto, created);
        verify(animalRepository, times(1)).createAnimal(animalDto);
    }

    @Test
    void updateAnimal() {
        when(animalRepository.updateAnimal(any(AnimalDto.class))).thenReturn(animalDto);
        AnimalDto updated = animalService.updateAnimal(animalDto);
        assertEquals(animalDto, updated);
        verify(animalRepository, times(1)).updateAnimal(animalDto);
    }

    @Test
    void deleteAnimal() {
        doNothing().when(animalRepository).deleteAnimal(1);
        animalService.deleteAnimal(1);
        verify(animalRepository, times(1)).deleteAnimal(1);
    }

    @Test
    void searchAnimals() {
        when(animalRepository.searchAnimals(any(), any(), any(), any(), any())).thenReturn(List.of(animalDto));
        List<AnimalDto> animals = animalService.searchAnimals(1, "P", "M", "M", "P");
        assertEquals(1, animals.size());
        assertEquals(animalDto, animals.get(0));
        verify(animalRepository, times(1)).searchAnimals(1, "P", "M", "M", "P");
    }
}
