package cl.methodo.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.any;

@EnableEurekaClient
@EnableAutoConfiguration
@EnableSwagger2
@SpringBootApplication
public class FuntionalSecurityApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FuntionalSecurityApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("Authorization").select()
                .apis(RequestHandlerSelectors.basePackage("cl.methodo.authorization.client.controller"))
                .paths(any()).build().apiInfo(new ApiInfo("Servicios de Autorización de Methodo", "", "1.0.0", null, new Contact("Arquitectura Methodo", "arquitectura@methodo.cl", null), null, null));
    }

}
