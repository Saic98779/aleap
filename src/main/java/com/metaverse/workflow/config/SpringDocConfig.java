package com.metaverse.workflow.config;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverter;
import io.swagger.v3.core.converter.ModelConverterContext;
import io.swagger.v3.oas.models.media.Schema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

@Configuration
public class SpringDocConfig {

    @Bean
    public ModelConverter servletTypeIgnoringConverter() {
        return new ModelConverter() {
            @Override
            public Schema<?> resolve(AnnotatedType type, ModelConverterContext context, Iterator<ModelConverter> chain) {
                if (type.getType() != null) {
                    String typeName = type.getType().getTypeName();
                    if (typeName.equals(HttpServletRequest.class.getName())
                            || typeName.equals(HttpServletResponse.class.getName())) {
                        return null;
                    }
                }
                return chain.hasNext() ? chain.next().resolve(type, context, chain) : null;
            }
        };
    }
}

