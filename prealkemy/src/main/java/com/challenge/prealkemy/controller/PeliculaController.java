
package com.challenge.prealkemy.controller;

import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PeliculaDTOBasic;
import com.challenge.prealkemy.service.PeliculaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author river
 */
@Controller
@RequestMapping("peliculas")

public class PeliculaController {
    
    @Autowired
    private PeliculaService peliculaService;

   
    @GetMapping
    public ResponseEntity<List<PeliculaDTOBasic>> getAllPeliculaBasic() {
        List<PeliculaDTOBasic> peliculaBasicList = peliculaService.getAllPeliculaBasic();
        return ResponseEntity.ok().body(peliculaBasicList);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> getPeliculaById(@PathVariable String idPelicula) {
        PeliculaDTO peliculaDTO = peliculaService.getPeliculaById(idPelicula);
        return ResponseEntity.status(HttpStatus.OK).body(peliculaDTO);
    }

    
    @PostMapping
    public ResponseEntity<PeliculaDTO> savePelicula(@RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO savedPelicula = peliculaService.savePelicula(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPelicula);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> modifyPelicula(@PathVariable String idPelicula, @RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO editedPelicula = peliculaService.modifyPelicula(idPelicula, peliculaDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedPelicula);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable String idPelicula) {
        peliculaService.deletePelicula(idPelicula);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    
    @PostMapping("/{peliculaId}/personaje/{idPersonaje}")
    public ResponseEntity<Void> addPersonajeDePelicula(@PathVariable String idPersonaje, @PathVariable String peliculaId) {
        peliculaService.addPersonajeDePelicula(idPersonaje, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

   
    @DeleteMapping("/{peliculaId}/personaje/{idPersonaje}")
    public ResponseEntity<Void> removePersonajeDePelicula(@PathVariable String idPersonaje, @PathVariable String peliculaId) {
        peliculaService.removePersonajeDePelicula(idPersonaje, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    
    @PostMapping("/{peliculaId}/genero/{idGenero}")
    public ResponseEntity<Void> addGeneroDePelicula(@PathVariable String idGenero, @PathVariable String peliculaId) {
        peliculaService.addGeneroDePelicula(idGenero, peliculaId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    
    @DeleteMapping("/{peliculaId}/genero/{idGenero}")
    public ResponseEntity<Void> removeGeneroDePelicula(@PathVariable String idGenero, @PathVariable String idPelicula) {
        peliculaService.removeGeneroDePelicula(idGenero, idPelicula);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    
    @GetMapping("/filters")
    public ResponseEntity<List<PeliculaDTO>> getByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<String> personajes,
            @RequestParam(required = false) List<String> generos,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PeliculaDTO> filteredPeliculas = peliculaService.getPeliculaByFilters(title, personajes, generos, order);
        return ResponseEntity.status(HttpStatus.OK).body(filteredPeliculas);
    }
}


