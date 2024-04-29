package org.example.atp.rest.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MiConfiguracion {
    @Bean
    public ModelMapper ModelMapper() {
        return new ModelMapper();
    }

    //Configuracion de CORS para el frontend
  //  @Bean
    //public WebMvcConfigurer WebMvcConfigurer() {
      //  return new WebMvcConfigurer() {
        //    @Override
          //  public void addCorsMappings(CorsRegistry registry) {
            //    registry.addMapping("/tenistas/**")
              //          .allowedOrigins("http://localhost:4200")
                //        .allowedMethods("GET", "POST", "PUT", "DELETE");
            //}
        //};
    //}
}
