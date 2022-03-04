
package com.challenge.prealkemy.service;

import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author river
 */
@Service
public interface EmailService {
    void sendWelcomeEmailTo(String to)throws IOException;
}
