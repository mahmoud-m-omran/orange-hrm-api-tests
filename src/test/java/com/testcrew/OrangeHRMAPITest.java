package com.testcrew;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.Arrays;

public class OrangeHRMAPITest {
    private final String baseUrl = "https://opensource-demo.orangehrmlive.com";
    private String apiKey = "acb8a4a2773e7e60307ad9eed36b472d";

    @Test
    public void aalogin() {
        Response response = RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/web/index.php/auth/validate")
                .header(HttpHeaders.ACCEPT, "application/json")
                .contentType(ContentType.HTML)
                .formParam("_token", "56c3d77fb7bbee.DQyFZvAkfz4UfKEj5SitaOjJ9EvO7OfbTVOwiWwW8TA.Rlu1CahdOFZYD-tzll3uOaewnS-DtL29dBD3-Sh0tmlgO9EsyRYGXWVJyg")
                .formParam("username", "Admin")
                .formParam("password", "admin123")
                .post();

        Cookie orangehrmCookie = response.getDetailedCookie("orangehrm");

        apiKey = orangehrmCookie.getValue();
    }

    @Test(description = "Delete multiple candidates")
    public void deleteCandidates() {
        int[] candidateIds = {25, 26, 27, 28, 23, 5, 19, 17, 1, 2, 12, 13, 20, 9};

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/web/index.php/api/v2/recruitment/candidates")
                .header(HttpHeaders.ACCEPT, "application/json")
                .header("Cookie", "orangehrm=" + "0c8c16b9e9f98ffb765423748e81c257")
                .contentType(ContentType.JSON)
                .body("{\"ids\":" + Arrays.toString(candidateIds) + "}")
                .delete()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "Add a new candidate")
    public void addCandidate() {
        String candidateJson = "{"
                + "\"firstName\": \"s\","
                + "\"middleName\": \"s\","
                + "\"lastName\": \"s\","
                + "\"email\": \"c@c.com\","
                + "\"contactNumber\": null,"
                + "\"keywords\": null,"
                + "\"comment\": null,"
                + "\"dateOfApplication\": \"2023-10-30\","
                + "\"consentToKeepData\": false,"
                + "\"vacancyId\": 4"
                + "}";

        RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/web/index.php/api/v2/recruitment/candidates")
                .header(HttpHeaders.ACCEPT, "application/json")
                .header("Cookie", "orangehrm=" + apiKey)
                .contentType(ContentType.JSON)
                .body(candidateJson)
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "Search candidates")
    public void searchCandidates() {
        RestAssured.given()
                .baseUri(baseUrl)
                .basePath("/web/index.php/api/v2/recruitment/candidates")
                .param("limit", 50)
                .param("offset", 0)
                .param("jobTitleId", 22)
                .param("vacancyId", 4)
                .param("hiringManagerId", 6)
                .param("fromDate", "2023-10-03")
                .param("toDate", "2023-10-19")
                .param("status", 2)
                .param("model", "list")
                .param("sortField", "candidate.dateOfApplication")
                .param("sortOrder", "DESC")
                .header(HttpHeaders.ACCEPT, "application/json")
                .header("Cookie", "orangehrm=" + apiKey)
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
