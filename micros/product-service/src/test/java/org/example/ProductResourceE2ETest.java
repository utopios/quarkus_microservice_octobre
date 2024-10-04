package org.example;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class ProductResourceE2ETest {

    @Test
    public void testAddProductEndpoint() {
        Product productToAdd = new Product(null, "Product 1", "Description", 100.0, true, 10);

        // Envoyer le produit et vérifier que la réponse renvoie bien un produit sérialisé
        RestAssured.given()
                .contentType("application/json")
                .body(productToAdd) // Envoyer l'objet Product sérialisé
                .when().post("/products")
                .then()
                .statusCode(200)
                .body("name", equalTo("Product 1"))
                .body("description", equalTo("Description"))
                .body("price", equalTo(100.0f)) // Pour les valeurs numériques avec décimales, utiliser 'equalTo' avec un float
                .body("available", equalTo(true))
                .body("quantity", equalTo(10));
    }


}
