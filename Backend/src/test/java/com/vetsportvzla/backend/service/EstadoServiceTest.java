package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.EstadoDto;
import com.vetsportvzla.backend.repository.EstadoRepository;
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
class EstadoServiceTest {

    @Mock
    private EstadoRepository estadoRepository;

    @InjectMocks
    private EstadoService estadoService;

    private EstadoDto estadoDto;

    @BeforeEach
    void setUp() {
        estadoDto = new EstadoDto(1, 58, "Zulia", "A", new Date(), new Date());
    }

    @Test
    void createEstado() {
        when(estadoRepository.createEstado(any(EstadoDto.class))).thenReturn(estadoDto);
        EstadoDto created = estadoService.createEstado(estadoDto);
        assertEquals(estadoDto, created);
        verify(estadoRepository, times(1)).createEstado(estadoDto);
    }

    @Test
    void updateEstado() {
        when(estadoRepository.updateEstado(any(EstadoDto.class))).thenReturn(estadoDto);
        EstadoDto updated = estadoService.updateEstado(estadoDto);
        assertEquals(estadoDto, updated);
        verify(estadoRepository, times(1)).updateEstado(estadoDto);
    }

    @Test
    void deleteEstado() {
        doNothing().when(estadoRepository).deleteEstado(1);
        estadoService.deleteEstado(1);
        verify(estadoRepository, times(1)).deleteEstado(1);
    }

    @Test
    void searchEstados() {
        when(estadoRepository.searchEstados(any(), any(), any())).thenReturn(List.of(estadoDto));
        List<EstadoDto> estados = estadoService.searchEstados(1, "Zulia", "A");
        assertEquals(1, estados.size());
        assertEquals(estadoDto, estados.get(0));
        verify(estadoRepository, times(1)).searchEstados(1, "Zulia", "A");
    }
}
