package org.example.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Startup;

@Startup
public class StartupCheck implements HealthCheck {
    @Override
    public HealthCheckResponse call() {
        return null;
    }
}
