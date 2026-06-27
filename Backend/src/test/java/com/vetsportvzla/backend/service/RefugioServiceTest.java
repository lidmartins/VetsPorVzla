package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RefugioDto;
import com.vetsportvzla.backend.repository.RefugioRepository;
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
class RefugioServiceTest {

    @Mock
    private RefugioRepository refugioRepository;

    @InjectMocks
    private RefugioService refugioService;

    private RefugioDto refugioDto;

    @BeforeEach
    void setUp() {
        refugioDto = new RefugioDto(1, 1, 1, "Hogar de Patitas", "A", 20, 10, "A", "CA", "S", "CP", "C", "Notas", null, new Date(), new Date());
    }

    @Test
    void createRefugio() {
        when(refugioRepository.createRefugio(any(RefugioDto.class))).thenReturn(refugioDto);
        RefugioDto created = refugioService.createRefugio(refugioDto);
        assertEquals(refugioDto, created);
        verify(refugioRepository, times(1)).createRefugio(refugioDto);
    }

    @Test
    void updateRefugio() {
        when(refugioRepository.updateRefugio(any(RefugioDto.class))).thenReturn(refugioDto);
        RefugioDto updated = refugioService.updateRefugio(refugioDto);
        assertEquals(refugioDto, updated);
        verify(refugioRepository, times(1)).updateRefugio(refugioDto);
    }

    @Test
    void deleteRefugio() {
        doNothing().when(refugioRepository).deleteRefugio(1);
        refugioService.deleteRefugio(1);
        verify(refugioRepository, times(1)).deleteRefugio(1);
    }

    @Test
    void searchRefugios() {
        when(refugioRepository.searchRefugios(any(), any(), any(), any())).thenReturn(List.of(refugioDto));
        List<RefugioDto> refugios = refugioService.searchRefugios(1, 1, "A", "A");
        assertEquals(1, refugios.size());
        assertEquals(refugioDto, refugios.get(0));
        verify(refugioRepository, times(1)).searchRefugios(1, 1, "A", "A");
    }
}
