package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.SolicitudDto;
import com.vetsportvzla.backend.repository.SolicitudRepository;
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
class SolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @InjectMocks
    private SolicitudService solicitudService;

    private SolicitudDto solicitudDto;

    @BeforeEach
    void setUp() {
        solicitudDto = new SolicitudDto(1, 1, 1, 1, "P", new Date(), "A", null, "/path", "url", new Date(), new Date());
    }

    @Test
    void createSolicitud() {
        when(solicitudRepository.createSolicitud(any(SolicitudDto.class))).thenReturn(solicitudDto);
        SolicitudDto created = solicitudService.createSolicitud(solicitudDto);
        assertEquals(solicitudDto, created);
        verify(solicitudRepository, times(1)).createSolicitud(solicitudDto);
    }

    @Test
    void updateSolicitud() {
        when(solicitudRepository.updateSolicitud(any(SolicitudDto.class))).thenReturn(solicitudDto);
        SolicitudDto updated = solicitudService.updateSolicitud(solicitudDto);
        assertEquals(solicitudDto, updated);
        verify(solicitudRepository, times(1)).updateSolicitud(solicitudDto);
    }

    @Test
    void deleteSolicitud() {
        doNothing().when(solicitudRepository).deleteSolicitud(1);
        solicitudService.deleteSolicitud(1);
        verify(solicitudRepository, times(1)).deleteSolicitud(1);
    }

    @Test
    void searchSolicitudes() {
        when(solicitudRepository.searchSolicitudes(any(), any(), any(), any())).thenReturn(List.of(solicitudDto));
        List<SolicitudDto> solicitudes = solicitudService.searchSolicitudes(1, "P", "A", 1);
        assertEquals(1, solicitudes.size());
        assertEquals(solicitudDto, solicitudes.get(0));
        verify(solicitudRepository, times(1)).searchSolicitudes(1, "P", "A", 1);
    }
}
