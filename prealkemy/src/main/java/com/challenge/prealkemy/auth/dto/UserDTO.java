
package com.challenge.prealkemy.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 *
 * @author river
 */
@Data
public class UserDTO {
    @Email(message = "El nombre de usuario debe ser un correo electrónico")
    @NotNull
    private String username;
    @NotNull
    @Size(min = 8)
    private String password;
}
