package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.RefugioDto;
import com.vetsportvzla.backend.repository.RefugioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RefugioService {

    private final RefugioRepository refugioRepository;

    public RefugioService(RefugioRepository refugioRepository) {
        this.refugioRepository = refugioRepository;
    }

    public RefugioDto createRefugio(RefugioDto refugio) {
        return refugioRepository.createRefugio(refugio);
    }

    public RefugioDto updateRefugio(RefugioDto refugio) {
        return refugioRepository.updateRefugio(refugio);
    }

    public void deleteRefugio(int refugioId) {
        refugioRepository.deleteRefugio(refugioId);
    }

    public List<RefugioDto> searchRefugios(Integer refugioId, Integer ubicacionId, String status, String speciesAllowed) {
        return refugioRepository.searchRefugios(refugioId, ubicacionId, status, speciesAllowed);
    }
}
