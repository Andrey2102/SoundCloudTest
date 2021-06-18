import io.restassured.http.ContentType;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

@RunWith(SerenityRunner.class)
public class SoundCloudTest {

    @Test
    public void GetTrack()
    {
        given()
                .baseUri(Config.SoundCloud_BASE_URL)
                .when()
                .get(Config.track)
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void GetPlaylist()
    {
        given()
                .baseUri(Config.SoundCloud_BASE_URL)
                .when()
                .get(Config.set)
                .then()
                .log().body()
                .assertThat()
                .body(Matchers.notNullValue());
    }

    @Test
    public void GetFakedSet()
    {
        given()
                .baseUri(Config.SoundCloud_BASE_URL)
                .when()
                .get(Config.fakedSet)
                .then()
                .log().body()
                .statusCode(404);
    }

    @Test
    public void PostUnexpectedData()
    {
        given()
                .baseUri(Config.SoundCloud_BASE_URL)
                .when()
                .post("Master of Pupets")
                .then()
                .log().body()
                .statusCode(200);
    }
}