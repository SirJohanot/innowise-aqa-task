import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;

public class CatFactsTest {

    @Test
    public void testFactShouldReturnAFactOfTheRequestedMaximumLengthWhenLengthIsEnoughToContainTheFact() {
        get("https://catfact.ninja/fact?max_length=20")
                .then()
                .statusCode(200)
                .assertThat()
                .body("fact", notNullValue())
                .body("length", lessThanOrEqualTo(20));
    }

    @Test
    public void testFactShouldNotReturnAnythingWhenLengthIsNotEnoughToContainTheFact() {
        get("https://catfact.ninja/fact?max_length=3")
                .then()
                .statusCode(200)
                .assertThat()
                .body("fact", nullValue());
    }

    @Test
    public void testFactsShouldReturnListOfFactOfTheRequestedMaximumLengthWhenLengthIsEnoughToContainTheFacts() {
        get("https://catfact.ninja/facts?max_length=30&limit=10")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data", hasSize(lessThanOrEqualTo(10)))
                .body("data.fact", hasItems(notNullValue()))
                .body("data.length", hasItems(lessThanOrEqualTo(30)));
    }

    @Test
    public void testFactsShouldNotReturnAnythingWhenLengthIsNotEnoughToContainTheFacts() {
        get("https://catfact.ninja/facts?max_length=4&limit=10")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data", hasSize(lessThanOrEqualTo(10)))
                .body("data", empty());
    }

    @Test
    public void testBreedsShouldReturnListOfBreedsOfTheRequestedMaximumLength() {
        get("https://catfact.ninja/breeds?limit=10")
                .then()
                .statusCode(200)
                .assertThat()
                .body("data", hasSize(lessThanOrEqualTo(10)))
                .body("data.breed", hasItems(notNullValue()))
                .body("data.country", hasItems(notNullValue()))
                .body("data.origin", hasItems(notNullValue()))
                .body("data.coat", hasItems(notNullValue()))
                .body("data.pattern", hasItems(notNullValue()));
    }
}
