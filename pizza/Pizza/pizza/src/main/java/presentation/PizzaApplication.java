package presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"presentation", "applicationLayer", "infrastructure", "domain"})
public class PizzaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

}
