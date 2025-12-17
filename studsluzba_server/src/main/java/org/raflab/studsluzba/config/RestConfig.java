package org.raflab.studsluzba.config;

import org.raflab.studsluzba.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.MediaType;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.useHalAsDefaultJsonMediaType(false);

        config.exposeIdsFor(TipSkole.class, NaucnaOblast.class, UzaNaucnaOblast.class, PredispitnaObavezaVrsta.class, Zvanje.class);
    }
}