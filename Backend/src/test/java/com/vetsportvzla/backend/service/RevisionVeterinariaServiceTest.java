package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RevisionVeterinariaDto;
import com.vetsportvzla.backend.repository.RevisionVeterinariaRepository;
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
class RevisionVeterinariaServiceTest {

    @Mock
    private RevisionVeterinariaRepository revisionVeterinariaRepository;

    @InjectMocks
    private RevisionVeterinariaService revisionVeterinariaService;

    private RevisionVeterinariaDto revisionVeterinariaDto;

    @BeforeEach
    void setUp() {
        revisionVeterinariaDto = new RevisionVeterinariaDto(1, 1, 1, "R", "Sano", new Date(), new Date());
    }

    @Test
    void createRevisionVeterinaria() {
        when(revisionVeterinariaRepository.createRevisionVeterinaria(any(RevisionVeterinariaDto.class))).thenReturn(revisionVeterinariaDto);
        RevisionVeterinariaDto created = revisionVeterinariaService.createRevisionVeterinaria(revisionVeterinariaDto);
        assertEquals(revisionVeterinariaDto, created);
        verify(revisionVeterinariaRepository, times(1)).createRevisionVeterinaria(revisionVeterinariaDto);
    }

    @Test
    void updateRevisionVeterinaria() {
        when(revisionVeterinariaRepository.updateRevisionVeterinaria(any(RevisionVeterinariaDto.class))).thenReturn(revisionVeterinariaDto);
        RevisionVeterinariaDto updated = revisionVeterinariaService.updateRevisionVeterinaria(revisionVeterinariaDto);
        assertEquals(revisionVeterinariaDto, updated);
        verify(revisionVeterinariaRepository, times(1)).updateRevisionVeterinaria(revisionVeterinariaDto);
    }

    @Test
    void deleteRevisionVeterinaria() {
        doNothing().when(revisionVeterinariaRepository).deleteRevisionVeterinaria(1);
        revisionVeterinariaService.deleteRevisionVeterinaria(1);
        verify(revisionVeterinariaRepository, times(1)).deleteRevisionVeterinaria(1);
    }

    @Test
    void searchRevisionesVeterinarias() {
        when(revisionVeterinariaRepository.searchRevisionesVeterinarias(any(), any(), any(), any())).thenReturn(List.of(revisionVeterinariaDto));
        List<RevisionVeterinariaDto> revisiones = revisionVeterinariaService.searchRevisionesVeterinarias(1, 1, 1, "R");
        assertEquals(1, revisiones.size());
        assertEquals(revisionVeterinariaDto, revisiones.get(0));
        verify(revisionVeterinariaRepository, times(1)).searchRevisionesVeterinarias(1, 1, 1, "R");
    }
}
