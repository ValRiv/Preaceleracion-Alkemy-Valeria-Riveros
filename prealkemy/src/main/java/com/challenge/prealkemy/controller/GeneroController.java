
package com.challenge.prealkemy.controller;

import com.challenge.prealkemy.dto.GeneroDTO;
import com.challenge.prealkemy.dto.GeneroDTOBasic;
import com.challenge.prealkemy.service.GeneroService;
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

/**
 *
 * @author river
 */
@Controller
@RequestMapping("generos")
public class GeneroController {
    @Autowired
    private GeneroService generoService;

   
    @GetMapping
    public ResponseEntity<List<GeneroDTOBasic>> getAllGeneros() {
        List<GeneroDTOBasic> generoList = generoService.getAllGenerosBasic();
        return ResponseEntity.ok().body(generoList);
    }

    
    @PostMapping
    public ResponseEntity<GeneroDTO> saveGenero(@RequestBody GeneroDTO generoDTO) {
        GeneroDTO savedGenero = generoService.saveGenero(generoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGenero);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> modifyGenero(@PathVariable String idGenero, @RequestBody GeneroDTO generoDTO) {
        GeneroDTO editedGenero = generoService.modifyGenero(idGenero, generoDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(editedGenero);
    }

    // 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenero(@PathVariable String idGenero) {
        generoService.deleteGeneroById(idGenero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


