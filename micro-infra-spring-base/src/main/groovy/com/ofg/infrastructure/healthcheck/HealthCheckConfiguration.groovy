package com.ofg.infrastructure.healthcheck

import com.ofg.infrastructure.discovery.ServiceResolver
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.io.Resource

/**
 * Registers {@link PingController} (the microservice health check controller) and {@link CollaboratorsConnectivityController} (provider of a state of microservice connection with dependent services).
 *
 * @see PingController
 * @see CollaboratorsConnectivityController
 */
@CompileStatic
@Configuration
@Import(CollaboratorsConfiguration)
class HealthCheckConfiguration {

    @Bean
    PingController pingController() {
        return new PingController()
    }

    @Bean
    CollaboratorsConnectivityController collaboratorsConnectivityController(CollaboratorsStatusResolver collaboratorsStatusResolver) {
        return new CollaboratorsConnectivityController(collaboratorsStatusResolver)
    }

    @Bean
    MicroserviceConfigurationController microserviceConfigurationController(@Value('${microservice.config.file:classpath:microservice.json}') Resource microserviceConfig) {
        return new MicroserviceConfigurationController(microserviceConfig)
    }

}
