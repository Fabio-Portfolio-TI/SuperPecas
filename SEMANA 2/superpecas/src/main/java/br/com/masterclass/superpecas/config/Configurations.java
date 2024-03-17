package br.com.masterclass.superpecas.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurations {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
}



}
