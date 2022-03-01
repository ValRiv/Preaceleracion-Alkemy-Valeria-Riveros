
package com.challenge.prealkemy.auth.builder;

import com.challenge.prealkemy.entity.GeneroEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author river
 */
@Getter
@Setter
public class GeneroEntityBuilder {
     private GeneroEntity generoEntity;

    public GeneroEntityBuilder() {
        this.generoEntity = new GeneroEntity();
    }

    public GeneroEntityBuilder imagen(String imagen) {
        this.generoEntity.setImagen(imagen);
        return this;
    }

    public GeneroEntityBuilder nombre(String nombre) {
        this.generoEntity.setNombre(nombre);
        return this;
    }

    public GeneroEntity build() {
        return generoEntity;
    }
}