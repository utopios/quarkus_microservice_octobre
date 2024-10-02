package com.example.healthcheck;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Readiness
@ApplicationScoped
public class ReadyCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        //Logique métier healthcheck ready
        return HealthCheckResponse.up("Readiness OK");
    }
}
