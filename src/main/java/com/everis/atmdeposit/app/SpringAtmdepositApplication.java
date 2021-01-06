package com.everis.atmdeposit.app;

import com.everis.atmdeposit.app.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;

/**
 * Class SpringAtmdepositApplication.
 */
@Import(SwaggerConfiguration.class)
@SpringBootApplication
public class SpringAtmdepositApplication {
    /**
     *
     * @param args .
     */
    public static void main(final String[] args) {
        SpringApplication.run(SpringAtmdepositApplication.class, args);
    }

    /**
     * @param registry .
     */
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }
}
