
package com.challenge.prealkemy.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

/**
 *
 * @author river
 */
@Entity
@Table(name = "peliculas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@SQLDelete(sql = "UPDATE peliculas SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
//
public class PeliculaEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private String imagen;

    @Column(unique = true)
    private String titulo;

    @Column(name = "fechaDeCreacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    
    private LocalDate fechaDeCreacion;

    @Nullable
    
    private float calificacion;

    
    private boolean deleted = Boolean.FALSE;

     
    @ManyToMany(cascade = {
        CascadeType.DETACH,
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH}, fetch = FetchType.LAZY)
   
    
    @JoinTable(
            name = "pelicula_personajes",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<PersonajeEntity> personajes = new ArrayList<>();

     
    @ManyToMany(cascade = {
        CascadeType.DETACH,
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.REFRESH}, fetch = FetchType.LAZY)
  
    @JoinTable(
            name = "pelicula_genero",
            joinColumns = @JoinColumn(name = "pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<GeneroEntity> generos = new ArrayList<>();


}
