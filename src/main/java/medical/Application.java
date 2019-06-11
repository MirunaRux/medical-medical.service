package medical;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @SpringBootApplication annotation is equivalent with adding the following 3 annotations: @ComponentScan, @Controller, @EnableAutoConfiguration
 *
 * @ComponentScan tells Spring in which packages you have annotated classes which should be managed by Spring. So, for example, if you have a class annotated with @RestController which is in a package which is not scanned by Spring, you will not be able to use it as Spring controller.
 * Without arguments it scan the current package and all of its sub-packages.
 *
 * @Controller annotation indicates that a particular class serves the role of a controller.
 *
 * @EnableAutoConfiguration attempts to guess and configure beans that you are likely to need. Auto-configuration classes are usually applied based on your classpath and what beans you have defined.
 * It is generally recommended that you place @EnableAutoConfiguration in a root package so that all sub-packages and classes can be searched.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
