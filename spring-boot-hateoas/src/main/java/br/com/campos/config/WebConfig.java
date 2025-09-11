package br.com.campos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        // Via Extension. http://localhost:8080/api/person/v1/2.xml or http://localhost:8080/api/person/v1/2.json


        // Via QUERY PARAM: http://localhost:8080/api/person/v1/2?MediaType=xml
        /*
        configurer.favorParameter(true)
                .parameterName("MediaType")
                .ignoreAcceptHeader(true)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML);
        */

        // Via HEADER PARAM: http://localhost:8080/api/person/v1/2?MediaType=xml
        configurer.favorParameter(false)
                .parameterName("MediaType")
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("yaml", MediaType.APPLICATION_YAML);

    }
}
