package org.example.client;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dto.ProductDto;

@Path("/products")
@RegisterRestClient(configKey = "product-service")
public interface ProductServiceClient {

    @GET
    @Path("/{id}")
    ProductDto getProductById(@PathParam("id") Long id);


}
