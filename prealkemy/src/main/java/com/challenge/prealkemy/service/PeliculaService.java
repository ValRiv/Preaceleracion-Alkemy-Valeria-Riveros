
package com.challenge.prealkemy.service;

import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PeliculaDTOBasic;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service

public interface PeliculaService {
        
      PeliculaDTO savePelicula(PeliculaDTO pelicula);

    PeliculaDTO modifyPelicula(String id, PeliculaDTO peliculaDTO);

    void deletePelicula(String id);

    List<PeliculaDTOBasic> getAllPeliculaBasic();

    List<PeliculaDTO> getAllPeliculas();

    List<PeliculaDTO> getPeliculaByFilters(String titulo, List<String> personajes, List<String> genero, String order);

    PeliculaDTO getPeliculaById(String id);

    void addPersonajeDePelicula(String personajeId, String peliculaId);

    void removePersonajeDePelicula(String idPersonaje, String idPelicula);

    void addGeneroDePelicula(String idGenero, String idPelicula);

    void removeGeneroDePelicula(String idGenre, String idMovie);
}


