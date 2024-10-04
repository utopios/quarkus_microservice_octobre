package org.example.health;

import jakarta.ws.rs.Path;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;


@Liveness

public class LivenessCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return null;
    }
}
