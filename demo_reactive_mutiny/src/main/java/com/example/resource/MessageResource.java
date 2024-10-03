package com.example.resource;

import io.smallrye.mutiny.Multi;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Duration;


@Path("messages")
public class MessageResource {

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<String> streamMessages() {

        return Multi.createFrom().ticks()
                .every(Duration.ofSeconds(2))
                .onItem().transform(n -> "message "+ n)
                .select().first(5);
    }

    private void demoMulti() {
        Multi<Long> demoMulti = Multi.createFrom().ticks().every(Duration.ofSeconds(2));
        demoMulti.subscribe();
        demoMulti.onFailure();
    }
}
