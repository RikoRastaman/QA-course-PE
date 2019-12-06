package testsPackage;

import org.junit.Test;

import static io.restassured.RestAssured.given;
public class watchesTest {

    @Test
    public void check_googleToUp() {
        given().when().get("http://www.google.com").then().statusCode(200);
    }
}
