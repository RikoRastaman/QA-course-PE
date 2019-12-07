package testsPackage;

import io.restassured.http.ContentType;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

public class watchesTest {

    static String GET_ALL_PRODUCTS = "http://52.136.215.164:9000/api/products";
    static String GET_DELETE_BY_ID = "http://52.136.215.164:9000/api/deleteproduct?id=";
    static String POST_ADD_PRODUCT = "http://52.136.215.164:9000/api/addproduct";
    static String POST_EDIT_PRODUCT = "http://52.136.215.164:9000/api/editproduct";

    @Test
    public void GetAllProducts_ResponseHeaderAndStatusCode_ShouldBeCorrect() {
        given().
        when().
            get(GET_ALL_PRODUCTS).
        then().assertThat().statusCode(200).
            and().
            contentType(ContentType.JSON).
        and().
            header("Content-Type", equalTo("application/json"));
        System.out.println("All products response header correct, status 200");
    }

    @Test
    public void BasicAuthenticationToAddProduct_ShouldBeGivenAccess() {

        given().
            auth().
            preemptive().
            basic("test177", "test177").
        when().
            get(POST_ADD_PRODUCT).
        then().
            assertThat().
            statusCode(200);
        System.out.println("Authentication to add access given, status 200");
    }

    @Test
    public void APIDeleteProduct_ProductDeleted() {
        String IDtoDelete = "403";
        given().
            param("id", IDtoDelete).
            auth().
            preemptive().
            basic("test177", "test177").
        when().
            get(GET_DELETE_BY_ID).
        then().
            assertThat().
            statusCode(200);
    }

}
