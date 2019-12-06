package testsPackage;

import org.junit.Test;

import static io.restassured.RestAssured.given;
public class watchesTest {

    @Test
    public void check_googleToUp() {
        given().when().get("http://52.136.215.164:9000/api/products").then().statusCode(200);
    }
}
