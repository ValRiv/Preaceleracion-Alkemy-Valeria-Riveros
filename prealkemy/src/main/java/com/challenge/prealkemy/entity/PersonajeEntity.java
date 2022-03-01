package com.challenge.prealkemy.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

/**
 *
 * @author river
 */
@Entity
@Table(name = "personajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
//

@SQLDelete(sql = "UPDATE personajes SET deleted = true WHERE id=?")
//

@Where(clause = "deleted = false")
//
public class PersonajeEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(unique = true)
    private String id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Double peso;

    private String historia;

    // Atributo para SOFT DELETE
    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PeliculaEntity> peliculas = new ArrayList<>();

}




