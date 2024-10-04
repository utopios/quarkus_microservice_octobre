package org.example.restclient;



import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.example.dto.Review;

import java.util.List;

@Path("/reviews")
@RegisterRestClient
public interface ReviewServiceClient {

    @GET
    @Path("/product/{productId}")
    List<Review> getReviewsByProductId(@PathParam("productId") Long productId);
}
