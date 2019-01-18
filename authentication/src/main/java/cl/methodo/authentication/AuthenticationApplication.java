package cl.methodo.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static springfox.documentation.builders.PathSelectors.any;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableAutoConfiguration
@EnableSwagger2
public class AuthenticationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticationApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Authtentication").select()
                .apis(RequestHandlerSelectors.basePackage("cl.methodo.authentication.client.controller"))
                .paths(any()).build().apiInfo(new ApiInfo("Servicios de Autenticación de Methodo", "", "1.0.0", null, new Contact("Arquitectura Methodo", null, "arquitectura@methodo.cl"), null, null));
    }
    
}
