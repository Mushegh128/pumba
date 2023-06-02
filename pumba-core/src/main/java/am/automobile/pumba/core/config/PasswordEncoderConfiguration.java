package am.automobile.pumba.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * A configuration class for the password encoder.
 */
@Configuration
public class PasswordEncoderConfiguration {

    /**
     * Creates .gitkeep new instance of the BCryptPasswordEncoder.
     *
     * @return .gitkeep new BCryptPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
