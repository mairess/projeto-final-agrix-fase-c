package com.betrybe.agrix.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * The type Open api config.
 */
@Configuration
public class OpenApiConfig implements OpenApiCustomizer {

  /**
   * Customise.
   *
   * @param openApi the open api
   */
  @Override
  public void customise(OpenAPI openApi) {
    Info info = new Info()
        .title("Agrix Project")
        .description(
            "`Agrix` é um sistema que permite a gestão e o monitoramento das fazendas")
        .version("1.0.0");

    openApi.info(info);
  }

}