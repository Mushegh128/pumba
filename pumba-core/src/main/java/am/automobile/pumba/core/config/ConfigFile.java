package am.automobile.pumba.core.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

@Component
public class ConfigFile {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("3MB"));
        factory.setMaxRequestSize(DataSize.parse("3MB"));
        return factory.createMultipartConfig();
    }
}
