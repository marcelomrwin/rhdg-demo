package com.redhat.test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class DemoDataGridTest {
    @Test
    public void testInsertAndRetrieveOnCache(){
        given().contentType(ContentType.JSON)
                .body("{\n" +
                        "           \"id\": \"1\",\n" +
                        "           \"routeNumber\": \"42\",\n" +
                        "           \"startLocation\": \"Main Street\",\n" +
                        "           \"endLocation\": \"Broadway\"\n" +
                        "         }")
                .when()
                .put("/api/cache/1")
                .then()
                .statusCode(201);
    }
}
