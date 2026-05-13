package com.ntt.practicas.gestoriaincidencias.service;

import com.ntt.practicas.gestoriaincidencias.dto.IncidenciaDTO;
import java.util.List;

public interface IncidenciaService {

    IncidenciaDTO crear(IncidenciaDTO dto);

    List<IncidenciaDTO> listar();

    IncidenciaDTO obtenerPorId(Long id);

    IncidenciaDTO actualizar(Long id, IncidenciaDTO dto);

    void eliminar(Long id);
}