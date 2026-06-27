package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.UbicacionDto;
import com.vetsportvzla.backend.repository.UbicacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UbicacionServiceTest {

    @Mock
    private UbicacionRepository ubicacionRepository;

    @InjectMocks
    private UbicacionService ubicacionService;

    private UbicacionDto ubicacionDto;

    @BeforeEach
    void setUp() {
        ubicacionDto = new UbicacionDto(1, 1, "Maracaibo", "Sector 1", "Calle 1", "Ref 1", "4001", new BigDecimal("10.0"), new BigDecimal("-71.0"), new Date(), new Date());
    }

    @Test
    void createUbicacion() {
        when(ubicacionRepository.createUbicacion(any(UbicacionDto.class))).thenReturn(ubicacionDto);
        UbicacionDto created = ubicacionService.createUbicacion(ubicacionDto);
        assertEquals(ubicacionDto, created);
        verify(ubicacionRepository, times(1)).createUbicacion(ubicacionDto);
    }

    @Test
    void updateUbicacion() {
        when(ubicacionRepository.updateUbicacion(any(UbicacionDto.class))).thenReturn(ubicacionDto);
        UbicacionDto updated = ubicacionService.updateUbicacion(ubicacionDto);
        assertEquals(ubicacionDto, updated);
        verify(ubicacionRepository, times(1)).updateUbicacion(ubicacionDto);
    }

    @Test
    void deleteUbicacion() {
        doNothing().when(ubicacionRepository).deleteUbicacion(1);
        ubicacionService.deleteUbicacion(1);
        verify(ubicacionRepository, times(1)).deleteUbicacion(1);
    }

    @Test
    void searchUbicaciones() {
        when(ubicacionRepository.searchUbicaciones(any(), any(), any())).thenReturn(List.of(ubicacionDto));
        List<UbicacionDto> ubicaciones = ubicacionService.searchUbicaciones(1, 1, "Maracaibo");
        assertEquals(1, ubicaciones.size());
        assertEquals(ubicacionDto, ubicaciones.get(0));
        verify(ubicacionRepository, times(1)).searchUbicaciones(1, 1, "Maracaibo");
    }
}
