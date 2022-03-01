
package com.challenge.prealkemy.auth.builder;

import com.challenge.prealkemy.dto.PersonajeDTOBasic;

/**
 *
 * @author river
 */
public class PersonajeBasicBuilder {
    private PersonajeDTOBasic personajeDTOBasic;

    public PersonajeBasicBuilder() {
        this.personajeDTOBasic = new PersonajeDTOBasic();
    }

    public PersonajeBasicBuilder id(String id) {
        this.personajeDTOBasic.setId(id);
        return this;
    }

    public PersonajeBasicBuilder imagen(String imagen) {
        this.personajeDTOBasic.setImagen(imagen);
        return this;
    }

    public PersonajeBasicBuilder nombre(String nombre) {
        this.personajeDTOBasic.setNombre(nombre);
        return this;
}
}