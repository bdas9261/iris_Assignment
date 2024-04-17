package Stepdefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;


import static io.restassured.RestAssured.when;
import static net.serenitybdd.rest.SerenityRest.*;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.junit.Assert.assertThat;


public class ApiTest {
    @Given("the API {string}")
    public void the_api(String string) {
        SerenityRest.setDefaultBaseUri(apiUrl);

    }
    @When("a GET request is made")
    public void a_get_request_is_made() {
        SerenityRest.when().get();

    }
    @Then("^the response status code should be (\\d+)$")
    public void verifyStatusCode(int expectedStatusCode) {
        SerenityRest.then().statusCode(expectedStatusCode);
    }
    @Then("the response should contain a valid price")
    public void the_response_should_contain_a_valid_price() {
        String responseBody = SerenityRest.then().extract().body().asString();
        double price = Double.parseDouble(responseBody);
        assertThat(price).isGreaterThan(0);

    }
    @Then("the price should be in the range {double}-{int}.{int}")
    public void the_price_should_be_in_the_range(Double actualPairs, double min, double max) {
        String responseBody = SerenityRest.then().extract().body().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
         actualPairs = jsonPath.getList("$.rates.AED").size();
        //assertThat(actualPairs).isEqualTo(expectedPairs);
        //double price = Double.parseDouble(responseBody);
        assertThat(actualPairs).isBetween(min).and(max);
    }
    @Then("the response time should not be less than {int} seconds")
    public void the_response_time_should_not_be_less_than_seconds(Integer minResponseTime) {
        long actualResponseTime = SerenityRest.then().extract().time();
        assertThat(actualResponseTime).isGreaterThanOrEqualTo(minResponseTime * 1000);

    }
    @Then("the response should contain {int} currency coin pairs")
    public void the_response_should_contain_currency_coin_pairs(Integer expectedPairs) {
        String responseBody = SerenityRest.then().extract().body().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        int actualPairs = jsonPath.getList("$.rates").size();
        assertThat(actualPairs).isEqualTo(expectedPairs);

    }
    @Then("the response should match the JSON schema {string}")
    public void the_response_should_match_the_json_schema(String schemaFilePath) {
        String responseBody = SerenityRest.then().extract().body().asString();

        // Load the JSON schema from the file
        File schemaFile = new File(schemaFilePath);
        JsonSchemaFactory factory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(
                        ValidationConfiguration.newBuilder()
                                .setDefaultVersion(
                                        com.github.fge.jsonschema.core.draftv3.DraftV3SchemaVersion
                                                .instance())
                                .freeze())
                .freeze();
        JsonSchema schema = factory.getJsonSchema(schemaFile.toURI().toString());

        // Validate the response against the JSON schema
        schema.validate(responseBody);
    }


}
