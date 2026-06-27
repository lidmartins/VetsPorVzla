package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.SolicitudDto;
import com.vetsportvzla.backend.repository.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public SolicitudDto createSolicitud(SolicitudDto solicitud) {
        return solicitudRepository.createSolicitud(solicitud);
    }

    public SolicitudDto updateSolicitud(SolicitudDto solicitud) {
        return solicitudRepository.updateSolicitud(solicitud);
    }

    public void deleteSolicitud(int solicitudId) {
        solicitudRepository.deleteSolicitud(solicitudId);
    }

    public List<SolicitudDto> searchSolicitudes(Integer solicitudId, String type, String status, Integer ubicacionId) {
        return solicitudRepository.searchSolicitudes(solicitudId, type, status, ubicacionId);
    }
}
