package com.example.provider;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;


@Provider
@Priority(Priorities.AUTHENTICATION)
public class CustomJWTAtuhFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        //Récupération du token

        String token = containerRequestContext.getHeaderString("Authorization").split(" ")[1];


        //Vérification du token en utilisant la clé public
    }

    private boolean verificationToken(String token) {
        String[] partieToken = token.split(".");
        String newSignature = calculeSignature(partieToken[0], partieToken[1]);
        return (newSignature.equals(partieToken[2]));
    }

    private String calculeSignature(String partie1, String partie2) {
        /// Calcule de signature avec la clé privée
        return "signature";
    }
}
