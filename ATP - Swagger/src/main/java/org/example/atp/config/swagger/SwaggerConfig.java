package org.example.atp.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
   // @Bean
  //  OpenAPI apiInfo() {
        //return new OpenAPI()
               // .info(
                       // new Info()
                            //    .title("API REST de ATP 2024/2025")
                            //    .version("1.0.0")
                            //    .description("API del curso de Spring para la asignatura Desarrollo de Aplicaciones Web 2024/2025")
                            //    .contact(
                            //            new Contact()
                                       //         .name("Laura Garrido Arredondo")
                           //                     .email("anoobmedic@gmail.com")
                             //   )

             //   );
   // }


   // @Bean
   // GroupedOpenApi httpApi() {
      //  return GroupedOpenApi.builder()
        //        .group("https")
      //          .pathsToMatch("/org.example.atp.rest.tenistas/**", "/org.example.atp.rest.torneos/**")
    //            .displayName("API ATP 2024/2025")
    //            .build();
  //  }
}