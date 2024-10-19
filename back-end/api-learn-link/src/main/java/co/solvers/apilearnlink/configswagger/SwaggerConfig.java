package co.solvers.apilearnlink.configswagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

//    Link para vizualização do swagger
//    http://localhost:8080/swagger-ui/index.html

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("LearnLink API")
                        .version("v5.0.0")
                        .description("API para gerenciamento de publicações e usuários")
                )
                .externalDocs(new ExternalDocumentation()
                        .description("GitHub da API")
                        .url("https://github.com/orgs/Solvers-Co/repositories"));
    }
}
