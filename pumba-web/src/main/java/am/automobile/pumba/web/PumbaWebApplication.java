package am.automobile.pumba.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"am.automobile.pumba.core*", "am.automobile.pumba.web*"})
@EnableJpaRepositories(basePackages = {"am.automobile.pumba.core.repository"})
@EntityScan({"am.automobile.pumba.core.entity"})
@SpringBootApplication
public class PumbaWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(PumbaWebApplication.class, args);
    }

}
