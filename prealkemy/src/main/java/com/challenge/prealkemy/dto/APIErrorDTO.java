
package com.challenge.prealkemy.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 *
 * @author river
 */
@Data
@AllArgsConstructor

public class APIErrorDTO {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
