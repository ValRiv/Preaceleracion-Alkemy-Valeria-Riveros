package com.challenge.prealkemy.controller;

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

    @GetMapping
    public ResponseEntity<List<PersonajeDTOBasic>> getAllPersonajeBasic() {
        List<PersonajeDTOBasic> personajeBasicList = personajeService.getAllPersonajeBasic();
        return ResponseEntity.ok().body(personajeBasicList);
    }

    /*
    4. Creación, Edición y Eliminación de Personajes (CRUD)
        Deberán existir las operaciones básicas de creación, edición y eliminación de personajes.
     */
    //
    @PostMapping
    public ResponseEntity<PersonajeDTO> savePersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO savedPersonaje = personajeService.savePersonaje(personajeDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPersonaje);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> modifyPersonaje(@PathVariable String idPersonaje, @RequestBody PersonajeDTO perDTO) {
        PersonajeDTO updatedpersonaje = personajeService.modifyPersonaje(idPersonaje, perDTO);
        return ResponseEntity.ok(updatedpersonaje);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PersonajeDTO> deletePersonaje(@PathVariable String idPersonaje) {
        personajeService.deletePersonaje(idPersonaje);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonajeDetailById(@PathVariable String idPersonaje) {
        PersonajeDTO personajeDTO = personajeService.getPersonajeById(idPersonaje);
        return ResponseEntity.ok(personajeDTO);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<PersonajeDTO>> getPersonajeByFilters(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Double peso,
            @RequestParam(required = false) List<String> peliculas,
            @RequestParam(required = false, defaultValue = "ASC") String order) {

        List<PersonajeDTO> personajeDTOList = personajeService.getPersonajeByFilters(nombre, edad, peso, peliculas, order);
        return ResponseEntity.ok(personajeDTOList);
    }
}
