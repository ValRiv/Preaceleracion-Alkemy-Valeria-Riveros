package com.challenge.prealkemy.controller;

import com.challenge.prealkemy.dto.PeliculaDTO;
import com.challenge.prealkemy.dto.PersonajeDTO;
import com.challenge.prealkemy.dto.PersonajeDTOBasic;
import com.challenge.prealkemy.service.PersonajeService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
@RequestMapping("personajes")
public class PersonajeController {

    @Lazy
    @Autowired
    private PersonajeService personajeService;

    @GetMapping("/{idPersonaje}")
    public ResponseEntity<PersonajeDTO> getPersonajeById(@PathVariable String idPersonaje) {
        PersonajeDTO personajeDTO = personajeService.getPersonajeById(idPersonaje);
        return ResponseEntity.ok(personajeDTO);
    }
//    @GetMapping("/filters")

    @GetMapping
    public ResponseEntity<List<PersonajeDTO>> getPersonajeByFilters(
            
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) List<String> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<PersonajeDTO> personajeDTOList = personajeService.getPersonajeByFilters(nombre, edad, peliculas, order);
        return ResponseEntity.ok(personajeDTOList);
    }

    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO savedPersonaje = personajeService.savePersonaje(personajeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonaje);

    }

    @PutMapping("/{idPersonaje}")
    public ResponseEntity<PersonajeDTO> modifyPersonaje(@PathVariable String idPersonaje, @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO modificarpersonaje = personajeService.modifyPersonaje(idPersonaje, personajeDTO);
        return ResponseEntity.ok(modificarpersonaje);
    }

    @DeleteMapping("/{idPersonaje}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable String idPersonaje) {
        personajeService.deletePersonaje(idPersonaje);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
