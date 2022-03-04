
package com.challenge.prealkemy.exception;

/**
 *
 * @author river
 */
public class RegisterException extends RuntimeException{
    
    public RegisterException(String errorMsg) {
        super(errorMsg);
    }
}
