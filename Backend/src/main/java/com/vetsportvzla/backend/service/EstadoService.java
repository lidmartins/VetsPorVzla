package com.vetsportvzla.backend.service;

import com.vetsportvzla.backend.dto.EstadoDto;
import com.vetsportvzla.backend.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public EstadoDto createEstado(EstadoDto estado) {
        return estadoRepository.createEstado(estado);
    }

    public EstadoDto updateEstado(EstadoDto estado) {
        return estadoRepository.updateEstado(estado);
    }

    public void deleteEstado(int estadoId) {
        estadoRepository.deleteEstado(estadoId);
    }

    public List<EstadoDto> searchEstados(Integer estadoId, String nombre, String status) {
        return estadoRepository.searchEstados(estadoId, nombre, status);
    }
}
