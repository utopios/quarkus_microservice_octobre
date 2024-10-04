package com.example.resource;


import com.example.entity.Review;
import com.example.service.ReviewService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReviewResource {

    private final ReviewService reviewService;

    public ReviewResource(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GET
    @Path("/product/{productId}")
    public List<Review> getReviewsByProductId(@PathParam("productId") Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @POST
    @Transactional
    public Review createReview(Review review) throws Exception {
        return reviewService.createReview(review);
    }

    @DELETE
    @Path("/{id}")
    public void deleteReview(@PathParam("id") Long id) {
        reviewService.deleteReview(id);
    }
}
