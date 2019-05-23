package com.lambdaschool.health.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:2019/swagger-ui.html

@Configuration
@EnableSwagger2
public class Swagger2Config extends WebMvcConfigurerAdapter
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.lambdaschool.health")).paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("Health App").description("Health App back end, uses /oauth/token to log in").contact(new Contact("Sam R", "http://www.lambdaschool.com", "sam@lambdaschool.com")).license("MIT").licenseUrl("https://github.com/LambdaSchool/java-crudysnacks/blob/master/LICENSE").version("1.0.0").build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}