package ru.itis.javawarrior.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import ru.itis.javawarrior.dto.SignUpDto;
import springfox.documentation.builders.OperationBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author lnurullina
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class ApiListingScannerPluginImpl implements ApiListingScannerPlugin {
    private static final String REQUEST_EXAMPLE = "{\n" +
            "  \"password\": \"string\",\n" +
            "  \"username\": \"string\"\n" +
            "}";

    @Override
    public List<ApiDescription> apply(DocumentationContext context) {
        return new ArrayList<>(
                Collections.singletonList(
                        new ApiDescription(
                                "/sign_in",
                                "Get token for login",
                                Collections.singletonList(
                                        new OperationBuilder(
                                                new CachingOperationNameGenerator())
                                                .authorizations(new ArrayList<>())
                                                .codegenMethodNameStem("basicAuth0001")
                                                .method(HttpMethod.POST)
                                                .parameters(
                                                        Collections.singletonList(
                                                                new ParameterBuilder()
                                                                        .description("Login")
                                                                        .type(new TypeResolver().resolve(SignUpDto.class))
                                                                        .name("Authorization")
                                                                        .parameterType("body")
                                                                        .parameterAccess("access")
                                                                        .hidden(true)
                                                                        .required(true)
                                                                        .modelRef(new ModelRef(REQUEST_EXAMPLE))
                                                                        .build())
                                                )
                                                .build()),
                                false)));
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return DocumentationType.SWAGGER_2.equals(delimiter);
    }
}
