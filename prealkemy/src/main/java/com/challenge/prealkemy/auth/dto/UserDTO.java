
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
    @Email(message = "Username must be an email")
    private String username;
    @Size(min = 8)
    @NotNull
    private String password;
}
